/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import swm.project.clustering.ClusterPoint;
import swm.project.clustering.DBscan;
import swm.project.findsimilarities.FindMovieSimilarities;
import swm.project.loadDataToDb.LoadDataToDb;
import swm.project.mappings.AllMappings;
import swm.project.mappings.MappingConstants;
import swm.project.mappings.MeasurementMetrics;

/**
 *
 * @author Kruti
 */
public class SwmProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        //loadAllDataToDb();
        //findSimilarities();
        //clusterMovies();
//      findMetricForAllBaseAndTest();
//         AllMappings m = AllMappings.getInstance();
//         m.initUserRatings("datafiles//baseandtestmatrix//u"+1+".base","datafiles//baseandtestmatrix//u"+1+".test"); 
//         m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
//                m.initUsertoMovieCluster();
//               // m.writeUserToMovieClustersToFile("ARFF");
//                m.initUserToUserClusters();
//                m.initUserClusterToMovieCluster();
//                for (int j = 30; j <= 30; j+=10) {
//                    HashMap<Integer,MeasurementMetrics> measurements = m.getMeasurementMetricsForAllTestUsers(j,MappingConstants.MATRIX_RECOMMENDATION, MappingConstants.USER_HISTORY_CLUSTER);
//                    String filename = "datafiles//baseandtest//measure//u"+1+"//measure"+j+".csv";
//                    PrintWriter pw = new PrintWriter(filename);
//                    pw.print("Userid, Precision, Recall");
//                    for(int userId: measurements.keySet()){
//                        pw.print("\n");
//                        pw.print(userId);
//                        pw.print(",");
//                        pw.print(measurements.get(userId).getPrecision());
//                        pw.print(",");
//                        pw.print(measurements.get(userId).getRecall());
//                    }
//                    pw.close();
//                }
      findMetricForAllBaseAndTestMatrix();
//      findMetricForAllBaseAndTest();
       
        
//        m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
//        m.initUserRatings();
//        //m.initUserRatings("datafiles//baseandtest//u1.base","datafiles//baseandtest//u1.test");
//        m.initUsertoMovieCluster();
//        m.initUserClustersFromMovieClusters();
//        m.initUserClusterToMovieCluster();
//        List<Integer> movs = m.reccomendMoviesForuser(1, 30, MappingConstants.MATRIX_RECOMMENDATION);
//        System.out.println(movs.toString());
//        
//        m.writeUserToMovieClustersToFile();
//        MeasurementMetrics meas = m.getMeasurementMetricsForUser(1, m.reccomendMoviesForuser(1, 1600));
//        System.out.println(meas.getPrecision()+ "   "+ meas.getRecall());
        
    }
 
    
    public static void loadAllDataToDb(){
        LoadDataToDb ldb = new LoadDataToDb();
        ldb.loadUserDataToDb();
        ldb.loadMovieDataToDb();
    }
    
    public static void findSimilarities(){
         FindMovieSimilarities fms = new FindMovieSimilarities();
        fms.findMovieSimilarities();
    }

    private static void findMetricForAllBaseAndTest() {
        AllMappings m = AllMappings.getInstance();
        try {
            m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
            for(int i=1;i<=1;i++){
                m.initUserRatings("datafiles//baseandtest//u"+i+".base","datafiles//baseandtest//u"+i+".test"); 
                m.initUsertoMovieCluster();
                m.writeUserToMovieClustersToFile("ARFF");
                m.initUserToUserClusters();
                m.initUserClusterToMovieCluster();
                for (int j = 30; j <= 30; j+=10) {
                    HashMap<Integer,MeasurementMetrics> measurements = m.getMeasurementMetricsForAllTestUsers(j,MappingConstants.MATRIX_RECOMMENDATION, MappingConstants.USER_HISTORY_CLUSTER);
                    String filename = "datafiles//baseandtest//measure//u"+i+"//measure"+j+".csv";
                    PrintWriter pw = new PrintWriter(filename);
                    pw.print("Userid, Precision, Recall");
                    for(int userId: measurements.keySet()){
                        pw.print("\n");
                        pw.print(userId);
                        pw.print(",");
                        pw.print(measurements.get(userId).getPrecision());
                        pw.print(",");
                        pw.print(measurements.get(userId).getRecall());
                        
                    }
                    pw.close();
                }
            }                       
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    private static void findMetricForAllBaseAndTestMatrix() {
        AllMappings m = AllMappings.getInstance();
        try {
            m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
            for(int i=1;i<=5;i++){
                m.initUserRatings("datafiles//baseandtestmatrix//u"+i+".base","datafiles//baseandtestmatrix//u"+i+".test"); 
                m.initUsertoMovieCluster();
                m.writeUserToMovieClustersToFile("ARFF");
                m.initUserToUserClusters();
                m.initUserClusterToMovieCluster();
                for (int j = 10; j <= 50; j+=10) {
                    HashMap<Integer,MeasurementMetrics> measurements = m.getMeasurementMetricsForAllTestUsers(j,MappingConstants.MATRIX_RECOMMENDATION,MappingConstants.USER_HISTORY_CLUSTER);
                    String filename = "datafiles//baseandtestmatrix//measure//u"+i+"//measure"+j+".csv";
                    PrintWriter pw = new PrintWriter(filename);
                    pw.print("Userid, Precision, Recall");
                    for(int userId: measurements.keySet()){
                        pw.print("\n");
                        pw.print(userId);
                        pw.print(",");
                        pw.print(measurements.get(userId).getPrecision());
                        pw.print(",");
                        pw.print(measurements.get(userId).getRecall());
                        
                    }
                    pw.close();
                }
            }                       
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static void clusterMovies() throws IOException{
        double[][] matrix = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
        BufferedReader br = new BufferedReader(new FileReader("datafiles//OverallSimilarities.csv"));
        String line;
        int i = 0;
        while((line = br.readLine())!=null){
            String vals[] = line.split(",");
            for(int j=0;j<vals.length;j++){
                matrix[i][j] = Double.parseDouble(vals[j]);
                
            }
            i++;
        }
        
        DBscan instance = new DBscan(Consts.MAX_MOVIES, matrix);
        
        HashMap<Integer,ClusterPoint> clusters = instance.ClusterUsingDBScan(10, 0.79);
        PrintWriter pw = new PrintWriter("datafiles//MovieClusters.csv");
        if(pw!=null){
            Set<Integer> ks = clusters.keySet();
            for(Integer k:ks){
                pw.write(k+","+clusters.get(k).getClusterNumber()+"\n");

            }
        }
        pw.close();
      
    }
}
