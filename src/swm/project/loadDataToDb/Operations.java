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
        
    public static <T> Set<T> intersection(List<T> set1, List<T> set2){
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
    
    public static long findBinaryJaccardDistance(String set1,String set2){
        long a =  new BigInteger(set1, 2).longValue();
        long b =  new BigInteger(set2, 2).longValue();
        if((a | b) == 0)
            return 0;
        long dist=(a & b)/(a | b);
        return dist;
    }
}
