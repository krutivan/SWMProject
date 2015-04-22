/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.findsimilarities;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import swm.project.Consts;
import swm.project.loadDataToDb.Operations;

/**
 *
 * @author Sagar
 */
public class FindUserSimilarities {
    double simAge [][]=new double[Consts.MAX_USERS][Consts.MAX_USERS];
    int simGender [][]=new int[Consts.MAX_USERS][Consts.MAX_USERS];
    int simOccupation [][]=new int[Consts.MAX_USERS][Consts.MAX_USERS];
    double simZip [][]=new double[Consts.MAX_USERS][Consts.MAX_USERS];
    double finalSim[][]=new double[Consts.MAX_USERS][Consts.MAX_USERS];
    MongoClient mongoClient;
    MongoDatabase db;
    
    public FindUserSimilarities()
    {
        connectToDb();
    }
    
    private void connectToDb(){
        mongoClient = new MongoClient("localhost",27017);
        db=mongoClient.getDatabase(Consts.DBNAME);
    }
    
    public void findUserSimilarities()
    {
        findDistancesForGender();
        //findDistancesForOccupation();
        findDistancesForAge();
        findOverallSimilarities();
    }
    
    private void findDistancesForGender()
    {
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//GenderSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
         MongoCollection<Document> userGenderCollection = db.getCollection(Consts.USER_GENDER);
         FindIterable<Document> userGenderAll = userGenderCollection.find();
        MongoCursor userGenderCursorX = userGenderAll.iterator();
        
        while(userGenderCursorX.hasNext()){
            Document dX = (Document) userGenderCursorX.next();
            int idX = (int) dX.get("_id");
            String genderX =   (String) dX.get(Consts.USER_GENDER);
            MongoCursor userGenderCursorY = userGenderAll.iterator();
            
            while(userGenderCursorY.hasNext()){
                Document dY = (Document) userGenderCursorY.next();
                int idY = (int) dY.get("_id");
                String genderY = (String) dY.get(Consts.USER_GENDER);
                
                int simGenders = Operations.findSimilar(genderX, genderY);
                simGender[idX-1][idY-1] = simGenders;
                System.out.print(simGenders+",");
                pw.print(simGenders+",");
                
            }
           // System.out.println("");
            pw.println();
        }   
        pw.close();
    }
    
    private void findDistancesForOccupation()
    {
         PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//OccupationSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
         MongoCollection<Document> userOccupationCollection = db.getCollection(Consts.USER_OCCUPATION);
         FindIterable<Document> userOccupationAll = userOccupationCollection.find();
        MongoCursor userOccupationCursorX = userOccupationAll.iterator();
        
        while(userOccupationCursorX.hasNext()){
            Document dX = (Document) userOccupationCursorX.next();
            int idX = (int) dX.get("_id");
            String occupationX =   (String) dX.get(Consts.USER_OCCUPATION);
            MongoCursor userOccupationCursorY = userOccupationAll.iterator();
            
            while(userOccupationCursorY.hasNext()){
                Document dY = (Document) userOccupationCursorY.next();
                int idY = (int) dY.get("_id");
                String occupationY = (String) dY.get(Consts.USER_OCCUPATION);
                
                int simOccupations = Operations.findSimilar(occupationX, occupationY);
                simOccupation[idX-1][idY-1] = simOccupations;
                System.out.print(simOccupations+",");
                pw.print(simOccupations+",");
                
            }
           // System.out.println("");
            pw.println();
        }   
        pw.close();
    }
    
    private void findDistancesForAge()
    {
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//AgeSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
         MongoCollection<Document> userAgeCollection = db.getCollection(Consts.USER_AGE);
         FindIterable<Document> userAgeAll = userAgeCollection.find();
        MongoCursor userAgeCursorX = userAgeAll.iterator();
        
        while(userAgeCursorX.hasNext()){
            Document dX = (Document) userAgeCursorX.next();
            int idX = (int) dX.get("_id");
            int ageX = (Integer) dX.get(Consts.USER_AGE);
            MongoCursor userAgeCursorY = userAgeAll.iterator();
            
            while(userAgeCursorY.hasNext()){
                Document dY = (Document) userAgeCursorY.next();
                int idY = (int) dY.get("_id");
                int ageY = (Integer)dY.get(Consts.USER_AGE);
                
                double simAges = Operations.findSimilarAges(ageX, ageY);
                simAge[idX-1][idY-1] = simAges;
                //System.out.print(simAges+",");
                pw.print(simAges+",");         
            }
           // System.out.println("");
            pw.println();
        }   
        pw.close();
    }
    
    private void findOverallSimilarities()
    {
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//UserAgeGenderSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(int i=0;i<Consts.MAX_USERS;i++)
        {
            for(int j=0;j<Consts.MAX_USERS;j++)
            {
                finalSim[i][j] = Consts.AgeWeight* simAge[i][j] + Consts.GenderWeight * simGender[i][j];
                pw.print(finalSim[i][j]+",");
            }
            pw.println();
        }
        pw.close();
    }
}
