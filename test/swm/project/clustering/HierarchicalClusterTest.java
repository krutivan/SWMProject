/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.clustering;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import swm.project.Consts;

/**
 *
 * @author Kruti
 */
public class HierarchicalClusterTest {
     double[][] matrix = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
     
    public HierarchicalClusterTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws IOException {
           
    }
    
    @After
    public void tearDown() {
    }
    @Test
    public void ClusterUsingHCTest() throws IOException{
        double[][] matrix = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
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
        
        HeirarchicalClustering instance = new HeirarchicalClustering(Consts.MAX_MOVIES, matrix);
        
        
        com.apporiented.algorithm.clustering.Cluster Cluster = instance.ClusterUsingHC();  
        com.apporiented.algorithm.clustering.visualization.DendrogramPanel dp = new com.apporiented.algorithm.clustering.visualization.DendrogramPanel();
        //dp.setModel(Cluster);
        //Cluster.toConsole(0);
        /*PrintWriter pw = new PrintWriter("datafiles//MovieClustersHierarchy.csv");
        if(pw!=null){
            List<com.apporiented.algorithm.clustering.Cluster> ks = Cluster.getChildren();//clusters.keySet();
            
            for (int j = 0; j < ks.size(); j++) {
		pw.write(j+","+ks.get(j).getName()+"\n");
                List<com.apporiented.algorithm.clustering.Cluster> ks1 = ks.get(j).getChildren();
                
		}
        }
        pw.close();*/
    JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.setLocation(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel content = new JPanel();
        

        frame.setContentPane(content);
        content.setBackground(Color.red);
        content.setLayout(new BorderLayout());
        content.add(dp, BorderLayout.CENTER);
        dp.setBackground(Color.WHITE);
        dp.setLineColor(Color.BLACK);
        dp.setScaleValueDecimals(0);
        dp.setScaleValueInterval(1);
        dp.setShowDistances(false);

        
        dp.setModel(Cluster);
        frame.setVisible(true);
        System.err.println("");
    }
}
