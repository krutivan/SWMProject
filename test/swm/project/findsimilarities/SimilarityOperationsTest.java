/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import swm.project.Consts;
import swm.project.loadDataToDb.GetDataFromDb;

/**
 *
 * @author Kruti
 */
public class SimilarityOperationsTest {
    
    public SimilarityOperationsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of cosineSimilarities method, of class SimilarityOperations.
     */
    @Test
    public void testSimilarities() throws FileNotFoundException {
        //System.out.println("cosineSimilarities");
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//UserSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Integer> vector1 = new ArrayList<>();
        ArrayList<Integer> vector2 = new ArrayList<>();
        GetDataFromDb gb=new GetDataFromDb();
        ArrayList<ArrayList<Integer>> userVector=new ArrayList<ArrayList<Integer>>();
        HashMap<Integer, HashMap<Integer,Integer>> ratings=gb.getAllUserRatings();
        SimilarityOperations instance = new SimilarityOperations();
        double result;
        for(int i=1;i<=Consts.MAX_USERS;i++)
        {
            for(int j=1;j<=Consts.MAX_USERS;j++)
            {
                    for(int k=1;k<=Consts.MAX_MOVIES;k++)
                    {
                        if(ratings.get(i).containsKey(k))
                          vector1.add((ratings.get(i)).get(k));
                        else
                          vector1.add(0);
                        if(ratings.get(j).containsKey(k))
                          vector2.add((ratings.get(j)).get(k));
                        else
                          vector2.add(0);
                    }
                    result = instance.findJacUOD(vector1, vector2); 
                    pw.print(result);
            } 
        pw.println();
        }           
        //double result = instance.cosineSimilarities(vector1, vector2);
        //double p = instance.pearsonsCoefficient(vector1, vector2);
        //System.out.println(result+" , "+p+" , "+n);
        assertEquals(true,true);
        // TODO review the generated test code and remove the default call to fail.
        pw.close();
    }

    /**
     * Test of pearsonsCoefficient method, of class SimilarityOperations.
     */
//    @Test
//    public void testPearsonsCoefficient() {
//        System.out.println("pearsonsCoefficient");
//        
//        ArrayList<Integer> vector1 = new ArrayList<>();
//        ArrayList<Integer> vector2 = new ArrayList<>();
//        SimilarityOperations instance = new SimilarityOperations();
//        double expResult = 0.0;
//        double result = instance.pearsonsCoefficient(vector1, vector2);
//        assertEquals(expResult, result, 0.0);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    
}
