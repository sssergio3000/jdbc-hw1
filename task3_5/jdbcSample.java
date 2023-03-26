package task3_5;

import java.io.*;
import java.sql.*;

public class jdbcSample {
    private static final String URL = "jdbc:mysql://localhost:3306/myjoinsdb1";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";

    public static void main(String[] args) {




        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is loaded");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Connection connection = null;

        try{
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD );
            if (connection!=null){
                System.out.println("connection is set");
            }
            Statement statement = connection.createStatement();
            statement.executeUpdate("insert into people (name, ph_num) values ('red', '098888888')");
//            statement.executeUpdate("delete from people where name = 'red'");


 //           1) Получите контактные данные сотрудников (номера телефонов, место жительства)
            ResultSet rs;
            rs = statement.executeQuery("select name, ph_num, address from people join pers_info on id = pers_id;");
            System.out.println("1) Получите контактные данные сотрудников (номера телефонов, место жительства)");
            while (rs.next()){
                System.out.println(rs.getString(1)+" "+rs.getString(2)+ "  "+ rs.getString(3));
            }
            System.out.println("================================================");
            System.out.println();

//            2) Получите информацию о дате рождения всех холостых сотрудников и их номера

            rs = statement.executeQuery("select name, pers_info.birth_date, " +
                    "ph_num from people join pers_info on id = " +
                    "pers_id where fam_status in ('single', 'divorced')");
            System.out.println("2) Получите информацию о дате рождения всех холостых " +
                    "сотрудников и их номера");
            while (rs.next()){
                System.out.println(rs.getString("name")+ "  "
                        + rs.getDate("birth_date")+ "  "
                        + rs.getString("ph_num"));

            }
            System.out.println("================================================");
            System.out.println();

//            3) Получите информацию обо всех менеджерах компании: дату рождения и номер телефона.
            rs = statement.executeQuery("select name, pers_info.birth_date, ph_num from people " +
                    "join off_info on id = pers_id " +
                    "join pers_info on pers_info.pers_id = off_info.pers_id " +
                    "where position_name = 'manager'");
            System.out.println("3) Получите информацию обо всех менеджерах компании: " +
                    "дату рождения и номер телефона");
            while (rs.next()){
                System.out.println(rs.getString("name")+ "  "
                        + rs.getDate("birth_date")+ "  "
                        + rs.getString("ph_num"));

            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            if (connection.isClosed()){
                System.out.println("connection is closed");

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
