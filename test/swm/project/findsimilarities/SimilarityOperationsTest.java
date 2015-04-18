/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testSimilarities() {
        //System.out.println("cosineSimilarities");
        ArrayList<Double> vector1 = new ArrayList<>();
        ArrayList<Double> vector2 = new ArrayList<>();
        vector1.add(2.0);
        vector1.add(1.0);
        vector1.add(1.0);
        vector1.add(1.0);
        vector1.add(0.0);

        vector2.add(2.0);
        vector2.add(4.0);
        vector2.add(2.0);
        vector2.add(4.0);
        vector2.add(5.0);
        

        SimilarityOperations instance = new SimilarityOperations();
        double expResult = 9.938;
        //double result = instance.cosineSimilarities(vector1, vector2);
        //double p = instance.pearsonsCoefficient(vector1, vector2);
        double result = instance.findJacUOD(vector1, vector2);
        //System.out.println(result+" , "+p+" , "+n);
        System.out.println(result);
        assertEquals(expResult, result, 0.001);
        // TODO review the generated test code and remove the default call to fail.
        
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
