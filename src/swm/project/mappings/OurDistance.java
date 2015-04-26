/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import weka.core.Attribute;
import weka.core.DistanceFunction;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Option;
import weka.core.neighboursearch.PerformanceStats;

/**
 *
 * @author Kruti
 */
public class OurDistance implements DistanceFunction{

    @Override
    public void setInstances(Instances i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Instances getInstances() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setAttributeIndices(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAttributeIndices() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setInvertSelection(boolean bln) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean getInvertSelection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double distance(Instance instnc, Instance instnc1) {
        
        int num =instnc.numAttributes();
        List<Double> movieClusterRating1=new ArrayList<Double>(), movieClusterRating2=new ArrayList<Double>();
                
        Attribute id = instnc.attribute(0);
        for(int index=1; index< num;index++){
            
        }
        return 1;
    }

    @Override
    public double distance(Instance instnc, Instance instnc1, PerformanceStats ps) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double distance(Instance instnc, Instance instnc1, double d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double distance(Instance instnc, Instance instnc1, double d, PerformanceStats ps) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void postProcessDistances(double[] doubles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Instance instnc) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clean() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Enumeration<Option> listOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setOptions(String[] strings) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getOptions() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
