package com.tw.apistackbase.controller;

import java.util.ArrayList;
import java.util.List;

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
@RequestMapping("/companies")
public class CompaniesController {
	private static List<Employee> employees = new ArrayList<Employee>() {{
		add(new Employee(1));
		add(new Employee(2));
	}};
	private static List<Company> companies = new ArrayList<Company>() {{
		// ��˾��id  ������Ϣ  Ա��
		add(new Company(1,"�ٶ�",employees));	
	}};
	
	// ��ȡ�б�  ��ѯ
	@GetMapping
	public ResponseEntity<List<Company>> queryCompanies() {
		
		return ResponseEntity.ok(companies);
	}
	
	//��ȡĳһ���ض��Ĺ�˾
	@GetMapping(path = "/{id}/employees")
    public ResponseEntity<Company> queryCompany(@PathVariable Integer id) {
		for(Company company:companies) {
       	if(company.getId()==id) {
       		 return ResponseEntity.ok(company);      
       	}
        }
        return null;
	}
	
	//��ȡĳһ����˾�µ�����Ա��
		@GetMapping(path = "/{id}")
	    public ResponseEntity<List<Employee>> queryEmployees(@PathVariable Integer id) {
			for(Company company:companies) {
	       	if(company.getId()==id) {
	       		//ͨ��get set ��ȡֵ
	       		List<Employee> employees=company.getEmployee();  
	       		return ResponseEntity.ok(employees);   
	       	}
	        }
	        return null;
		}
		
      //��ҳ��ѯ	
		@GetMapping
	    public ResponseEntity<List<Company>> getCompaiesByPage(@RequestParam int page,@RequestParam int pageSize){			
			if(companies.size()<=(page-1)*pageSize) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	        else {
	            List<Company> pageCompanies = new ArrayList<>();
	            for(int i=(page-1)*pageSize;i<companies.size()&&i<page*pageSize;i++){
	              pageCompanies.add(companies.get(i));
	            }
	            return ResponseEntity.ok(pageCompanies);
	        }
		}
		
 	 //���һ���µĹ�˾
		@PostMapping(consumes="application/json")
		public ResponseEntity<Company> addCompany(@RequestBody Company company){
			companies.add(company);
			return ResponseEntity.status(HttpStatus.CREATED).build();   
		}	
		
	//����һ����˾�Ļ�����Ϣ	
		@PutMapping(consumes = "application/json")
		 public ResponseEntity<Company> putCompany(@RequestBody Company companyUpdate) {
		    for (Company company:companies) {
		     if (company.getId() == companyUpdate.getId()) {
//		    	 company.setBaseInformation(companyUpdate.getBaseInformation());
//		    	 company.setEmployee(companyUpdate.getEmployee());
		    	 company=companyUpdate;
		         }
		 }
		     return ResponseEntity.status(HttpStatus.CREATED).build();
		 }
	
   //ɾ��һ����˾
	    @DeleteMapping("/{id}")
		public ResponseEntity<Company> deleteCompanyByID(@PathVariable Integer id){
	    	for (int i=0;i<companies.size();i++) {
			     if (companies.get(i).getId() == id) {	
			    	 companies.remove(i);
			         }
			 }
			     return ResponseEntity.status(HttpStatus.OK).build();
		} 
}
