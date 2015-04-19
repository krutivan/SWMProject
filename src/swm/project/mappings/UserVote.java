/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

/**
 *
 * @author Kruti
 */
 class UserVote {
    
    private int clusterId;
    private int votes;
    private int totalMoviesRatedInCluster;
    private double prob;
    private int clusterSize;

    public UserVote(int clusterId, int clusterSize) {
        this.clusterId = clusterId;
        this.clusterSize = clusterSize;
        votes=0;
        totalMoviesRatedInCluster =0;
    }
    
    public void incrementVote(int newVote)
    {
        totalMoviesRatedInCluster++;
        votes+=newVote;
        if(votes < 0)
            votes=0;
            
    }
    
    public void calcProb(int totalVotes){
        prob = (double)votes/(double)totalVotes;
    }
    
    public int getClusterId() {
        return clusterId;
    }

    public int getVotes() {
        return votes;
    }

    public int getTotalMoviesRatedInCluster() {
        return totalMoviesRatedInCluster;
    }

    public double getProb() {
        return prob;
    }

    public int getClusterSize() {
        return clusterSize;
    }
    
    
}
