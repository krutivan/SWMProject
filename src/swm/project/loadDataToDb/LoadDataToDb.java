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
import org.bson.Document;
import swm.project.Consts;

/**
 * @author Kruti
 */
public class LoadDataToDb {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;
    
    public void connectToDb(){
        mongoClient = new MongoClient("localhost",27017);
        db=mongoClient.getDatabase(Consts.DBNAME);
        
        db.createCollection(Consts.UDATA);
        collection = db.getCollection(Consts.UDATA);
    }
    
    
    /**
     * Call this function to load user data from u.data in db
     */
    public void loadUserDataToDb() throws NullPointerException{
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
                d.put("rating",Integer.parseInt(fields[2]));
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
} 
