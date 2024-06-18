package com.example.plantsstore.DTO.responses;

public class EmployeeResponse {
  public static class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String position;
    private String image;

    public Employee(
        String id,
        String firstName,
        String lastName,
        String position,
        String image) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
      this.image = image;
    }

    public Employee(
        String id,
        String firstName,
        String lastName,
        String position) {
      this.id = id;
      this.firstName = firstName;
      this.lastName = lastName;
      this.position = position;
    }

    public Employee() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getFirstName() {
      return firstName;
    }

    public void setFirstName(String firstName) {
      this.firstName = firstName;
    }

    public String getLastName() {
      return lastName;
    }

    public void setLastName(String lastName) {
      this.lastName = lastName;
    }

    public String getPosition() {
      return position;
    }

    public void setPosition(String position) {
      this.position = position;
    }

    public String getImage() {
      return image;
    }

    public void setImage(String image) {
      this.image = image;
    }
  }

  public static class EmployeeRegistration {
    private Employee employee;
    private UserResponse user;

    public EmployeeRegistration(Employee employee, UserResponse user) {
      this.employee = employee;
      this.user = user;
    }

    public EmployeeRegistration() {
    }

    public Employee getEmployee() {
      return employee;
    }

    public void setEmployee(Employee employee) {
      this.employee = employee;
    }

    public UserResponse getUser() {
      return user;
    }

    public void setUser(UserResponse user) {
      this.user = user;
    }
  }
}
