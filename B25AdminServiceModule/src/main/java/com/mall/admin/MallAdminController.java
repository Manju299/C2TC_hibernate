package com.mall.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.bytebuddy.asm.Advice.Origin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class MallAdminController {
	
	@Autowired
	private MallAdminService service;
	

	@PostMapping("/Admin")
	public void add(@RequestBody MallAdmin malladmin) {
		service.add(malladmin);
		
	}
	
	@DeleteMapping("/Admin/{id}")
	public void remove(@PathVariable Integer id) {
		service.delete(id);
	}
	
	@PutMapping("/Admin/{id}")
	public ResponseEntity<?> update(@RequestBody MallAdmin malladmin,@PathVariable Integer id){
		try {
			MallAdmin exisingadmin =  service.get(id);
			service.add(malladmin);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		catch(Exception e){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/Admin/{id}")
	public ResponseEntity<MallAdmin> get(@PathVariable Integer id ){
		try {
			MallAdmin malladmin = service.get(id);
			return new ResponseEntity<MallAdmin>(malladmin,HttpStatus.OK);
		}
		catch(Exception e) {
			return new ResponseEntity<MallAdmin>(HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/Admin")
	public List<MallAdmin> list(){
		return service.listall();
	}
	

}
