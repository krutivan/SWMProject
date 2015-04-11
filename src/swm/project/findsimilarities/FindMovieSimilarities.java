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
import java.util.List;
import java.util.Set;
import org.bson.Document;
import swm.project.Consts;
import swm.project.loadDataToDb.Operations;

/**
 *
 * @author Kruti
 */
public class FindMovieSimilarities {
    double simActs [][]=new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    
    
    MongoClient mongoClient;
    MongoDatabase db;
   
    
    public FindMovieSimilarities() {
        connectToDb();
    }
    
      
    private void connectToDb(){
        mongoClient = new MongoClient("localhost",27017);
        db=mongoClient.getDatabase(Consts.DBNAME);
    }
    public void findMovieSimilarities(){
        findDistancesForActors();
    }
    
    private void findDistancesForActors(){
         MongoCollection<Document> movieActorsCollection = db.getCollection(Consts.MOVIE_ACTORS_DATA);
         FindIterable<Document> movieActorsAll = movieActorsCollection.find();
        MongoCursor movieActorCursorX = movieActorsAll.iterator();
        
        while(movieActorCursorX.hasNext()){
            Document dX = (Document) movieActorCursorX.next();
            int idX = (int) dX.get("_id");
            List<String> actorsX = (List<String>) dX.get(Consts.MOVIE_ACTORS_DATA);
            MongoCursor movieActorCursorY = movieActorsAll.iterator();
            
            while(movieActorCursorY.hasNext()){
                Document dY = (Document) movieActorCursorY.next();
                int idY = (int) dY.get("_id");
                List<String> actorsY = (List<String>) dY.get(Consts.MOVIE_ACTORS_DATA);
                
                double simActors = Operations.findJaccardDistance(actorsX, actorsY);
                simActs[idX-1][idY-1] = simActors;
                System.out.print(simActors);
                
            }
            System.out.println("");
        }   
    }
    private void findDistancesForGenre(){
        MongoCollection<Document> movieGenreCollection = db.getCollection(Consts.MOVIE_GENRE_DATA);
    }
    private void findDistancesForOtherFeatures(){
         MongoCollection<Document> movieOtherCollection = db.getCollection(Consts.MOVIE_OTHERFEATURES_DATA); 
    }
}
