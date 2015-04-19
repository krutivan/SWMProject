/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import swm.project.Consts;

/**
 *
 * @author Kruti
 */
 class MovieToMovieCluster {
    private HashMap<Integer, Integer> movToClust;
    private HashMap<Integer, LinkedList<Integer>> clustToMov;
            
    
    public void  setMovieToMovieCluster(String filename) throws IOException {
        movToClust = new HashMap<>();
        clustToMov = new HashMap<>();
        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        int i = 0;
        while((line = br.readLine())!=null){
            String vals[] = line.split(",");
            int movieId = Integer.parseInt(vals[0]);
            int clusterNumber = Integer.parseInt(vals[1]);
            movToClust.put(movieId,clusterNumber );
            LinkedList<Integer> movies = new LinkedList<>();
            if(clustToMov.containsKey(clusterNumber))
                movies = clustToMov.get(clusterNumber);
            
            movies.add(movieId);
            clustToMov.put(clusterNumber, movies);
        }
    }
    public int getClusterNumber(int movie)
    {
        return movToClust.get(movie);
    }
    
    public int getClusterSize(int clusterNumber){
        return clustToMov.get(clusterNumber).size();
    }
    
    
    

    
    
    
}
