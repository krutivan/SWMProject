/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Kruti
 */
public class UserClusterToMovieCluster {
    HashMap<Integer, FinalScores> uClustToMClustProfile;
    
    HashMap<Integer, FinalScores> uClustToMClustHistory;

    public UserClusterToMovieCluster() {
        
        initMatrixFromHistory();
        initMatrixFromProfile();
                
    }
    
    private void initMatrixFromHistory(){
        AllMappings m = AllMappings.getInstance();
        uClustToMClustHistory = new HashMap<>();
       
         
        
        for(int userClusterNumber: m.userToUserCluster.userClustersToUsersHistory.keySet()){
            
          FinalScores fs = new FinalScores(userClusterNumber, MappingConstants.USER_HISTORY_CLUSTER);
          fs.calculateAllScores();
          uClustToMClustHistory.put(userClusterNumber, fs);
           
        }
        System.err.println("");
        
    }

  
    private void initMatrixFromProfile(){
        AllMappings m = AllMappings.getInstance();
        uClustToMClustProfile = new HashMap<>();
       for(int userClusterNumber: m.userToUserCluster.userClustersToUsersProfile.keySet()){
          FinalScores fs = new FinalScores(userClusterNumber, MappingConstants.USER_PROFILE_CLUSTER);
          fs.calculateAllScores();
          uClustToMClustProfile.put(userClusterNumber, fs);
        }
    }
    
    public FinalScores getFinalScoresForUserCluster(int userClusterNumber, int UserClusterType){
        if(UserClusterType == MappingConstants.USER_HISTORY_CLUSTER)
            return uClustToMClustHistory.get(userClusterNumber);
        else
            return uClustToMClustProfile.get(userClusterNumber);
    }
            
}
