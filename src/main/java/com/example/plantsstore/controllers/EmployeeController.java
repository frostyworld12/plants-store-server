package com.example.plantsstore.controllers;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.plantsstore.model.Employee;
import com.example.plantsstore.repository.EmployeeRepository;

@Controller
@RequestMapping(path="/employees")
public class EmployeeController {
  @Autowired
  private EmployeeRepository employeeRepository;
}
