/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.beans;

import uta.cse4361.databases.DatabaseManager;
/**
 *
 * @author oguni
 */
public class CreateAptTypeBean {
    private String name = null;
    
    public CreateAptTypeBean(){
        
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String createAptType(){
        DatabaseManager dm = new DatabaseManager();
        String msg = dm.createAptType(name);           
        return msg;
    }
}
