/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import swm.project.loadDataToDb.Operations;

/**
 *
 * @author Kruti
 */
public class MeasurementMetrics {
    
    int userid;
    Set<Integer> predictedPositiveMovies;
    Set<Integer> actualTestPositives;
    Set<Integer> truePositives;
    double precision;
    double recall;
           
    
    public MeasurementMetrics(int userid, List<Integer> predictedMovies) {
        this.userid = userid;
        this.predictedPositiveMovies = new HashSet<>();
        this.actualTestPositives=new HashSet<>();
        calculateMetrics(predictedMovies);
    }

    private void calculateMetrics(List<Integer> predictedMovies){
        AllMappings m = AllMappings.getInstance();
        HashMap<Integer,Integer> actualTestRatings = null;
        HashSet<Integer>allPredicted = new HashSet<Integer>(predictedMovies);
       
        
        try {
            actualTestRatings = m.userToMovieRatings.getAllRatedMoviesFortestUser(userid);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println(actualTestRatings.keySet().toString());
        System.out.println("-----------------------------------------------------------");
        System.out.println(allPredicted.toString());
        predictedPositiveMovies = Operations.intersection(actualTestRatings.keySet(), allPredicted);
        for(int movId: actualTestRatings.keySet()){
            if(m.userToMovieCluster.userLikesMovie(actualTestRatings.get(movId), userid))
                actualTestPositives.add(movId);
        }
        
        truePositives = Operations.intersection(predictedPositiveMovies, actualTestPositives);
        
        precision = (double)truePositives.size()/(double)predictedPositiveMovies.size();
        recall = (double)truePositives.size()/(double)actualTestPositives.size();        
    }

    public double getPrecision() {
        return precision;
    }

    public double getRecall() {
        return recall;
    }
    
    
}
