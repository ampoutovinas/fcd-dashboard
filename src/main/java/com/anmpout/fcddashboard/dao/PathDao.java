/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.dao;


import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.MonthData;
import com.anmpout.fcddashboard.model.Path;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author anmpout
 */
@Remote
public interface PathDao {
    
    public Path getPath(Integer pathId);
    
    public List<Path> getAllPaths();
    public List<Path> getAllRegionPaths(Integer regionId);
    public List<FilterData> getDayData(Integer pathId,Long timestampFrom, Long timestampTo);
    public List<MonthData> getMonthData(Integer pathId,Integer month,Integer year) ;
    
}
