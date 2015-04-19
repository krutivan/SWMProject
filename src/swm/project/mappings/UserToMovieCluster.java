/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

/**
 *
 * @author Kruti
 */
 class UserToMovieCluster {
     HashMap<Integer, HashMap<Integer,UserVote>> uToMCLust;
     
     public void setUserToMovieCluster(){
         AllMappings m = AllMappings.getInstance();
         Set<Integer> users = m.userToMovieRatings.getAllBaseUsers();
         for(int u:users){
             HashMap<Integer,Integer> movieAndRatings = m.userToMovieRatings.getAllRatedMoviesForBaseUser(u);
             Set<Integer> moviesRatedByUser = movieAndRatings.keySet();
            double avgRatingForUser = getAvgRatingsForUser(movieAndRatings.values());
             HashMap<Integer,UserVote> clusterToVote = new HashMap<>();
                    
            if(!uToMCLust.containsKey(u))
                uToMCLust.put(u, clusterToVote);
            
            for(int mov: moviesRatedByUser){
                int rating = movieAndRatings.get(mov);
                int voteValue = -1;
                UserVote userClusterVote;
                
                if(userLikesMovie(rating,avgRatingForUser))
                    voteValue = 1;
                
                int clusterNumber = m.movieToMovieCluster.getClusterNumber(mov);
                if(clusterToVote.containsKey(clusterNumber))
                    userClusterVote = clusterToVote.get(clusterNumber);
                else
                    userClusterVote = new UserVote(clusterNumber, m.movieToMovieCluster.getClusterSize(clusterNumber));
                
                userClusterVote.incrementVote(voteValue);
                clusterToVote.put(clusterNumber, userClusterVote);
            }
         }
     }
     
     private double getAvgRatingsForUser(Collection<Integer> values) {
       double sum = 0;
       for(int r : values){
           sum+=r;
       }
       return sum/(double)values.size();
    }

    private boolean userLikesMovie(int rating, double avgRatingForUser) {
       return  rating>= avgRatingForUser;
    }
     public void calculateUserToClusterProbability(){
        
     }
}
