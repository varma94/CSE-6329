/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uta.cse4361.businessobjects;

/**
 *
 * @author varma
 */
public class StudentPasswordAccount {
     private String email;
    private String newPassword;
   
    
    public StudentPasswordAccount(){
        
    }
    
    public boolean initialize(String email, String newPassword){
        boolean result = true;
        
        if(email != null && !email.isEmpty() && 
                newPassword != null && !newPassword.isEmpty())
                
        {
            this.email = email;
            this.newPassword = newPassword;
           
        }
        else
        {
            result = false;
        }
        
        return result;
    }
    
//    public boolean initialize(String name, String email, String department, int ID, int rank){
//        boolean result = true;
//        
//        if(name != null && !name.isEmpty() && 
//                email != null && !email.isEmpty() && 
//                department != null && !department.isEmpty())
//        {
//            this.name = name;
//            this.email = email;
//            this.department = department;
//            this.rank = rank;
//        }
//        else
//        {
//            result = false;
//        }
//        
//        return result;
//    }
    
    public static String hashPassword(String newPassword)
    {
        int hash = newPassword.hashCode();
        return Integer.toString(hash);
    }
    
  public void setEmail(String email){
        this.email = email;
    }
    
    public void setTempPassword(String newPassword){
        this.newPassword = newPassword;
    }
    
    public String getPassword(){
        return newPassword;
    }
    
    public String getEmail(){
        return email;
    }
    
    
}
