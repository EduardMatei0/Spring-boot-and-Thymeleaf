package com.eduardmatei.thymeleaf.controller;


import com.eduardmatei.thymeleaf.entity.Employee;
import com.eduardmatei.thymeleaf.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model) {

        List<Employee> employees = employeeService.findAll();

        model.addAttribute("employees", employees);

        return "/employees/list-employees";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {

        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "/employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.findById(id);

        model.addAttribute("employee", employee);

        return "/employees/employee-form";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);

        return "redirect:/employees/list";
    }
}
