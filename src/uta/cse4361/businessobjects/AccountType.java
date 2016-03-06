/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

/**
 *
 * @author oguni
 */
public class AccountType {
    private int id;
    private String name;
    
    public AccountType(int id, String name){
        this.id = id;
        this.name= name;
    }
    
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
}
