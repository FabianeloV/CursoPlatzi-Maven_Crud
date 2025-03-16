package org.example.fabianelo;

import java.sql.*;

import org.example.fabianelo.model.Employee;
import org.example.fabianelo.repository.EmployeeRepository;
import org.example.fabianelo.repository.Repository;
import org.example.fabianelo.util.DataBaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        try (Connection conn = DataBaseConnection.getConnection()) {
            if (conn.getAutoCommit()) {
                conn.setAutoCommit(false);
            }

            try {
                Repository<Employee> repository = new EmployeeRepository();
                Employee employee = new Employee();

                System.out.println("NEW employee:");

                //Seteamnos el empleado a añadir
                employee.setFirst_name("Fabianelo");
                employee.setPa_surname("Verdesoto");
                employee.setMa_surname("Romero");
                employee.setEmail("fabian@email.com");
                employee.setSalary(12000F);
                employee.setCurp("AME356273894098727");

                //Accedemos al metodo save() del repositorio
                repository.save(employee);

                //Realizamos en commit si se realizó la operación correctamente
                conn.commit();

                repository.findAll().forEach(System.out::println);

            } catch (SQLException e){
                //En caso de error, regresamos al ultimo estado antes de realizar la operacion
                conn.rollback();
                e.printStackTrace();
            }
        }
    }
}