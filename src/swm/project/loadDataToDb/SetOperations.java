/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Kruti
 */
public class SetOperations{
    public static <T> Set<T> union(Set<T> set1, Set<T> set2){
        Set<T> tmp = new HashSet<>(set1);
        tmp.addAll(set2);
        return tmp;    
    }
        
    public static <T> Set<T> intersection(Set<T> set1, Set<T> set2){
        Set<T> tmp = new HashSet<>();
        set1.stream().filter((x) -> (set2.contains(x))).forEach((x) -> {
            tmp.add(x);
        });
        return tmp;
    }

}
