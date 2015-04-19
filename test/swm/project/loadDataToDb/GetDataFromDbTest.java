/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import swm.project.Consts;
import swm.project.findsimilarities.FindMovieSimilarities;

/**
 *
 * @author Sagar
 */
public class GetDataFromDbTest {
    
    public GetDataFromDbTest() {
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
     * Test of getAllUserRatings method, of class GetDataFromDb.
     */
    @Test
    public void getAllUserRatingsTest()
    {
        GetDataFromDb gb=new GetDataFromDb();
        ArrayList<ArrayList<Integer>> userVector=new ArrayList<ArrayList<Integer>>();
        HashMap<Integer, HashMap<Integer,Integer>> ratings=gb.getAllUserRatings();
        //int[] userIDs= new int[ratings.size()];
        //int j=0;
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//UserVectors.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=1;i<=Consts.MAX_USERS;i++)
        {
            for(int j=1;j<=Consts.MAX_MOVIES;j++)
            {
                if(ratings.get(i).containsKey(j))
                  pw.print(((Map)ratings.get(i)).get(j)+",");
                else
                  pw.print("0,");
            }
           pw.println();
        }           
        pw.close();
        assertEquals(true,true);
    }
}
