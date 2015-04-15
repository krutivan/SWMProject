/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.clustering;

/**
 *
 * @author Kruti
 */
public class ClusterPoint {
    public static final int NOISE = 0;
    public static final int NOT_VISITED = -1;
    private int clusterPointId;
    private boolean visited;
    private int clusterNumber;

    public ClusterPoint(int clusterPointId, boolean visited) {
        this.clusterPointId = clusterPointId;
        this.visited = visited;
        this.clusterNumber = NOT_VISITED;
    }
      
    public void setClusterNumber(int clusterNumber) {
        this.clusterNumber = clusterNumber;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
    
    public boolean isVisited() {
        return visited;
    }

    public int getClusterNumber() {
        return clusterNumber;
    }

    public int getClusterPointId() {
        return clusterPointId;
    }
    
    
    
    
}
