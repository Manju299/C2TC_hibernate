package com.tns.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController

public class CustomerController {


	@Autowired
	private CustomerService service;
	
	
	@PostMapping("/Customer")
	public void add(@RequestBody Customer Customer) {
		service.add(Customer);
		
	}
	
	@DeleteMapping("/Customer/{id}")
	public void remove(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/Customer/{id}")
	public ResponseEntity<?> update(@RequestBody Customer Customer,@PathVariable Integer id){
		try {
			Customer exisitingCustomer =  service.get(id);
			service.add(Customer);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/Customer/{id}")
	public ResponseEntity<Customer> get(@PathVariable Integer id ){
		try {
			Customer Customer = service.get(id);
			return new ResponseEntity<Customer>(Customer,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<Customer>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/Customer")
	public List<Customer> list(){
		return service.listall();
	}
	

}


