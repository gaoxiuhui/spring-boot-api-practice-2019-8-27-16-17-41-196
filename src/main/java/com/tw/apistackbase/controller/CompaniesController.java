package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tw.apistackbase.modules.Company;
import com.tw.apistackbase.modules.Employee;

@RestController
@RequestMapping("/companies")
public class CompaniesController {
	private static List<Employee> employees = new ArrayList<Employee>() {{
		add(new Employee(1));
		add(new Employee(2));
	}};
	private static List<Company> companies = new ArrayList<Company>() {{
		// 公司：id  基本信息  员工
		add(new Company(1,"百度",employees));	
	}};
	// 获取列表  查询
	@GetMapping
	public ResponseEntity<List<Company>> queryCompanies() {
		
		return ResponseEntity.ok(companies);
	}
	//获取某一个特定的公司
	@GetMapping(path = "/{id}")
    public ResponseEntity<Company> queryCompany(@PathVariable Integer id) {
		for(Company company:companies) {
       	if(company.getId()==id) {
       		 return ResponseEntity.ok(company);      
       	}
        }
        return null;
	}
	
}
