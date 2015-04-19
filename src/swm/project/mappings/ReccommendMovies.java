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

/**
 *
 * @author Kruti
 */
public class ReccommendMovies {
    public List<Integer> getNMovies(int N,int userId, LinkedList<UserVote> movieClusterVotes){
        AllMappings m = AllMappings.getInstance();
        HashMap<Integer, Integer> userHistory = m.userToMovieRatings.getAllRatedMoviesForBaseUser(userId);
        
        ArrayList<UserVote> clusters = (ArrayList<UserVote>) m.userToMovieCluster.uToMCLust.get(userId).values();
        clusters.sort(new Comparator<UserVote>() {

            @Override
            public int compare(UserVote o1, UserVote o2) {
                if(o1.getProb() < o2.getProb())
                    return -1;
                return 1;
            }
        }
        );
        
        
        
        return null;
        
    }
}
