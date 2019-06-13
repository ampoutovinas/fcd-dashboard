/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.dao;


import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.FilterDataRT;
import com.anmpout.fcddashboard.model.MonthData;
import com.anmpout.fcddashboard.model.Path;
import com.anmpout.fcddashboard.model.ProfileData;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author anmpout
 */
@Remote
public interface PathDao {
    /**
     * Return a single path
     * @param pathId
     * @return Path
     */
    public Path getPath(Integer pathId);
    /**
     * Returns all available paths
     * @return List of Paths
     */
    public List<Path> getAllPaths();
    /**
     * Returns all paths for a region
     * @param regionId
     * @return List of Paths
     */
    public List<Path> getAllRegionPaths(Integer regionId);
    /**
     * Return data for a day
     * @param pathId
     * @param timestampFrom
     * @param timestampTo
     * @return List of FilterData
     */
    public List<FilterData> getDayData(Integer pathId,Long timestampFrom, Long timestampTo);
    /**
     * Return data for a month
     * @param pathId
     * @param month
     * @param year
     * @return List of FilterData
     */
    public List<MonthData> getMonthData(Integer pathId,Integer month,Integer year) ;
    /**
     * Returns all available paths
     * @param timestampFrom
     * @return List of Paths
     */
    public List<Path> getAllPathsForRT(Long timestampFrom);
    
  
    public FilterDataRT getRTData(Integer pathId, Long currentTimestamp);
    
    public List<ProfileData> getProfileData(Integer pathId, Long currentTimestamp);
    
}
