/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anmpout.fcddashboard.service;


import com.anmpout.fcddashboard.dao.PathDao;
import com.anmpout.fcddashboard.model.FilterData;
import com.anmpout.fcddashboard.model.Path;
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
    
}
