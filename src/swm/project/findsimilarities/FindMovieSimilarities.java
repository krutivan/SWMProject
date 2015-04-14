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
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import swm.project.Consts;
import swm.project.loadDataToDb.Operations;

/**
 *
 * @author Kruti
 */
public class FindMovieSimilarities {
    double simActs [][]=new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    double simDirects[][]=new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    double simGenres[][]= new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    double simDates[][]= new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    double finalSim[][] = new double[Consts.MAX_MOVIES][Consts.MAX_MOVIES];
    
    //finalSim[x][y] = ActorWeight* simAct[x][y] + DirectorWeight * SimDirects[x][y]...
    //sum of all weights = 1;
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
        //findDistancesForActors();
        //findDistancesForDirectors();
        //findDistancesForGenre();
        findDistancesForDates();
    }
    
    private void findDistancesForActors(){
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//Similarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
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
                //System.out.print(simActors+",");
                pw.print(simActors+",");
                
            }
           // System.out.println("");
            pw.println();
        }   
    }
    
    private void findDistancesForDirectors(){
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//DirectorSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
         MongoCollection<Document> movieDirectorsCollection = db.getCollection(Consts.MOVIE_DIRECTORS_DATA);
         FindIterable<Document> movieDirectorsAll = movieDirectorsCollection.find();
         MongoCursor movieDirectorsCursorX = movieDirectorsAll.iterator();
        
        while(movieDirectorsCursorX.hasNext()){
            Document dX = (Document) movieDirectorsCursorX.next();
            int idX = (int) dX.get("_id");
            List<String> directorsX = (List<String>) dX.get(Consts.MOVIE_DIRECTORS_DATA);
            MongoCursor movieDirectorsCursorY = movieDirectorsAll.iterator();
            
            while(movieDirectorsCursorY.hasNext()){
                Document dY = (Document) movieDirectorsCursorY.next();
                int idY = (int) dY.get("_id");
                List<String> directorsY = (List<String>) dY.get(Consts.MOVIE_DIRECTORS_DATA);
                
                double simDirectors = Operations.findJaccardDistance(directorsX, directorsY);
                simDirects[idX-1][idY-1] = simDirectors;
                //System.out.print(simDirectors+",");
                pw.print(simDirectors+",");
                
            }
           // System.out.println("");
            pw.println();
        }   
    }
    
    private void findDistancesForGenre(){
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//GenreSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
        MongoCollection<Document> movieGenreCollection = db.getCollection(Consts.MOVIE_GENRE_DATA);
        FindIterable<Document> movieGenreAll = movieGenreCollection.find();
        MongoCursor movieGenreCursorX = movieGenreAll.iterator();
        
        while(movieGenreCursorX.hasNext()){
            Document dX = (Document) movieGenreCursorX.next();
            int idX = (int) dX.get("_id");
            String genreX = (String) dX.get(Consts.MOVIE_GENRE_DATA);
            MongoCursor movieGenreCursorY = movieGenreAll.iterator();
            
            while(movieGenreCursorY.hasNext()){
                Document dY = (Document) movieGenreCursorY.next();
                int idY = (int) dY.get("_id");
                String genreY = (String) dY.get(Consts.MOVIE_GENRE_DATA);
                
                double similarGenres = Operations.findBinaryJaccardDistance(genreX, genreY);
                simGenres[idX-1][idY-1] = similarGenres;
                System.out.print(similarGenres+",");
                pw.print(similarGenres+",");       
            }
           // System.out.println("");
            pw.println();
        }
    }
    
    private void findDistancesForDates()
    {
        PrintWriter pw = null;
        try {
            
            pw = new PrintWriter("datafiles//DateSimilarities.csv");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FindMovieSimilarities.class.getName()).log(Level.SEVERE, null, ex);
        }
        MongoCollection<Document> movieDateCollection = db.getCollection(Consts.MOVIE_DATE_DATA);
        FindIterable<Document> movieDateAll = movieDateCollection.find();
        MongoCursor movieDateCursorX = movieDateAll.iterator();
        
        while(movieDateCursorX.hasNext()){
            Document dX = (Document) movieDateCursorX.next();
            int idX = (int) dX.get("_id");
            int dateX = (int)dX.get(Consts.MOVIE_DATE_DATA);
            MongoCursor movieDateCursorY = movieDateAll.iterator();
            
            while(movieDateCursorY.hasNext()){
                Document dY = (Document) movieDateCursorY.next();
                int idY = (int) dY.get("_id");
                int dateY = (int) dY.get(Consts.MOVIE_DATE_DATA);
                double similarDates = Operations.findSimilarDates(dateX, dateY);
                simDates[idX-1][idY-1] = similarDates;
                System.out.print(similarDates+",");
                pw.print(similarDates+",");       
            }
           // System.out.println("");
            pw.println();
        }
        pw.close();
    }
    
    private void findDistancesForOtherFeatures(){
         MongoCollection<Document> movieOtherCollection = db.getCollection(Consts.MOVIE_OTHERFEATURES_DATA); 
    }
}
