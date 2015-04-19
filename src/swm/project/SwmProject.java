/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import swm.project.findsimilarities.FindMovieSimilarities;
import swm.project.loadDataToDb.LoadDataToDb;
import swm.project.mappings.AllMappings;

/**
 *
 * @author Kruti
 */
public class SwmProject {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //loadAllDataToDb();
        //findSimilarities();
       AllMappings m = AllMappings.getInstance();
        try {
            m.initMovieClusters(Consts.DBSCAN_CLUSTERS);
            m.initUserRatings();
            m.initUsertoMovieCluster();
            m.reccomendMoviesForuser(1, 30);
        } catch (IOException ex) {
            Logger.getLogger(SwmProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
}
