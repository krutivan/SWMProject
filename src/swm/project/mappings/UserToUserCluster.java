/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.EuclideanDistance;
import weka.core.Instances;
import weka.core.SelectedTag;
import weka.core.Tag;

/**
 *
 * @author Kruti
 */
class UserToUserCluster {
    
    
    HashMap<Integer,Integer> userToUserClusterHistory;
    HashMap<Integer, ArrayList<Integer>> userClustersToUsersHistory;
     HashMap<Integer,Integer> userToUserClusterProfile;
    HashMap<Integer, ArrayList<Integer>> userClustersToUsersProfile;
            
    
    void clusterUsersFromUserToMovieCluster(int clusteringType) {
      if(clusteringType == MappingConstants.KMEANS)
        try {
              clusterUserHistoryWithKmeans();
              clusterUserProfileWithKmeans();
              //clusterWithDBSCAN();
      } catch (Exception ex) {
              System.err.println(ex.getMessage());
          Logger.getLogger(UserToUserCluster.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    private void clusterUserHistoryWithKmeans() throws FileNotFoundException, IOException, Exception{
        Reader reader;
        userToUserClusterHistory = new HashMap<>();
        userClustersToUsersHistory = new HashMap<>();
        reader = new FileReader(MappingConstants.USER_MOVIE_CLUSTERS);
        Instances instanceValues = new Instances(reader);
        SimpleKMeans kmeans = new SimpleKMeans();
        
        kmeans.setNumClusters(20);
        kmeans.setPreserveInstancesOrder(true);
        kmeans.setDistanceFunction(new EuclideanDistance());
        kmeans.buildClusterer(instanceValues);
        
        
        int[] assignments = kmeans.getAssignments();
        int userid = 0;
        for(int clusterNo: assignments){
            int user = (int)instanceValues.get(userid).value(0);
            userToUserClusterHistory.put(user, clusterNo);
            ArrayList<Integer> users = new ArrayList<>();
            if(userClustersToUsersHistory.containsKey(clusterNo)){
                users=userClustersToUsersHistory.get(clusterNo);
                users.add(user);
            }else{
                users.add(user);
                userClustersToUsersHistory.put(clusterNo, users);
            }
          
        }  
    }
    private void clusterWithDBSCAN() throws FileNotFoundException, IOException, Exception{
        Reader reader;
        reader = new FileReader(MappingConstants.USER_MOVIE_CLUSTERS);
        Instances instanceValues = new Instances(reader);
        HierarchicalClusterer h = new  HierarchicalClusterer();
        
        h.setNumClusters(20);
        h.setPrintNewick(true);
        h.buildClusterer(instanceValues);      
    }
    
    int numberOfClustersHistory(){
        return userClustersToUsersHistory.keySet().size();
    }
    
    ArrayList<Integer> getUsersInUserClusterHistory(int clusterNumber){
        return userClustersToUsersHistory.get(clusterNumber);
    }
    ArrayList<Integer> getUsersInUserClusterProfile(int clusterNumber){
       return userClustersToUsersProfile.get(clusterNumber);
    }
    int getClusterNumberForUser(int userid, int clusterType){
        if(clusterType==MappingConstants.USER_HISTORY_CLUSTER){
            if(userToUserClusterHistory.containsKey(userid))
                return userToUserClusterHistory.get(userid);
            else
                return 0;
        }
        else
            return userToUserClusterProfile.get(userid);
    }  

    private void clusterUserProfileWithKmeans() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
