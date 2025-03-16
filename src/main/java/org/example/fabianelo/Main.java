package org.example.fabianelo;

import java.sql.*;

import org.example.fabianelo.model.Employee;
import org.example.fabianelo.repository.EmployeeRepository;
import org.example.fabianelo.repository.Repository;
import org.example.fabianelo.util.DataBaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (
                //myConn -- Establecer conexión
                Connection myConn = DataBaseConnection.getConnection();

                //Inicializar statement
                Statement myStmt = myConn.createStatement();

                //Result statement -- Mostrar datos: Nombre y apellido
                ResultSet myRs = myStmt.executeQuery("SELECT * FROM employees");
        ) {
            System.out.println("Connected to database");

            Repository<Employee> repo = new EmployeeRepository();

            repo.findAll().forEach(System.out::println);

            /*while (myRs.next()) {
                System.out.println(myRs.getString("first_name") + " | " + myRs.getString("pa_surname") + " | " + myRs.getString("email"));
            }*/
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Failed to connect to database");
        }
    }
}