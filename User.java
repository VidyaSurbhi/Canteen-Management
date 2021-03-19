/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import beans.UserBeans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class User {
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private DbConnection db;
    
    public User(){
      db=new DbConnection();
      conn=db.getConnection();
      stmt=db.getStatement();
    } 
    
    public boolean userLogin(UserBeans ub){
        boolean flag=false;
        
        try{
            String sql="select * from temp_users where name='"+ub.getName()+"' and password='"+ub.getPassword()+"'";
            rs=stmt.executeQuery(sql);
            if(rs.next()){
                flag=true;
      }
        }catch(SQLException e){
            db.setWarningMessage("UserModel.userLogin",e.toString());
        }
        return flag;
    }
    

    public UserBeans getUserInfo(ResultSet rs){
        UserBeans u=null;
        if(rs!=null){
        try{
          u=new UserBeans();
          u.setUserId(rs.getInt("user_id"));
          u.setName(rs.getString("name"));
          u.setGender(rs.getString("gender"));
          u.setDob(rs.getDate("dob")+"");
          u.setDesig(rs.getString("desig"));
          u.setMobile(rs.getString("mobile"));
          u.setAddress(rs.getString("address"));
          u.setEmail(rs.getString("email"));
          u.setRole(rs.getString("role"));
          u.setPassword(rs.getString("password"));
          
        }
        catch(SQLException e){
            db.setWarningMessage("UserModel.getUserInfo",e.toString());
        }
    }
        return u;
    }
         
  
    
    public boolean createNewUser(UserBeans u){
        boolean flag = true;
        try{
            String sql = "insert into temp_users values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement p = conn.prepareCall(sql);
            p.setString(1, u.getName());
            p.setString(2, u.getGender());
            p.setString(3, u.getDob());
            p.setString(4, u.getDesig());
            p.setString(5, u.getMobile());
            p.setString(6, u.getEmail());
            p.setString(7, u.getAddress());
            p.setString(8, u.getRole());
            p.setString(10, u.getPassword());
            flag = p.execute();
        }
        catch(SQLException e){
            db.setWarningMessage("UserModel.createNewUser",e.toString());
        }
        return flag;
    }
    

}
