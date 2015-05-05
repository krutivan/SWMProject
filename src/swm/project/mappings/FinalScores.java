/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Kruti
 */
class FinalScores {
    
    int userClusterId;
    int userClusterType;
    HashMap<Integer, Double> movieClusterScores; //<movieid, score>
    LinkedList<Integer> sortedMovClusters;
    
    public FinalScores(int userClusterId, int clusterType) {
        this.userClusterId = userClusterId;
        userClusterType = clusterType;
        movieClusterScores = new HashMap<>(AllMappings.getInstance().movieToMovieCluster.getNumberOfMovieclusters());
    }
    
    void calculateAllScores(){
        HashMap<Integer, HashMap<Integer,Double>> probsOfClusterByAllUsers = new HashMap<>();
        ArrayList<Integer> usersInCluster = getUsersInCluster();
        AllMappings m = AllMappings.getInstance();
        for(int userid:usersInCluster){
            List<UserVote> votesForCluster = m.userToMovieCluster.getMovieClustersForUser(userid);
            for(UserVote uv: votesForCluster){
                
                HashMap<Integer,Double> userAndVoteProb = new HashMap<>();
                
                if(probsOfClusterByAllUsers.containsKey(uv.getClusterId()))
                    userAndVoteProb = probsOfClusterByAllUsers.get(uv.getClusterId());
                if(uv.getProb()>0)
                    userAndVoteProb.put(userid, uv.getProb());
                
                probsOfClusterByAllUsers.put(uv.getClusterId(), userAndVoteProb);
               
            }
        }
        calculateFinalScores(probsOfClusterByAllUsers);
        calculateSortedMovieScores();
    }
    
    ArrayList<Integer> getUsersInCluster(){
        if(userClusterType == MappingConstants.USER_HISTORY_CLUSTER)
            return AllMappings.getInstance().userToUserCluster.getUsersInUserClusterHistory(userClusterId);
        else
            return AllMappings.getInstance().userToUserCluster.getUsersInUserClusterProfile(userClusterId);
    }

    private void calculateFinalScores(HashMap<Integer, HashMap<Integer, Double>> probsOfClusterByAllUsers) {
       for(int clusterNumber: probsOfClusterByAllUsers.keySet()){
           double sum=0;
           for(int userid: probsOfClusterByAllUsers.get(clusterNumber).keySet()){
               sum+= probsOfClusterByAllUsers.get(clusterNumber).get(userid);
           }
           sum /= probsOfClusterByAllUsers.get(clusterNumber).size();
           movieClusterScores.put(clusterNumber, sum);
       }
       
           
    }

    private void calculateSortedMovieScores() {
        sortedMovClusters = new LinkedList<>(movieClusterScores.keySet());
        sortedMovClusters.sort(new Comparator<Integer>() {

            @Override
            public int compare(Integer o1, Integer o2) {
               if(movieClusterScores.get(o1)< movieClusterScores.get(o2))
                   return 1;
               return -1;
            }
        });
    }

    public LinkedList<Integer> getSortedMovClusters() {
        return sortedMovClusters;
    }
    
    public double getScoreForCluster(int clusterNumber){
        return sortedMovClusters.get(clusterNumber);
    }
    
    
}
