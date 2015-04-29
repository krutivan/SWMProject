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

/**
 *
 * @author Kruti
 */
public class DBscan {
    
    double similarityMatrix[][];
    HashMap<Integer, ClusterPoint> pointsToCluster;
    private Object monitor;

    
    public DBscan(int numOfItems, double[][] matrix){
        monitor=new Object();
        similarityMatrix = matrix;
        pointsToCluster = new HashMap<>();
        for(int i=1;i<= numOfItems; i++){
            pointsToCluster.put(i, new ClusterPoint(i, false));
        }
    }
    
    double[][] getSimilarityMatrix(){
        return similarityMatrix;
    }
    public synchronized HashMap<Integer, ClusterPoint> ClusterUsingDBScan(int minPnts, double eps){
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
    
    private synchronized Set<Integer> regionQuery(int id, double eps){
        Set<Integer> neighbors=Collections.synchronizedSet(new HashSet());
        
        for (int i = 0; i < similarityMatrix.length; i++) {
            if(similarityMatrix[id-1][i] > eps)
                neighbors.add(i+1);
            
        }
        return neighbors;
    }

    private synchronized void expandCluster(ClusterPoint currentPoint, Set<Integer> neighborPoints, int currentClusterNumber, double eps, int minPnts) {
        LinkedList<Integer> neighborsList= new LinkedList<>(neighborPoints);
        
        currentPoint.setClusterNumber(currentClusterNumber);
        Iterator<Integer> neighborsIterator = neighborPoints.iterator();
        while(!neighborsList.isEmpty()){
            ClusterPoint pointsNeighbor =pointsToCluster.get(neighborsList.getFirst());
            if(!pointsNeighbor.isVisited()){
                pointsNeighbor.setVisited(true);
                Set<Integer> neighborsofNeighbor = regionQuery(pointsNeighbor.getClusterPointId(), eps);
                 if(neighborsofNeighbor.size()>= minPnts){
                     for(Integer p: neighborsofNeighbor)
                         neighborsList.addLast(p);
                }
            }
            if(pointsNeighbor.getClusterNumber() == ClusterPoint.NOT_VISITED){
                pointsNeighbor.setClusterNumber(currentClusterNumber);
            }
            neighborsList.remove();
        }
        
//        while(neighborsIterator.hasNext()){
//            ClusterPoint pointsNeighbor = pointsToCluster.get(neighborsIterator.next());
//            if(!pointsNeighbor.isVisited()){
//                pointsNeighbor.setVisited(true);
//                Set<Integer> neighborsofNeighbor = regionQuery(pointsNeighbor.getClusterPointId(), eps);
//                if(neighborsofNeighbor.size()>= minPnts){
//                    expandCluster(pointsNeighbor, neighborsofNeighbor, currentClusterNumber, eps, minPnts);
//                    //neighborPoints.addAll(neighborsofNeighbor);
//                }
//                    
//            }
            
            
        
       
    }
    
}
