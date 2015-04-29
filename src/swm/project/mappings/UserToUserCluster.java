/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import weka.clusterers.HierarchicalClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.EuclideanDistance;
import weka.core.Instance;
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
            
    
    void clusterUsers(int clusteringType) {
      if(clusteringType == MappingConstants.KMEANS)
        try {
                userClustersJacOnHistory();
              //clusterUserHistoryWithKmeans();
              clusterUserProfile();
              //clusterWithDBSCAN();
      } catch (Exception ex) {
              System.err.println(ex.getMessage());
          Logger.getLogger(UserToUserCluster.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    private void userClustersJacOnHistory() throws FileNotFoundException, IOException{
        userToUserClusterHistory= new HashMap<>();
        userClustersToUsersHistory=new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader(MappingConstants.USER_USER_CLUSTERS_JAC));
        String line=null;
        while((line = br.readLine())!= null){
            String[] vals = line.split(",");
            userToUserClusterHistory.put(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]));
        }
        for(int user: userToUserClusterHistory.keySet())
        {
            int clusterNo = userToUserClusterHistory.get(user);
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
            userid++;
          
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

    private void clusterUserProfile() throws FileNotFoundException, IOException {
        userToUserClusterProfile= new HashMap<>();
        userClustersToUsersProfile = new HashMap<>();
        Reader reader;
        reader = new FileReader(MappingConstants.USER_USER_CLUSTER_HIER);
        Instances instanceValues = new Instances(reader);
        
        List<String> clusterVals = new ArrayList<>(MappingConstants.NUM_OF_USER_PROFILE_CLUSTERS);
        for(int i = 0; i<= MappingConstants.NUM_OF_USER_PROFILE_CLUSTERS; i++)
            clusterVals.add("cluster"+i);
        Attribute a = new Attribute("Cluster",clusterVals);
        for(Instance inst: instanceValues){
            int userId = (int)inst.value(1);
            int clusterNumber = (int)inst.value(instanceValues.attribute("Cluster"));
            userToUserClusterProfile.put(userId, clusterNumber);
             ArrayList<Integer> users = new ArrayList<>();
            if(userClustersToUsersProfile.containsKey(clusterNumber)){
                users=userClustersToUsersProfile.get(clusterNumber);
                users.add(userId);
            }else{
                users.add(userId);
                userClustersToUsersProfile.put(clusterNumber, users);
            }
        }
    }   
}
