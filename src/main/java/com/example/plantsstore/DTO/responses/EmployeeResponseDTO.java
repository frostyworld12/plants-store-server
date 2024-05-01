package com.example.plantsstore.DTO.responses;
import java.util.List;

public class EmployeeResponseDTO {
  public static class Employees {
    public List<Employee> employees;
    public Integer count;

    public Employees(Integer count, List<Employee> employees) {
      this.count = count;
      this.employees = employees;
    }

    public Employees() {}
  }

  public static class Employee {
    public String userId;
    public String image;
    public String employeeId;
    public String username;
    public String firstName;
    public String lastName;
    public String position;

    public Employee() {}

    public Employee(String userId, String employeeId, String username, String image, String firstName, String lastName, String position) {
      this.userId = userId;
      this.employeeId = employeeId;
      this.username = username;
      this.image = image;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
    }
  }
}
