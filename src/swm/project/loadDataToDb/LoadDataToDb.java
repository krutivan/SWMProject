/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.bson.Document;
import swm.project.Consts;

/**
 * @author Kruti
 */
public class LoadDataToDb {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;
    
    public LoadDataToDb() {
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
        
        collection = db.getCollection(Consts.UDATA);
        collection.dropCollection();
        db.createCollection(Consts.UDATA);
        
        connectToDb();
        FileInputStream fis=null;
        BufferedReader br;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream("datafiles//u.data");
            br = new BufferedReader(new InputStreamReader(fis));
            String inputLine;
            
            while((inputLine = br.readLine())!=null)
            {
                String [] fields = inputLine.split("\t");
               // System.out.println(fields[0]);
                Document d = new Document();
                d.put("userid", Integer.parseInt(fields[0]));
                d.put("itemid", Integer.parseInt(fields[1]));
                d.put("rating", Integer.parseInt(fields[2]));
                d.put("timestamp", fields[3]);
                collection.insertOne(d);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("u.data file not found"+ex.getMessage());
        } catch (IOException ex) {
            System.out.println("u.data IO exception"+ex.getMessage());
        }
        if(fis == null )
            throw new NullPointerException("u.data file does not exist!!!");
    }
    
    /**
     * Call this function to load all movie data to the DB
     */
    
    
    public void loadMovieDataToDb(){
        //dropCollections(new HashSet<String>());
                
    }
    
    public void dropCollections(Set<String> collectionsToDrop){
        MongoIterable<String> collectionNames = db.listCollectionNames();
       
        String colName = collectionNames.first();
        while(colName!=null)
        {
            if(collectionsToDrop.contains(colName)){
                collection = db.getCollection(colName);
                collection.dropCollection();
            }
        }
    }
} 
