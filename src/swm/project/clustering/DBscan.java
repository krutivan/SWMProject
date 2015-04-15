/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.clustering;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Kruti
 */
public class DBscan {
    
    double similarityMatrix[][];
    HashMap<Integer, ClusterPoint> pointsToCluster;
    
    void initializeSimilarityMatrix(int numOfItems, double[][] matrix){
        similarityMatrix = matrix;
        pointsToCluster = new HashMap<>();
        for(int i=1;i<= numOfItems; i++){
            pointsToCluster.put(i, new ClusterPoint(i, false));
        }
    }
    
    double[][] getSimilarityMatrix(){
        return similarityMatrix;
    }
    HashMap<Integer, ClusterPoint> ClusterUsingDBScan(int minPnts, double eps){
        Set<Integer> keySet = pointsToCluster.keySet();
        Iterator<Integer> pointsIterator = keySet.iterator();
        
        int currentClusterNumber = 0;
        
        while(pointsIterator.hasNext()){
            int id = pointsIterator.next();
            ClusterPoint currentPoint = pointsToCluster.get(id);
            if(!currentPoint.isVisited()){
                currentPoint.setVisited(true);
                Set<Integer> neighborPoints= regionQuery(id, eps);
                if(neighborPoints.size() < minPnts)
                    currentPoint.setClusterNumber(ClusterPoint.NOISE);
                else{
                    currentClusterNumber++;
                    expandCluster(currentPoint,neighborPoints,currentClusterNumber,eps,minPnts);
                }
                
                    
            }
        }
        return pointsToCluster;
    }
    
    HashSet<Integer> regionQuery(int id, double eps){
        HashSet<Integer> neighbors=new HashSet<>();
        
        for (int i = 0; i < similarityMatrix.length; i++) {
            if(similarityMatrix[id-1][i] > eps)
                neighbors.add(i+1);
            
        }
        return neighbors;
    }

    private void expandCluster(ClusterPoint currentPoint, Set<Integer> neighborPoints, int currentClusterNumber, double eps, int minPnts) {
        currentPoint.setClusterNumber(currentClusterNumber);
        Iterator<Integer> neighborsIterator = neighborPoints.iterator();
        while(neighborsIterator.hasNext()){
            ClusterPoint pointsNeighbor = pointsToCluster.get(neighborsIterator.next());
            if(!pointsNeighbor.isVisited()){
                pointsNeighbor.setVisited(true);
                Set<Integer> neighborsofNeighbor = regionQuery(pointsNeighbor.getClusterPointId(), eps);
                if(neighborsofNeighbor.size()>= minPnts)
                    neighborPoints.addAll(neighborsofNeighbor);
            }
            if(pointsNeighbor.getClusterNumber() == ClusterPoint.NOT_VISITED){
                pointsNeighbor.setClusterNumber(currentClusterNumber);
            }
            
        }
       
    }
    
}
