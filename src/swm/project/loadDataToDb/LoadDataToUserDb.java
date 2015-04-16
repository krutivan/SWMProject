/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import swm.project.Consts;

/**
 *
 * @author Sagar
 */
public class LoadDataToUserDb {
    

    MongoClient mongoClient;
    MongoDatabase db;
    
    
    public void LoadDataToDb() {
        connectToDb();
    }
    
      
    private void connectToDb(){
        mongoClient = new MongoClient("localhost",27017);
        db=mongoClient.getDatabase(Consts.DBNAME);
    }

  
    
    /**
     * Call this function to load user data from u.data in db
     */
    public void loadUserDataToDb() throws NullPointerException{
        String [] collectionsToDrop = {Consts.USER_AGE,Consts.USER_GENDER,Consts.USER_OCCUPATION,Consts.USER_ZIP};
           
            dropCollections(collectionsToDrop);
        /*    
        MongoCollection<Document> collection;
        collection = db.getCollection(Consts.UDATA);
        collection.dropCollection();
        db.createCollection(Consts.UDATA);
                */
            
         db.createCollection(Consts.USER_AGE);
            MongoCollection<Document> userAgeCollection = db.getCollection(Consts.USER_AGE);
         db.createCollection(Consts.USER_GENDER);
            MongoCollection<Document> userGenderCollection = db.getCollection(Consts.USER_GENDER);
         db.createCollection(Consts.USER_OCCUPATION);
            MongoCollection<Document> userOccupationCollection = db.getCollection(Consts.USER_OCCUPATION);
         db.createCollection(Consts.USER_ZIP);
            MongoCollection<Document> userZipCollection = db.getCollection(Consts.USER_ZIP);   
            
        FileInputStream fis=null;
        BufferedReader br;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream("datafiles//u.user");
            br = new BufferedReader(new InputStreamReader(fis));
            String inputLine;
            int c=0;
            while((inputLine = br.readLine())!=null)
            {
                inputLine.trim();
                String [] fields = inputLine.split(",");
               // System.out.println(fields[0]);
                addUserAge(c,Integer.parseInt(fields[1]),userAgeCollection);
                addUserGender(c,fields[2],userGenderCollection);
                addUserOccupation(c,fields[3],userOccupationCollection);
                addUserZip(c,Integer.parseInt(fields[4]),userZipCollection);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("u.data file not found"+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("u.data IO exception"+ex.getMessage());
        }
    }
    
    private void addUserAge(int id, int age, MongoCollection<Document> userAgeCollection)
    {
        Document d = new Document();  
        d.put("_id", id);
        d.put(Consts.USER_AGE, age);
        userAgeCollection.insertOne(d);
    }
    
    private void addUserGender(int id, String gender, MongoCollection<Document> userGenderCollection)
    {
        Document d = new Document();  
        d.put("_id", id);
        d.put(Consts.USER_GENDER, gender);
        userGenderCollection.insertOne(d);
    }
    
    private void addUserOccupation(int id, String occupation, MongoCollection<Document> userOccupationCollection)
    {
        Document d = new Document();  
        d.put("_id", id);
        d.put(Consts.USER_OCCUPATION, occupation);
        userOccupationCollection.insertOne(d);
    }
    
    private void addUserZip(int id, int age, MongoCollection<Document> userZipCollection)
    {
        Document d = new Document();  
        d.put("_id", id);
        d.put(Consts.USER_ZIP, age);
        userZipCollection.insertOne(d);
    }
    
     public void dropCollections(String[] collectionsToDrop){
       int counter=0;
       for(String collection:collectionsToDrop)
      {
          db.getCollection(collection).dropCollection();
          System.out.println(collection);
      }
    }
}
  
    
 