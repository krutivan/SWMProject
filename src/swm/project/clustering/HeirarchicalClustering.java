/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.clustering;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import swm.project.Consts;
import java.io.PrintWriter;
/*
 *
 * @author Kruti
 */
public class HeirarchicalClustering {
    
     double similarityMatrix[][];
     String[] movies = new String[Consts.MAX_MOVIES];
    private Object monitor;
    
    
    HeirarchicalClustering(int numOfItems, double[][] matrix){
        monitor=new Object();
        similarityMatrix = matrix;
        
        for(int i=0;i< Consts.MAX_MOVIES; i++){
            movies[i] = Integer.toString(i);
        }
    }
    
    double[][] getSimilarityMatrix(){
        return similarityMatrix;
    }
    
    public com.apporiented.algorithm.clustering.Cluster ClusterUsingHC() {
    
com.apporiented.algorithm.clustering.ClusteringAlgorithm alg = new com.apporiented.algorithm.clustering.DefaultClusteringAlgorithm();
com.apporiented.algorithm.clustering.Cluster cluster = alg.performClustering(similarityMatrix, movies,
    new com.apporiented.algorithm.clustering.CompleteLinkageStrategy());
//com.apporiented.algorithm.clustering.visualization.DendrogramPanel dp = new com.apporiented.algorithm.clustering.visualization.DendrogramPanel();
//dp.setModel(cluster);
    return cluster;

    }
    

}
