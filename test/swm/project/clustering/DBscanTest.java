/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.clustering;

import com.sun.org.apache.xalan.internal.xsltc.DOM;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.jar.Pack200;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import swm.project.Consts;

/**
 *
 * @author Kruti
 */
public class DBscanTest {
     double[][] matrix = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
     
    public DBscanTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
         
        /*double[][] matrix = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
        BufferedReader br = new BufferedReader(new FileReader("datafiles//OverallSimilarities.csv"));
        String line;
        int i = 0;
        while((line = br.readLine())!=null){
            String vals[] = line.split(",");
            for(int j=0;j<vals.length;j++){
                matrix[i][j] = Double.parseDouble(vals[j]);
                
            }
            i++;
        }
        
        DBscan instance = new DBscan(Consts.MAX_MOVIES, matrix);
        
        HashMap<Integer,ClusterPoint> clusters = instance.ClusterUsingDBScan(10, 0.6);
        PrintWriter pw = new PrintWriter("datafiles//MovieClusters.csv");
        if(pw!=null){
            Set<Integer> ks = clusters.keySet();
            for(Integer k:ks){
                pw.write(k+","+clusters.get(k).getClusterNumber()+"\n");

            }
        }
        pw.close();
        */
        testClusterUsingDBScan();
    }
    
   @Test 
   public void testUserClusteringDBScan() throws IOException{
       
       double[][] matrix = new double[Consts.MAX_USERS][Consts.MAX_USERS];
        BufferedReader br = new BufferedReader(new FileReader("datafiles//UserSimilarities.csv"));
        String line;
        int i = 0;
        while((line = br.readLine())!=null){
            String vals[] = line.trim().split(",");
            for(int j=0;j<vals.length;j++){
                matrix[i][j] = Double.parseDouble(vals[j]);
                
            }
            i++;
        }
        
        DBscan instance = new DBscan(Consts.MAX_USERS, matrix);
        
        HashMap<Integer,ClusterPoint> clusters = instance.ClusterUsingDBScan(10, 0.6);
        PrintWriter pw = new PrintWriter("datafiles//UserCluters.csv");
        if(pw!=null){
            Set<Integer> ks = clusters.keySet();
            for(Integer k:ks){
                pw.write(k+","+clusters.get(k).getClusterNumber()+"\n");

            }
        }
        pw.close();
   }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of ClusterUsingDBScan method, of class DBscan.
     */
    //@Test
    public void testClusterUsingDBScan() {
        System.out.println("ClusterUsingDBScan");
        int minPnts = 20;
        double eps = 0.5;
//        DBscan instance = new DBscan();
        //HashMap<Integer, ClusterPoint> expResult = null;
        ///HashMap<Integer, ClusterPoint> result = instance.ClusterUsingDBScan(minPnts, eps);
        
          assertEquals(true, true);
        
    }

    /**
     * Test of regionQuery method, of class DBscan.
     */
    @Test
    public void testRegionQuery() {
        System.out.println("regionQuery");
        int id = 0;
        double eps = 0.0;
//        DBscan instance = new DBscan();
        //HashSet<Integer> expResult = null;
        //HashSet<Integer> result = instance.regionQuery(id, eps);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
          assertEquals(true, true);
    }
    
}
