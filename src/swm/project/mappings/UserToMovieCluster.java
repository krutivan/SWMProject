/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kruti
 */
 class UserToMovieCluster {
     HashMap<Integer, HashMap<Integer,UserVote>> uToMCLust;
     HashMap<Integer, Double> avgRatingForUser = new HashMap<>();
     public void setUserToMovieCluster(){
         uToMCLust = new HashMap<>();
                 
         AllMappings m = AllMappings.getInstance();
         Set<Integer> users = m.userToMovieRatings.getAllBaseUsers();
         for(int u:users){
             HashMap<Integer,Integer> movieAndRatings = m.userToMovieRatings.getAllRatedMoviesForBaseUser(u);
             Set<Integer> moviesRatedByUser = movieAndRatings.keySet();
            double avgRating = getAvgRatingsForUser(movieAndRatings.values());
            avgRatingForUser.put(u, avgRating);
            
             HashMap<Integer,UserVote> clusterToVote = new HashMap<>();
                    
            if(uToMCLust.containsKey(u))
                clusterToVote = uToMCLust.get(u);
            
                
            for(int mov: moviesRatedByUser){
                int rating = movieAndRatings.get(mov);
                int voteValue = -1;
                UserVote userClusterVote;
                
                if(userLikesMovie(rating,u))
                    voteValue = 1;
                
                int clusterNumber = m.movieToMovieCluster.getClusterNumber(mov);
                
                if(clusterToVote.containsKey(clusterNumber))
                    userClusterVote = clusterToVote.get(clusterNumber);
                else
                    userClusterVote = new UserVote(clusterNumber, m.movieToMovieCluster.getClusterSize(clusterNumber));
                
                userClusterVote.incrementVote(voteValue);
                clusterToVote.put(clusterNumber, userClusterVote);
                
            }
            uToMCLust.put(u, clusterToVote);
         }
     }
     
     private double getAvgRatingsForUser(Collection<Integer> values) {
       double sum = 0;
       for(int r : values){
           sum+=r;
       }
       return sum/(double)values.size();
    }

    public boolean userLikesMovie(int rating, int userid) {
       return  rating>= avgRatingForUser.get(userid);
    }
     public void calculateUserToClusterProbability(){
         Set<Integer> users = uToMCLust.keySet();
         for(int u: users){
             int totalVotes  = 0;
             HashMap<Integer,UserVote> userToClusterVotes = uToMCLust.get(u);
             Collection<UserVote> votes = userToClusterVotes.values();
             
             for(UserVote uv: votes){
                 totalVotes+= uv.getVotes();
             }
             for(int clusterNumber: userToClusterVotes.keySet()){
                 UserVote uv = userToClusterVotes.get(clusterNumber);
                 uv.calcProb(totalVotes);
             }
         }
         System.out.println("");
                
     }
     
     public HashMap<Integer, UserVote> getMovieClustersForUser(int userid){
         return uToMCLust.get(userid);
     }
     
     
}
