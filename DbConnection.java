import java.sql.*;
import java.util.Scanner;

import javax.swing.plaf.nimbus.State;

public class DbConnection {
    private static final String username = "root";
    private static final String password = "";
    private static final String url = "jdbc:mysql://localhost:3306/corejavacrudapplication";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }

    public static String getUrl() {
        return url;
    }

    public static String getDRIVER_CLASS() {
        return DRIVER_CLASS;
    }

}

class CRUDOperation {
    Connection conn = null;

    public CRUDOperation() throws ClassNotFoundException, SQLException {
        try {
            DbConnection connection = new DbConnection();
            Class.forName(connection.getDRIVER_CLASS());
            System.out.println("Connecting to the database.....");
            conn = DriverManager.getConnection(connection.getUrl(), connection.getUsername(), connection.getPassword());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    void Create() throws SQLException {
        PreparedStatement statement = null;
        try {
            // String query = "Insert into person (personid, personname) values (?,?)";
            // statement = conn.prepareStatement(query);
            // statement.setInt(1, 1);
            // statement.setString(2, "Mandip Timsina");
            // statement.executeUpdate();
            // System.out.println("The information are recorded successfully");

            Scanner scanner = new Scanner(System.in);
            boolean givenValidation = false;
            while (!givenValidation) {
                System.out.println("Enter your name here: ");
                String name = scanner.nextLine();
                if (name instanceof String) {
                    String query = "Insert into person (personid, personname) values (?, ?)";
                    statement = conn.prepareStatement(query);
                    statement.setInt(1, 2);
                    statement.setString(2, name);
                    statement.executeUpdate();
                    System.out.println("The information which you have enetred is recorded successfully");
                    givenValidation = true;
                } else {
                    System.out.println("Invalid input. Please try again:");
                }
            }

        } finally {
            if (statement != null) {
                statement.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}

class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        CRUDOperation operation = new CRUDOperation();
        operation.Create();
    }
}
