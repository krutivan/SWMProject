/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import swm.project.findsimilarities.FindMovieSimilarities;
import swm.project.loadDataToDb.LoadDataToDb;
import swm.project.mappings.AllMappings;
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
       // findSimilarities();
        //findMetricForAllBaseAndTest();
        
      
        AllMappings m = AllMappings.getInstance();
        m.initUserClustersFromMovieClusters();
//        m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
//        m.initUserRatings("datafiles//baseandtest//u1.base","datafiles//baseandtest//u1.test");
//        m.initUsertoMovieCluster();
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
                m.writeUserToMovieClustersToFile();
                m.initUserClustersFromMovieClusters();
                for (int j = 50; j <= 50; j+=10) {
                    HashMap<Integer,MeasurementMetrics> measurements = m.getMeasurementMetricsForAllTestUsers(j);
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
}
