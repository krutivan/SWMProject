/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

import java.util.ArrayList;

/**
 *
 * @author Kruti
 */
public class SimilarityOperations {
    
    static final int MAX_RATING = 5;
    static final int MIN_RATING = 1;
    
    public double cosineSimilarities(ArrayList<Double> vector1, ArrayList<Double> vector2){
        double dotProduct=0;
        double squaredSum1=0,squaredSum2=0;
        for(int i = 0; i<vector1.size();i++){
            if(vector1.get(i)>0 && vector2.get(i)>0){
                dotProduct += (vector1.get(i))*(vector2.get(i));
                squaredSum1+= Math.pow(vector1.get(i),2);
                squaredSum2+= Math.pow(vector2.get(i),2);
            }
        }
        squaredSum1 = Math.sqrt(squaredSum1);
        squaredSum2 = Math.sqrt(squaredSum2);
        
        return(dotProduct/(squaredSum1 * squaredSum2));
        
    }
    
    public double pearsonsCoefficient(ArrayList<Double> vector1, ArrayList<Double> vector2){
        double avg1 = findAvg(vector1);
        double avg2 = findAvg(vector1);
        ArrayList<Double> normalizedv1 = new ArrayList<>();
        ArrayList<Double> normalizedv2 = new ArrayList<>();
        for (int i=0;i< vector1.size();i++){
            normalizedv1.add(vector1.get(i)-avg1);
            normalizedv2.add(vector2.get(i)-avg2);
        }
        return cosineSimilarities(normalizedv1, normalizedv2);
    }
 
    private double findAvg(ArrayList<Double> vector)
    {
        double sum=0;
        for(double i: vector){
            sum+=i;
        }
        return  sum/vector.size();
    }
    
    public double findSimilarity(ArrayList<Double> vector1, ArrayList<Double> vector2){  
        double commonFeatureDist=0,differentFeatureDistance=0;
        double numberOfRankedItems1=0, numberOfRankedItems2=0, commonRanked = 0;
        for(int i=0;i<vector1.size();i++){
            double vector1rating = vector1.get(i);
            double vector2rating = vector2.get(i);
            if(vector1rating>0)
                numberOfRankedItems1++;
            if(vector2rating>0)
                numberOfRankedItems2++;
            if(vector1rating > 0 && vector2rating > 0)//common feature
            {
                commonRanked++;
                commonFeatureDist+= Math.abs(1 - (MAX_RATING -MIN_RATING)*(Math.abs(vector1rating-vector2rating)));
            }
        }
        differentFeatureDistance = (numberOfRankedItems1-commonRanked) + (numberOfRankedItems2 - commonRanked);
        
        return (commonFeatureDist/(commonFeatureDist+differentFeatureDistance));
    } 
}
