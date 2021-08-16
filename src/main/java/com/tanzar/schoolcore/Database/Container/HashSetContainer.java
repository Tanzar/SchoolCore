/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanzar.schoolcore.Database.Container;

import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 *
 * @author Tanzar
 */
public abstract class HashSetContainer<itemType> {
    
    private HashSet<itemType> data;
    
    public HashSetContainer(){
        this.data = new LinkedHashSet<itemType>();
    }
    
    public HashSetContainer(HashSet<itemType> data){
        this.data = data;
    }
    
    public void add(itemType item){
        this.data.add(item);
    }
    
    public itemType get(Integer index){
        itemType[] dataArray = this.toArray();
        return dataArray[index];
    }
    
    public void remove(Integer index){
        itemType item = this.get(index);
        this.data.remove(item);
    }
    
    public Integer size(){
        return this.data.size();
    }
    
    public boolean contains(itemType item){
        return this.data.contains(item);
    }
    
    public void clear(){
        this.data.clear();
    }
    
    public itemType[] toArray(){
        return this.toArray(data);
    }
    
    protected abstract itemType[] toArray(HashSet<itemType> data);
}
