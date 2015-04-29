/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

/**
 *
 * @author Kruti
 */
public final class MappingConstants {
    //clusering type
    public static final int KMEANS =0;
    
    //filnames
    public static final String USER_MOVIE_CLUSTERS = "datafiles//userToMovieCluster.arff";
    public static final String USER_USER_CLUSTERS_JAC ="datafiles//useruserclustersjac.csv";
    //user cluster Type
    public static final int USER_HISTORY_CLUSTER = 0;
    public static final int USER_PROFILE_CLUSTER = 1;
    public static final int USER_HISTORY_PROFILE_CLUSTER = 2;
    
    //recommendation TYpe
    public static final int HINDAWI_RECOMMENDATION = 0;
    public static final int MATRIX_RECOMMENDATION = 1;
    
    //userClustersOnProfile
    public static final String USER_USER_CLUSTER_HIER = "datafiles//userToUserClustersHier.arff";
    public static final String USER_USER_CLUSTER_KMEANS = "datafiles//userToUserClustersKmeans.arff";
    public static final int NUM_OF_USER_PROFILE_CLUSTERS = 30;
}
