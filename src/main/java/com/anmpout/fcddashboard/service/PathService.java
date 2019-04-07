/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.service;


import com.anmpout.fcddashboard.dao.PathDao;
import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.MonthData;
import com.anmpout.fcddashboard.model.Path;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author anmpout
 */
@Stateful
@LocalBean
public class PathService implements PathDao  {
        @PersistenceContext
        private EntityManager entityManager;
    @Override
    public Path getPath(Integer pathId) {
       Query query = entityManager.createQuery("SELECT p FROM Path p where p.pathId=?1");
       query.setParameter(1, pathId);
        return (Path) query.getSingleResult();
    }

    @Override
    public List<Path> getAllPaths() {
        Query query = entityManager.createQuery("SELECT p FROM Path p");
        return query.getResultList();
    }

    @Override
    public List<Path> getAllRegionPaths(Integer regionId) {
           Query query = entityManager.createQuery("SELECT p FROM Path p where p.regionId= ?1");
           query.setParameter(1, regionId);
        return query.getResultList();   
    }
    @Override
    public List<FilterData> getDayData(Integer pathId,Long timestampFrom, Long timestampTo) {
           Query query = entityManager.createQuery("SELECT t FROM FilterData t WHERE t.pathId=?1"
                   + " AND t.timestamp>=?2 AND t.timestamp < ?3 ORDER BY t.timestamp");
            query.setParameter(1, pathId);
            query.setParameter(2, timestampFrom);
            query.setParameter(3, timestampTo);
        return query.getResultList();     }
    
        @Override
    public List<MonthData> getMonthData(Integer pathId,Integer month,Integer year) {
        List<MonthData>  returnList = new ArrayList<>();
       List<Object[]> results  = entityManager.createNativeQuery("SELECT "
               + "T.PATH_ID,T.DAY,T.MONTH,T.YEAR,AVG(T.TIME),MAX(T.TIME),MIN(T.TIME),"
               + "AVG(T.COUNT),MAX(T.COUNT),MIN(T.COUNT),AVG(T.SPEED),MAX(T.SPEED),"
               + "MIN(T.SPEED) FROM FILTER_DATA T WHERE T.PATH_ID = ?1 AND T.MONTH = ?2 AND T.YEAR = ?3 "
                + " GROUP BY T.PATH_ID,T.DAY,T.MONTH,T.YEAR ORDER BY T.PATH_ID,T.DAY,T.MONTH,T.YEAR")
                .setParameter(1, pathId)
                .setParameter(2, month)
                .setParameter(3, year).getResultList();
            
        results.stream().forEach((record) -> {
            MonthData tmpData = new MonthData();
                tmpData.setPathId((Integer) record[0]);
                tmpData.setDay((Integer) record[1]);
                tmpData.setMonth((Integer) record[2]);
                tmpData.setYear((Integer) record[3]);
                tmpData.setTime(((BigDecimal) record[4]).intValue());
                tmpData.setMaxTime((Integer) record[5]);
                tmpData.setMinTime((Integer) record[6]);
                tmpData.setCount(((BigDecimal) record[7]).intValue());
                tmpData.setMaxCount((Integer) record[8]);
                tmpData.setMinCount((Integer) record[9]);
                tmpData.setSpeed(((Double) record[10]));
                tmpData.setMaxSpeed(((Double) record[11]));
                tmpData.setMinSpeed(((Double) record[12]));
                 returnList.add(tmpData);
        });

        return returnList;
    }
    
        @Override
    public List<Path> getAllPathsForRT(Long timestampFrom) {
        Query query = entityManager.createQuery("SELECT p FROM Path p INNER JOIN"
                + " FilterDataRT rt ON p.pathId = rt.pathId WHERE rt.timestamp=?1 ORDER BY rt.pathId");
         query.setParameter(1, timestampFrom);
        return query.getResultList();
    }
    
    
//    SELECT  T.PATH_ID,T.DAY,T.MONTH,T.YEAR,AVG(T.TIME),
//    MAX(T.TIME),MIN(T.TIME),AVG(T.COUNT),MAX(T.COUNT),MIN(T.COUNT),AVG(T.SPEED),MAX(T.SPEED),MIN(T.SPEED) FROM FILTER_DATA T
//WHERE T.PATH_ID = 100 AND T.MONTH = 04 AND T.YEAR = 2018 
// GROUP BY T.PATH_ID,T.DAY,T.MONTH,T.YEAR
//ORDER BY T.PATH_ID,T.DAY,T.MONTH,T.YEAR;
    
}
