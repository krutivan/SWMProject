/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Sagar
 */
public class LoadDataToDbTest {
    
    public LoadDataToDbTest() {
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
     * Test of loadUserDataToDb method, of class LoadDataToDb.
     */
    @Test
    public void testLoadUserDataToDb() {
        System.out.println("loadUserDataToDb");
        LoadDataToDb instance = new LoadDataToDb();
        instance.loadUserDataToDb();
        // TODO review the generated test code and remove the default call to fail.
    }

    /**
     * Test of loadMovieDataToDb method, of class LoadDataToDb.
     */
    @Test
    public void testLoadMovieDataToDb() {
        System.out.println("loadMovieDataToDb");
        LoadDataToDb instance = new LoadDataToDb();
        instance.loadMovieDataToDb();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dropCollections method, of class LoadDataToDb.
     */
    @Test
    public void testDropCollections() {
        System.out.println("dropCollections");
        String[] collectionsToDrop = null;
        LoadDataToDb instance = new LoadDataToDb();
        instance.dropCollections(collectionsToDrop);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
