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
public class CreateActTypeBean {
    private String name = null;
    private int privilege = 2;
    
    public CreateActTypeBean(){
        
    }
    
    public String getName(){
        return name;
    }
    
    public int getPrivilege(){
        return privilege;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setPrivilege(int privilege){
        this.privilege = privilege;
    }
    
    public String createActType(){
        DatabaseManager dm = new DatabaseManager();
        String msg = dm.createAccountType(name, privilege);           
        return msg;
    }
}
