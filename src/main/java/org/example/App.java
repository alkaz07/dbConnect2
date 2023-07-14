package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        String address = "jdbc:postgresql://10.10.101.200:5500/biblio";
        String user = "postgres";
        String pwd = "123";
        Connection con = connectToDB(address, user, pwd);
    }

    public static Connection connectToDB(String ad, String us, String pw) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ad, us, pw);
            System.out.println("ура! есть коннект");
        } catch (SQLException e) {
            System.out.println("не удалось подключиться к " + ad);
        }

        return connection;
    }
}
