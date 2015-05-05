/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import swm.project.Consts;

/**
 *
 * @author Kruti
 */
 class UserToMovieRatings {
   private HashMap<Integer, HashMap<Integer,Integer>> base;
   private HashMap<Integer, HashMap<Integer,Integer>> test;
    
   public void setUserRatingsAll() throws FileNotFoundException, IOException{
        base = new HashMap<>();
      BufferedReader br = new BufferedReader(new FileReader(Consts.USER_RATING_FILE));
            String inputLine;
            
            while((inputLine = br.readLine())!=null)
            {
                String [] fields = inputLine.split("\t");
                int userId = Integer.parseInt(fields[0]);
                int movId = Integer.parseInt(fields[1]);
                int rating = Integer.parseInt(fields[2]);
                HashMap<Integer,Integer> movRatings;
                if(base.containsKey(userId))
                    movRatings = base.get(userId);
                else
                    movRatings=new HashMap<>();

                movRatings.put(movId, rating);
                base.put(userId, movRatings);
                
            }
        
   }
   
   /**
    * loads userRatings as well as userRatingsTest
    * @param baseFile base file from which to read user movie Ratings
    * @param testFile test file
    */
   public void setUserRatingsBaseAndTest(String baseFile, String testFile) throws FileNotFoundException, IOException{
       base = new HashMap<>();
       test=new  HashMap<>();
       BufferedReader br = new BufferedReader(new FileReader(baseFile));
        String line;
        int userId,movId,rating;
        while((line = br.readLine())!=null){
            String vals[] = line.split("\t");
            userId = Integer.parseInt(vals[0]);
            movId = Integer.parseInt(vals[1]);
            rating = Integer.parseInt(vals[2]);
          
            HashMap<Integer,Integer> movRatings;
            if(base.containsKey(userId))
                movRatings = base.get(userId);
            else
                movRatings=new HashMap<>();
            
            movRatings.put(movId, rating);
            base.put(userId, movRatings);
        }
        
        br = new BufferedReader(new FileReader(testFile));
        
        while((line = br.readLine())!=null){
            String vals[] = line.split("\t");
            userId = Integer.parseInt(vals[0]);
            movId = Integer.parseInt(vals[1]);
            rating = Integer.parseInt(vals[2]);
          
            HashMap<Integer,Integer> movRatings;
            if(test.containsKey(userId))
                movRatings = test.get(userId);
            else
                movRatings=new HashMap<>();
            
            movRatings.put(movId, rating);
            test.put(userId, movRatings);
        }
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
   
   public Set<Integer> getAllTestUsers(){
       return test.keySet();
   }
   
   
}
