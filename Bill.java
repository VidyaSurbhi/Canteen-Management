/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author DELL
 */
public class Bill {
    private DbConnection db;
    private Connection conn;
    private Statement stmt;
    private ResultSet rs;
    private int total, current;
    
    public Bill(){
        try{
            db = new DbConnection();
            conn = db.getConnection();
            stmt = db.getStatement();
            rs = stmt.executeQuery("select (*) from staff_table");
            if(rs.next()){
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            //Logger.getLogger(Staff.class.getName()).log(Level.SEVERE, null, ex);
           // db.setWarningMessage("Staff", e.toString());

        }
    }
}
