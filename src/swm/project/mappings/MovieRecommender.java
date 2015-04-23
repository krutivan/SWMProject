/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swm.project.mappings;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Kruti
 */
public class MovieRecommender {
    public List<Integer> getNMovies(int N,int userId, List<UserVote> movieClusterVotes){
        
        int numOfMovs =0;
        List<Integer> recommendedMovieIds = new ArrayList<>();
        AllMappings m = AllMappings.getInstance();
        Set<Integer> userHistory = m.userToMovieRatings.getAllRatedMoviesForBaseUser(userId).keySet();
        
        LinkedList<UserVote> clusters = new LinkedList<>( movieClusterVotes);
        
        clusters.sort(new Comparator<UserVote>() {

            @Override
            public int compare(UserVote o1, UserVote o2) {
                if(o1.getProb() < o2.getProb())
                    return -1;
                return 1;
            }
        }
        );
        
        UserVote cluster = clusters.removeFirst();
        
        while(cluster!=null && numOfMovs< N){
            ArrayList<Integer> movsInCluster = new ArrayList<>(m.movieToMovieCluster.getMoviesInCluster(cluster.getClusterId()));
           for(int mov: movsInCluster){
               if(!userHistory.contains(mov)){
                   recommendedMovieIds.add(mov);
                   numOfMovs++;
               }
           }
           if(clusters.size()>0)
            cluster=clusters.removeFirst();
           else
               cluster=null;
            
            
        }

        return recommendedMovieIds;
        
    }
}
