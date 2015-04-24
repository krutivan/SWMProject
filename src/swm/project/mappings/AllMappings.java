/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

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
    private UserToUserCluster userToUserCluster;
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
        try {
            userToMovieRatings.setUserRatingsBaseAndTest(base, test);
        } catch (IOException ex) {
            System.err.println("base or test file does NOT exit"+ex.getMessage());
        }
    }
   
    public void initUsertoMovieCluster(){
        userToMovieCluster = new UserToMovieCluster();
        userToMovieCluster.setUserToMovieCluster();
        userToMovieCluster.calculateUserToClusterProbability();
    }
    
    public void initUserClustersFromMovieClusters(){
        userToUserCluster = new UserToUserCluster();
        userToUserCluster.clusterUsersFromUserToMovieCluster(MappingConstants.KMEANS);
    }
    
    public List<Integer> reccomendMoviesForuser(int userId, int numberOfMovies)
    {
        return movieReccommender.getNMovies(numberOfMovies, userId,userToMovieCluster.getMovieClustersForUser(userId));
    }
    
    public MeasurementMetrics getMeasurementMetricsForUser(int userId, List<Integer> predictedMovieIds)
    {
        MeasurementMetrics m = new MeasurementMetrics(userId, predictedMovieIds);
        return m;
    }
    
    public HashMap<Integer, MeasurementMetrics> getMeasurementMetricsForAllTestUsers(int numberOfMovies){
        Set<Integer> testUsers = userToMovieRatings.getAllTestUsers();
        HashMap<Integer,MeasurementMetrics> metricsForTestUsers = new HashMap<>();
        for(int user: testUsers){
            MeasurementMetrics m = getMeasurementMetricsForUser(user, reccomendMoviesForuser(user, numberOfMovies));
            metricsForTestUsers.put(user, m);
        }
        return metricsForTestUsers;
    }
    
    public void writeUserToMovieClustersToFile() throws FileNotFoundException{
        userToMovieCluster.putUserToMovieClustersToFile(MappingConstants.USER_MOVIE_CLUSTERS);
    }
}
