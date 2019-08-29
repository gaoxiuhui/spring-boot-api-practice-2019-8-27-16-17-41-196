package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.catalina.filters.AddDefaultCharsetFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.modules.Company;
import com.tw.apistackbase.modules.Employee;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private static List<Employee> employees=new ArrayList<Employee>() {{
    	add(new Employee(1,"男"));
    	add(new Employee(2,"女"));
    }};
    //获取某一个列表
    @GetMapping
    public ResponseEntity<List<Employee>> getEmployeesList(){
    	return ResponseEntity.ok(employees);
    }
    //通过id查询某一个员工
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeesList(@PathVariable Integer id){
    	for(Employee employee:employees) {
    		if(employee.getId()==id) {
    			return ResponseEntity.ok(employee);
    		}
    	}
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
   //增加一个员工
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
    	employees.add(employee);
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    
  //更新员工
    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee updateemployee){
    	for(Employee employee:employees) {
    		if(employee.getId()==updateemployee.getId()) {
    			employee=updateemployee;
    		}
    	}
    	return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //删除员工
    @DeleteMapping("/{id}")
    public ResponseEntity<Employee> deleteEmployee(@PathVariable Integer id){
    	for(int i=0;i<employees.size();i++) {
    		if(employees.get(i).getId()==id) {
    			employees.remove(i);
    		}
    	}
    	return ResponseEntity.status(HttpStatus.OK).build();
    } 
    
  //通过gender查询某一个员工
    @GetMapping
    public ResponseEntity<Employee> getEmployeeByGender(@RequestParam String gender){
    	for(Employee employee:employees) {
    		if(employee.getGender()==gender) {
    			return ResponseEntity.ok(employee);
    		}
    	}
    	return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
  //分页查询	
  		@GetMapping
  	    public ResponseEntity<List<Employee>> getEmployeeByPage(@RequestParam int page,@RequestParam int pageSize){			
  			if(employees.size()<=(page-1)*pageSize) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  	        else {
  	            List<Employee> pageEmployees = new ArrayList<>();
  	            for(int i=(page-1)*pageSize;i<employees.size()&&i<page*pageSize;i++){
  	            	pageEmployees.add(employees.get(i));
  	            }
  	            return ResponseEntity.ok(pageEmployees);
  	        }
  		}
}
