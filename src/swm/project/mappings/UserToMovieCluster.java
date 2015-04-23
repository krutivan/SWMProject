/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
     HashMap<Integer,List<UserVote>> uToMCLust;
     HashMap<Integer, Double> avgRatingForUser = new HashMap<>();
     public void setUserToMovieCluster(){
         uToMCLust = new HashMap<>();
                 
         AllMappings m = AllMappings.getInstance();
         Set<Integer> users = m.userToMovieRatings.getAllBaseUsers();
         int numberOfMovieClusters = m.movieToMovieCluster.getNumberOfMovieclusters();
         for(int u:users){
             HashMap<Integer,Integer> movieAndRatings = m.userToMovieRatings.getAllRatedMoviesForBaseUser(u);
             Set<Integer> moviesRatedByUser = movieAndRatings.keySet();
            double avgRating = getAvgRatingsForUser(movieAndRatings.values());
            avgRatingForUser.put(u, avgRating);
            
            List<UserVote> userVoteForCluster = new ArrayList<>(numberOfMovieClusters);
            for(int i=0;i<numberOfMovieClusters;i++)
                userVoteForCluster.add(new UserVote(i, m.movieToMovieCluster.getClusterSize(i)));
            
            if(uToMCLust.containsKey(u))
                userVoteForCluster = uToMCLust.get(u);
            
                
            for(int mov: moviesRatedByUser){
                int rating = movieAndRatings.get(mov);
                int voteValue = -1;
                
                
                if(userLikesMovie(rating,u))
                    voteValue = 1;
                
                int clusterNumber = m.movieToMovieCluster.getClusterNumber(mov);
                
               
                userVoteForCluster.get(clusterNumber).incrementVote(voteValue);
            }
            uToMCLust.put(u, userVoteForCluster);
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
             List<UserVote> votes = uToMCLust.get(u);

             for(UserVote uv: votes){
                 totalVotes+= uv.getVotes();
             }
             for(int clusterNumber=0; clusterNumber<AllMappings.getInstance().movieToMovieCluster.getNumberOfMovieclusters();clusterNumber++){
                 UserVote uv = votes.get(clusterNumber);
                 uv.calcProb(totalVotes);
             }
         }
         System.out.println("");
                
     }
     
     public List<UserVote> getMovieClustersForUser(int userid){
         return uToMCLust.get(userid);
     }
     
     public void putUserToMovieClustersToFile(String fileName) throws FileNotFoundException{
         PrintWriter pw = new PrintWriter(fileName);
         pw.write("User ID");
         for(int i=0;i<AllMappings.getInstance().movieToMovieCluster.getNumberOfMovieclusters();i++)
             pw.write(",Cluster "+i);
         for(int u:uToMCLust.keySet()){
             pw.write("\n"+u);
             for(UserVote v: uToMCLust.get(u)){
                pw.write(","+v.getProb());
             }
//             
         }
         pw.close();
     }
     
}
