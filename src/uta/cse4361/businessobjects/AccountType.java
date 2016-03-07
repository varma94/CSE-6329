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
    private int privilege;
    private static final String[] privilegeText = {"Administrator", "Advisor", "Student", "Lead Advisor", "Staff"};
    
    public AccountType(int id, String name, int privilege){
        this.id = id;
        this.name= name;
        this.privilege = privilege;
    }
    
    public int getID(){
        return id;
    }
    
    public String getName(){
        return name;
    }
    
    public int getPrivilege(){
        return privilege;
    }
    
    public String getPrivilegeText(){
        return privilegeText[privilege];
    }
    
}
