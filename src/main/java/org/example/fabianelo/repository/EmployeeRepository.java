package org.example.fabianelo.repository;

import org.example.fabianelo.model.Employee;
import org.example.fabianelo.util.DataBaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeRepository implements Repository<Employee> {


    @Override
    public Employee findById(int id) throws SQLException {
        return null;
    }

    @Override
    public List<Employee> findAll() throws SQLException {
        List<Employee> employees = new ArrayList<>();

        try (Connection connection = DataBaseConnection.getConnection(); Statement myStmt = connection.createStatement(); ResultSet myRs = myStmt.executeQuery("SELECT * FROM employees");) {
            while (myRs.next()) {
                employees.add(addEmployee(myRs));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return employees;
    }

    @Override
    public void save(Employee employee) throws SQLException {
        String sql;

        if (employee.getId() < 1) {
            sql = "INSERT INTO employees(first_name, pa_surname, ma_surname, email, salary, curp) VALUES(?, ?, ?, ?, ?, ?)";
        } else {
            sql = "UPDATE employees set first_name = ?, pa_surname = ?, ma_surname = ?, email = ?, salary = ?,  curp = ?, WHERE id = ?";
        }

        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement myStmt = connection.prepareStatement(sql)) {

            myStmt.setString(1, employee.getFirst_name());
            myStmt.setString(2, employee.getPa_surname());
            myStmt.setString(3, employee.getMa_surname());
            myStmt.setString(4, employee.getEmail());
            myStmt.setFloat(5, employee.getSalary());
            myStmt.setString(6, employee.getCurp());

            if (employee.getId() != 0) {
                myStmt.setInt(7, employee.getId());
            }

            int rows = myStmt.executeUpdate();

            if (rows > 0) {
                System.out.println("All that's good");
            } else {
                System.out.println("Something is going wrong");
            }
        }

    }

    @Override
    public void update(Employee object) throws SQLException {

    }

    @Override
    public void delete(int id) throws SQLException {
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement myStmt = connection.prepareStatement("DELETE FROM employees WHERE id = ?")) {
            myStmt.setInt(1, id);
            myStmt.executeUpdate();
        }
    }

    private static Employee addEmployee(ResultSet myRes) throws SQLException {
        Employee employee = new Employee();
        employee.setId(myRes.getInt("id"));
        employee.setFirst_name(myRes.getString("first_name"));
        employee.setPa_surname(myRes.getString("pa_surname"));
        employee.setMa_surname(myRes.getString("ma_surname"));
        employee.setEmail(myRes.getString("email"));
        employee.setSalary(myRes.getFloat("salary"));
        employee.setCurp(myRes.getString("curp"));

        return employee;
    }
}
