package task6;

import java.io.*;
import java.sql.*;

public class TestJDBC {

    private final static String URL = "jdbc:mysql://localhost:3306/testdb";
    private final static String NAME = "root";
    private final static String PASSWORD = "root";

    public static void main(String[] args) {

        //the path should be changed according to your actual path
        File file = new File("D:\\downloads\\cbs\\jdbc-hibernate\\hw\\jdbc-1\\src\\task6\\queries.txt");


        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // driver registering
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


//creating connection and statement
        try (Connection connection = DriverManager.getConnection(URL, NAME, PASSWORD)) {
            Statement statement = connection.createStatement();
            String request;
            BufferedReader br = new BufferedReader(new FileReader(file));


            while ((request = br.readLine()) != null) {
                System.out.println("request: " + request);

                //printing out the result table if request = select
                if (request.substring(0, 6).equalsIgnoreCase("select")) {
                    System.out.println("=======================================");
                    ResultSet rs;
                    rs = statement.executeQuery(request);
                    while (rs.next()) {
                        System.out.println("id: " + rs.getInt("id") + ",   name: "
                                + rs.getString(2) + ",   age: " + rs.getInt("age"));

                    }
                    System.out.println("=======================================");
                }
                statement.execute(request);
            }




        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
