/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 *
 * @author Kruti
 */
public class Operations{
    public static <T> Set<T> union(List<T> set1, List<T> set2){
        Set<T> tmp = new HashSet<>(set1);
        tmp.addAll(set2);
        return tmp;    
    }
     public static <T> Set<T> union(Set<T> set1, Set<T> set2){
        Set<T> tmp = new HashSet<>(set1);
        tmp.addAll(set2);
        return tmp;    
    }
        
    public static <T> Set<T> intersection(List<T> set1, List<T> set2){
        Set<T> tmp = new HashSet<>();
        set1.stream().filter((x) -> (set2.contains(x))).forEach((x) -> {
            tmp.add(x);
        });
        return tmp;
    }
     public static <T> Set<T> intersection(Set<T> set1, Set<T> set2){
        Set<T> tmp = new HashSet<>();
        set1.stream().filter((x) -> (set2.contains(x))).forEach((x) -> {
            tmp.add(x);
        });
        return tmp;
    }
    
    public static <T> double findJaccardDistance(List<T> set1,List<T> set2){
        float dist = ((float)intersection(set1, set2).size())/((float)(union(set1, set2).size()));
        return dist;
    }
    
    public static double findBinaryJaccardDistance(String set1,String set2){
        long a =  new BigInteger(set1, 2).longValue();
        long b =  new BigInteger(set2, 2).longValue();
        if((a | b) == 0)
            return 0;
        double dist=(double) (a & b)/(a | b);
        return dist;
    }
    
     public static double function(double x)
     {
         double temp=0.0462*x;
         double result=1.2741*Math.exp(temp);
         return result;
     }
     
    public static double findSimilarDates(int date1,int date2)
    {
        double numerator=sigma(Math.min(date1, date2),Math.max(date1, date2));
        double denominator=sigma(1922,1998);
        return numerator/denominator;
    }
    
       public static double sigma(int min, int max){
           double initial=function(min);
           for (int i = min+1; i <= max; i++) {
               initial += function(i);
           }
           return initial;
       }
       
       public static int findSimilar(String a,String b)
       {
           if(a.equals(b))
               return 1;
           return 0;
       }
 }
