/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Kruti
 */
public class AllMappings {
    private static final AllMappings mappings = new AllMappings();
    public MovieToMovieCluster movieToMovieCluster;
    public UserToMovieRatings userToMovieRatings;
    public UserToMovieCluster userToMovieCluster;
    private MovieRecommender movieReccommender;
    private AllMappings(){
        movieReccommender = new MovieRecommender();
    }
    
    public static AllMappings getInstance(){
        return mappings;
    }
            
    
    public void initMovieClusters(String movieClusterFile) throws IOException{
        movieToMovieCluster = new MovieToMovieCluster();
        movieToMovieCluster.setMovieToMovieCluster(movieClusterFile);
    }
    
    public void initUserRatings(){
        userToMovieRatings = new UserToMovieRatings();
        userToMovieRatings.setUserRatingsAll();
    }
    public void initUserRatings(String base, String test){
        userToMovieRatings = new UserToMovieRatings();
        userToMovieRatings.setUserRatingsBaseAndTest(base, test);
    }
   
    public void initUsertoMovieCluster(){
        userToMovieCluster = new UserToMovieCluster();
        userToMovieCluster.setUserToMovieCluster();
        userToMovieCluster.calculateUserToClusterProbability();
    }
    
    public List<Integer> reccomendMoviesForuser(int userId, int numberOfMovies)
    {
        return movieReccommender.getNMovies(numberOfMovies, userId,userToMovieCluster.getMovieClustersForUser(userId).values());
        
    }
    
}
