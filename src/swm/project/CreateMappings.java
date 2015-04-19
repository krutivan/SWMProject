/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;

import com.sun.javafx.font.freetype.HBGlyphLayout;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import swm.project.loadDataToDb.GetDataFromDb;

/**
 *
 * @author Kruti
 */
public class CreateMappings {
    
 
    HashMap<Integer, Integer> movieToMovieCluster;
    HashMap<Integer, HashMap<Integer,Integer>> userToMovieClusters;
     HashMap<Integer, HashMap<Integer,Integer>> userRatings;
    public void createMovieToMovieClusters() throws FileNotFoundException, IOException{
        movieToMovieCluster = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("datafiles//MovieClusters.csv"));
        String line;
        int i = 0;
        while((line = br.readLine())!=null){
            String vals[] = line.split(",");
            movieToMovieCluster.put(Integer.parseInt(vals[0]), Integer.parseInt(vals[1]));
        }
    }
    
    public void createUserToMovieCluster(){
        GetDataFromDb gdb = new GetDataFromDb();
        userRatings = gdb.getAllUserRatings();
        userToMovieClusters = new HashMap<>();
        Set<Integer> users = userRatings.keySet();
        for(int u: users){
            HashMap<Integer,Integer> movieAndRatings = userRatings.get(u);
            Set<Integer> moviesRatedByUser = movieAndRatings.keySet();
            double avgRatingForUser = getAvgRatingsForUser(movieAndRatings.values());
            HashMap<Integer,Integer> clusterToVote = new HashMap<Integer, Integer>();
                    
            if(!userToMovieClusters.containsKey(u))
                userToMovieClusters.put(u, clusterToVote);
            for(int m: moviesRatedByUser){
                int rating = movieAndRatings.get(m);
                int voteValue = -1;
                int oldVote=0;
                
                if(userLikesMovie(rating,avgRatingForUser))
                    voteValue = 1;
                
                int clusterNumber = movieToMovieCluster.get(m);
                if(clusterToVote.containsKey(clusterNumber))
                    oldVote = clusterToVote.get(clusterNumber);
                    
                clusterToVote.put(clusterNumber, oldVote+voteValue);
            }
        }
        System.out.println("");
    }

    private double getAvgRatingsForUser(Collection<Integer> values) {
       double sum = 0;
       for(int r : values){
           sum+=r;
       }
       return sum/(double)values.size();
    }

    private boolean userLikesMovie(int rating, double avgRatingForUser) {
       return  rating>= avgRatingForUser;
    }
   
    
    
}
