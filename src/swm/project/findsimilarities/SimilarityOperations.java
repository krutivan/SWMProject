/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

import java.lang.Math.*;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import swm.project.Consts;
import swm.project.loadDataToDb.Operations;

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
    /*wrong hass to be fixed*/
    public double pearsonsCoefficient(ArrayList<Double> vector1, ArrayList<Double> vector2){
        ArrayList<Double> vecNonZero1=new ArrayList<Double>();
        ArrayList<Double> vecNonZero2=new ArrayList<Double>();
        for(int i=0;i<vector1.size();i++)
        {
            if(vector1.get(i)!=0.0)
                vecNonZero1.add(vector1.get(i));
        }
        for(int i=0;i<vector2.size();i++)
        {
            if(vector2.get(i)!=0.0)
                vecNonZero2.add(vector2.get(i));
        }
        double avg1 = findAvg(vecNonZero1);
        double avg2 = findAvg(vecNonZero2);
        ArrayList<Double> normalizedv1 = new ArrayList<>();
        ArrayList<Double> normalizedv2 = new ArrayList<>();
        for (int i=0;i< vector1.size();i++){
            if(vector1.get(i)>0 && vector2.get(i)>0){
            normalizedv1.add(vector1.get(i)-avg1);
            normalizedv2.add(vector2.get(i)-avg2);
        }
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
    
    
   /*
    Kulvir & Sagar's old implementation
    public double findJacUOD(ArrayList<Integer> vector1, ArrayList<Integer> vector2){
        double commonFeatureDist=0,differentFeatureDistance=0;
        double simuv=0,numerator=0,denominator=0;
        int su=0, sv=0, suv = 0, suunionsv=0;
        boolean flag=false;
        
        for(int i=0;i<vector1.size();i++)
        {
            double vector1rating = vector1.get(i);
            double vector2rating = vector2.get(i);
            if(vector1rating!=vector2rating){
                flag=true;
            }
            if(vector1rating!=0)              
                   su++;
            if(vector2rating!=0)
                   sv++;
             if(vector1rating > 0 && vector2rating > 0)//common feature
            {
                suv++;
                denominator+=pow((vector1rating-vector2rating),2);
            }
             
        }
        suunionsv=su+sv-suv;
        numerator=sqrt(Consts.MAX_MOVIES*pow((MAX_RATING-MIN_RATING),2));
        System.out.println(suv + " " + numerator);
        if(flag)
            denominator=sqrt(denominator);
        else
            denominator=0.9;
        simuv=(suv/suunionsv)*(numerator/denominator);
        return simuv;
    }
    */
}

