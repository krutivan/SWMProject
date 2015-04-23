/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.loadDataToDb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bson.Document;
import swm.project.Consts;
import org.bson.types.ObjectId;

/**
 *
 * @author Kruti
 */
public class GetDataFromDb {
    MongoClient mongoClient;
    MongoDatabase db;
    
    
    public GetDataFromDb() {
        connectToDb();
    }
    
      
    private void connectToDb(){
        mongoClient = new MongoClient("localhost",27017);
        db=mongoClient.getDatabase(Consts.DBNAME);
    }
    
    /**
     * 
     * @return 
     * returns : HashMap<Integer, HashMap<Integer, Integer>>
     * i.e. HashMap<userid, HashMap<movieId, rating>>
     */
    public HashMap<Integer, HashMap<Integer,Integer>> getAllUserRatings()
    {
        MongoCollection<Document> collection;
        collection = db.getCollection(Consts.UDATA);
        HashMap<Integer, HashMap<Integer,Integer>> userRatings = new HashMap<>();
        FindIterable<Document> userRatingData = collection.find();
        MongoCursor<Document> userRatingCursor = userRatingData.iterator();
        while(userRatingCursor.hasNext())
        {
            Document ratingFields = userRatingCursor.next();
            int userId= (int) ratingFields.get("userid");
            int movieId = (int)ratingFields.get("itemid");
            int rating = (int) ratingFields.get("rating");
            
            if(userRatings.containsKey(userId)){
                HashMap<Integer,Integer> itemRatings = userRatings.get(userId);
                itemRatings.put(movieId, rating);
            }
            else{
                    HashMap<Integer,Integer> itemRatings = new HashMap<Integer, Integer>();
                    itemRatings.put(movieId, rating);
                    userRatings.put(userId, itemRatings);
            }       
        }
        return userRatings;
    }
    
    public List<String> getMovieDetails(List<Integer> movieID)
    {
        MongoCollection<Document> movieNamesCollection = db.getCollection(Consts.MOVIE_NAME_DATA);
         //FindIterable<Document> movieNamesAll = movieNamesCollection.find();
         //MongoCursor movieNamesCursorX = movieNamesAll.iterator();
         ArrayList<String> names = new ArrayList<>();
         BasicDBObject whereQuery = new BasicDBObject();
         for(int i=0;i<movieID.size();i++)
         {
            whereQuery.put("_id", movieID.get(i));
             FindIterable<Document> iterate = movieNamesCollection.find(whereQuery);
         MongoCursor cursor = iterate.iterator();
	 while(cursor.hasNext()) {
	    Document ratingFields = (Document) cursor.next();
            String movieName= (String) ratingFields.get(Consts.MOVIE_NAME_FIELD);
            //System.out.println(movieName);          
            names.add(movieName);
	}
         }
	return names;
    }
}
