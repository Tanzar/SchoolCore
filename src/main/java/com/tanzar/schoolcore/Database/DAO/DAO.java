/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanzar.schoolcore.Database.DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Tanzar
 */
@Repository
@Transactional
public abstract class DAO<entity, container> {
    
    @PersistenceContext	
    private EntityManager entityManager;
    
    private String entityName;
    
    public DAO(String entityName){
        this.entityName = entityName;
    }
    
    public container getAll() {
        String hql = "from " + entityName;
        List<entity> result = this.executeSelect(hql);
        return convertResults(result);
    }
    
    public int add(entity object) {
        int id = -1;
        Session ses = this.entityManager.unwrap(Session.class);
        id = (int) ses.save(object);
        return id;
    }
    
    public int[] add(entity[] entities){
        int[] ids = new int[entities.length];
        for(int i = 0; i < ids.length; i++){
            ids[i] = this.add(entities[i]);
        }
        return ids;
    }
    
    public void remove(entity object) {
        Session ses = this.entityManager.unwrap(Session.class);
        if(this.entityManager.contains(object)){
            ses.delete(object);
        }
        else{
            ses.delete(this.entityManager.merge(object));
        }
    }
    
    public void update(entity object) {
        Session ses = this.entityManager.unwrap(Session.class);
        ses.update(object);
    }
    
    protected container getWhere(String conditions){
        String hql = "from " + this.entityName + " where " + conditions;
        List<entity> result = this.executeSelect(hql);
        return convertResults(result);
    }
    
    private List<entity> executeSelect(String sqlQuery){
        Query query = entityManager.createQuery(sqlQuery);
        List<entity> resultList = query.getResultList();
        return resultList;
    }
    
    protected abstract container convertResults(List<entity> list);
}
