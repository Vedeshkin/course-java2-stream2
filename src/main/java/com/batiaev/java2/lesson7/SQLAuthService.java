package com.batiaev.java2.lesson7;

import java.sql.*;


/**
 * Created by Vedeshkin on 9/24/2018.
 * All right reserved.
 */
public class SQLAuthService implements AuthService {
    private static final String DATABASE_FILE_NAME = "C:/SQLlite/main.db";
    private String url = "jdbc:sqlite:"+DATABASE_FILE_NAME;

    private void init(){

        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                +" login text NOT NULL,\n"
                +" password text NOT NULL, \n"
                +" nick text NOT NULL\n"
                +");";
        try (Connection conn = DriverManager.getConnection(url)){
            if(conn != null){
                conn.createStatement().execute(sql);
            }

        }catch (SQLException e){e.printStackTrace();}
    }
    private void fillDB(){
        String sql = "INSERT INTO users(login,password,nick) VALUES ('login1','pass1','nick1'),('login2','pass2','nick2'),('login3','pass3','nick3');";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()){
            stmt.execute(sql);
            }catch (SQLException e)
            {e.printStackTrace();}
    }

    private void displayData(){
        String sql = "SELECT * FROM users;";
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement();
            ResultSet rs  = stmt.executeQuery(sql)){
            while (rs.next()) {
                System.out.println(rs.getString("login") +  "\t" +
                        rs.getString("password") + "\t" +
                        rs.getString("nick"));
            }

        }catch (SQLException e)
        {e.printStackTrace();}

    }

    public SQLAuthService() {
        init();
        fillDB();
        displayData();
    }


    @Override
    public String authByLoginAndPassword(String login, String password) {
        String sql = "Select nick FROM users WHERE login = ? AND password = ? ;";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement psmnt = conn.prepareStatement(sql))
        {
             psmnt.setString(1,login);
             psmnt.setString(2,password);

             ResultSet rs = psmnt.executeQuery();
             if (rs!= null)
             {

                 System.out.println(rs.getString("nick"));
                 return rs.getString("nick");
             }


    }catch (SQLException e ) {
            e.printStackTrace();
        }
        return null;

    }

    @Override
    public boolean changeNick(String nick) {
        String sql = "UPDATE users SET nick = ? WHERE nick = ?;";
        try (Connection conn = DriverManager.getConnection(url);
             PreparedStatement psmnt = conn.prepareStatement(sql)) {
            psmnt.setString(1, nick);
            psmnt.setString(2, nick);
            psmnt.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

}
