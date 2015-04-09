/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;

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
        LoadDataToDb ldb = new LoadDataToDb();
        ldb.connectToDb();
        ldb.display();
        
    }
    
}
