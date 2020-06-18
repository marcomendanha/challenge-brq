package com.brq.importer.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brq.importer.dto.UserDTO;
import com.brq.importer.models.User;
import com.brq.importer.services.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public List<User> findAll() {
		return service.findAll();
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody @Valid UserDTO userDTO) {
		return service.save(userDTO);
	}
	
	@GetMapping(path = "/{userId}")
	public ResponseEntity<UserDTO> findById(@PathVariable Integer userId) {
		return service.findById(userId);
	}
	
	@GetMapping(path = "/email/{email}")
	public ResponseEntity<List<User>> findByEmail(@PathVariable String email) {
		return service.findByEmail(email);
	}
	
	@GetMapping(path = "/company/{companyId}")
	public ResponseEntity<List<User>> findByEmail(@PathVariable Integer companyId) {
		return service.findByCompanyId(companyId);
	}
	
	@DeleteMapping(path = "/{userId}")
	public ResponseEntity<Object> delete(@PathVariable Integer userId) {
		return service.delete(userId);
	}

}
