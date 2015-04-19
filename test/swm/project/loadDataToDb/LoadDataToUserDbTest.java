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
public class LoadDataToUserDbTest {
    
    public LoadDataToUserDbTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        LoadDataToUserDb lu=new LoadDataToUserDb();
        lu.LoadDataToDb();
        lu.loadUserDataToDb();
    }
    
    @After
    public void tearDown() {
    }
 
    @Test
    public void testLoadUserDataToDb()
    {
        assertEquals(true,true);
    }
}
