package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        String address = "jdbc:postgresql://10.10.101.200:5500/biblio";
        String user = "postgres";
        String pwd = "123";
        try {
            Connection con = connectToDB(address, user, pwd);
            getTitlesByYear(con);
            con.close();
        } catch (SQLException e) {
            System.out.println("что-то пошло не так...");
            System.out.println(e.getMessage());
        }
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

    public static void getTitlesByYear(Connection cnt) {
        String q = "SELECT year, title FROM book ORDER BY year";
        //System.out.println(q);
        int y = 0;
        String t = "";
        try {
            ResultSet rs = cnt.createStatement().executeQuery(q);
            while (rs.next()) {
                if (y != rs.getInt(1)) {
                    if (y!=0 && t!= "")
                        System.out.println("    " + y + "     " + t);
                    y = rs.getInt(1);
                    t = rs.getString(2);
                }
                else {
                    t +=", "+ rs.getString(2);
                }
            }
            System.out.println("    " + y + "     " + t);

        } catch (SQLException e) {
            System.out.println("запрос " + q + " не удалось выполнить");
            System.out.println(e.getMessage());
        }

    }
}
