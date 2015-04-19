/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.HashMap;
import java.util.Set;
import swm.project.loadDataToDb.GetDataFromDb;

/**
 *
 * @author Kruti
 */
 class UserToMovieRatings {
   HashMap<Integer, HashMap<Integer,Integer>> base;
   HashMap<Integer, HashMap<Integer,Integer>> test;
    
   public void setUserRatingsAll(){
       GetDataFromDb gdb = new GetDataFromDb();
        base = gdb.getAllUserRatings();
   }
   
   /**
    * loads userRatings as well as userRatingsTest
    * @param baseFile base file from which to read user movie Ratings
    * @param testFile test file
    */
   public void setUserRatingsBaseAndTest(String baseFile, String testFile){
       
   }
   
   public HashMap<Integer,Integer> getAllRatedMoviesForBaseUser(int userId){
       if(base!= null)
           return base.get(userId);
     return null;
   }
   
   public HashMap<Integer,Integer> getAllRatedMoviesFortestUser(int userId) throws Exception{
       if(test!= null)
           return test.get(userId);
       else 
           throw new Exception("userRatings not initialized. Make sure setUserRatings are called...");
   }
   
   public Set<Integer> getAllBaseUsers(){
       return base.keySet();
   }
   
   
   
   
}
