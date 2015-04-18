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
import swm.project.loadDataToDb.GetDataFromDb;
import swm.project.loadDataToDb.LoadDataToDb;

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
        CreateMappings m =new CreateMappings();
        try {
            m.createMovieToMovieClusters();
        } catch (IOException ex) {
            Logger.getLogger(SwmProject.class.getName()).log(Level.SEVERE, null, ex);
        }
        m.createUserToMovieCluster();
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
