/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project;
//Sagar's comment on 11th April
/**
 *
 * @author Kruti
 */
public final class Consts {
    public static final String DBNAME= "swmdb";
    public static final String UDATA="uData";
    
    public static final String MOVIE_NAME_DATA = "moviesName";
    public static final String MOVIE_ACTORS_DATA = "movieActors";
    public static final String MOVIE_GENRE_DATA = "movieGenre";
    public static final String MOVIE_OTHERFEATURES_DATA = "movieOther";
    public static final String MOVIE_DIRECTORS_DATA="movieDirectors";
            
    //movie names
    public static final String MOVIE_NAME_FIELD = "movieField";
    public static final String MOVIE_YEAR="movieYear";
    public static final String MOVIE_DIRECTOR="movieDirector";
    public static final String MOVIE_CERT="movieCert";
    //movie Actors
    public static final String MOVIE_ACTORS_FIELD = "movieActors";
    public static final String MOVIE_DATE_DATA="movieDates";
    //counts
    public static final int MAX_MOVIES = 1682;
    //Weights
    public static final double ActorWeight=0.2;
    public static final double DirectorWeight=0.3;
    public static final double GenreWeight=0.5;
    public static final double DateWeight=0.1;
    
    //User attributes
    public static final String USER_AGE="userAge";
    public static final String USER_GENDER="userGender";
    public static final String USER_OCCUPATION="userOccupation";
    public static final String USER_ZIP="userZip";
    
}
