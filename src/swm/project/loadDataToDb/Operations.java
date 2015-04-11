/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

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
}
