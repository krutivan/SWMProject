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
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import swm.project.clustering.DBscan;
import weka.clusterers.Clusterer;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.SimpleKMeans;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.EuclideanDistance;
import weka.core.Instance;
import weka.core.Instances;
import weka.gui.beans.ClassAssigner;
import weka.gui.beans.DataSource;

/**
 *
 * @author Kruti
 */
class UserToUserCluster {
    
    
    HashMap<Integer,Integer> userToUserCluster;
    
    void clusterUsersFromUserToMovieCluster(int clusteringType) {
      if(clusteringType == MappingConstants.KMEANS)
          try {
              clusterWithKmeans();
      } catch (Exception ex) {
              System.err.println(ex.getMessage());
          Logger.getLogger(UserToUserCluster.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
    
    private void clusterWithKmeans() throws FileNotFoundException, IOException, Exception{
        Reader reader;
        reader = new FileReader(MappingConstants.USER_MOVIE_CLUSTERS);
        Instances instanceValues = new Instances(reader);
        SimpleKMeans kmeans = new SimpleKMeans();
        kmeans.setNumClusters(20);
        kmeans.setPreserveInstancesOrder(true);
        kmeans.setDistanceFunction(new EuclideanDistance());
        kmeans.buildClusterer(instanceValues);
        
        
        int[] assignments = kmeans.getAssignments();
        int userid = 0;
        for(int a: assignments)
            System.out.println(instanceValues.get(userid++).value(0)+" "+a);
        
       
    }
    private void clusterWithDBSCAN(){
       
        
                
    }
}
