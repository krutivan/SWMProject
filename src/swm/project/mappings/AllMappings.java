/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import swm.project.loadDataToDb.Operations;

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
    public UserToUserCluster userToUserCluster;
    public UserClusterToMovieCluster userClusterToMovieCluster;
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
    
    public void initUserToUserClusters(){
        userToUserCluster = new UserToUserCluster();
        userToUserCluster.clusterUsers(MappingConstants.KMEANS);
    }
    
    public void initUserClusterToMovieCluster(){
        userClusterToMovieCluster =  new UserClusterToMovieCluster();
        
    }
    
    public List<Integer> reccomendMoviesForuser(int userId, int numberOfMovies, int recommendationType, int userClusterType)
    {
        if(recommendationType==MappingConstants.HINDAWI_RECOMMENDATION)
            return movieReccommender.getNMovies(numberOfMovies, userId,userToMovieCluster.getMovieClustersForUser(userId));
        else{
            List <Integer> userProfileRec, userHistoryRec, finalRec;
            if(userClusterType == MappingConstants.USER_HISTORY_CLUSTER || userClusterType == MappingConstants.USER_PROFILE_CLUSTER)
                return  movieReccommender.getNMovies(numberOfMovies, userId, userClusterType);
            else{
                    userProfileRec = movieReccommender.getNMovies(numberOfMovies, userId, MappingConstants.USER_PROFILE_CLUSTER);
                    userHistoryRec = movieReccommender.getNMovies(numberOfMovies, userId, MappingConstants.USER_HISTORY_CLUSTER);
//                    finalRec = new ArrayList<>();
//                    finalRec.addAll(userProfileRec);
//                    finalRec.addAll(userHistoryRec);
//                    return finalRec;
                    return new ArrayList<>(Operations.intersection(userProfileRec, userHistoryRec));
            }
            
        }
    }
    
    public MeasurementMetrics getMeasurementMetricsForUser(int userId, List<Integer> predictedMovieIds)
    {
        MeasurementMetrics m = new MeasurementMetrics(userId, predictedMovieIds);
        return m;
    }
    
    public HashMap<Integer, MeasurementMetrics> getMeasurementMetricsForAllTestUsers(int numberOfMovies, int RecommendationType, int userClusterType){
        Set<Integer> testUsers = userToMovieRatings.getAllTestUsers();
        HashMap<Integer,MeasurementMetrics> metricsForTestUsers = new HashMap<>();
        for(int user: testUsers){
            MeasurementMetrics m = getMeasurementMetricsForUser(user, reccomendMoviesForuser(user, numberOfMovies,RecommendationType, userClusterType));
            metricsForTestUsers.put(user, m);
        }
        return metricsForTestUsers;
    }
    
    public void writeUserToMovieClustersToFile(String Filetype) throws FileNotFoundException{
        if(Filetype.equals("CSV"))
            userToMovieCluster.putUserToMovieClustersToCsvFile(MappingConstants.USER_MOVIE_CLUSTERS);
        else
            userToMovieCluster.putUserToMovieClustersToArffFile(MappingConstants.USER_MOVIE_CLUSTERS, "UserToMovClusters");
    }
}
