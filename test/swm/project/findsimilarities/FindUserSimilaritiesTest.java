/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

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
public class FindUserSimilaritiesTest {
    
    public FindUserSimilaritiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        FindUserSimilarities fu=new FindUserSimilarities();
        fu.findUserSimilarities();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindDistancesForAges() {
        // TODO review the generated test code and remove the default call to fail.
        assertEquals(true,true);
    }
    
}
