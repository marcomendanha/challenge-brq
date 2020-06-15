package com.brq.importer.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.brq.importer.dto.UserDTO;
import com.brq.importer.models.User;
import com.brq.importer.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	public User saveImport(User user) {
		if(repository.existsByEmailAndCompanyId(user.getEmail(), user.getCompanyId())) {
			return user;
		}
		return repository.save(user);
	}
	
	public List<User> findAll() {
		return repository.findAll();
	}
	
	public ResponseEntity<UserDTO> save(UserDTO userDTO) {
		if(repository.existsByEmailAndCompanyId(userDTO.getEmail(), userDTO.getCompanyId())) {
			return new ResponseEntity<>(userDTO, HttpStatus.BAD_REQUEST);
		}
		User userSaved = repository.save(userDTO.transformDtoToModel());
		return new ResponseEntity<>(userDTO.transformModelToDto(userSaved), HttpStatus.CREATED);
	}
	
	public ResponseEntity<UserDTO> findById(Integer userId) {
		UserDTO userDto = new UserDTO();
		Optional<User> opUser = repository.findById(userId);
		return new ResponseEntity<UserDTO>(userDto.transformModelToDto(opUser.get()), HttpStatus.FOUND);
	}
	
	public ResponseEntity<List<User>> findByEmail(String email) {
		List<User> users = repository.findByEmail(email);
		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
	}
	
	public ResponseEntity<List<User>> findByCompanyId(Integer companyId) {
		List<User> users = repository.findByCompanyId(companyId);
		return new ResponseEntity<List<User>>(users, HttpStatus.FOUND);
	}

	public ResponseEntity<Object> delete(Integer userId) {
		Optional<User> userOP = repository.findById(userId);
		if(userOP.isPresent()) {
			repository.delete(userOP.get());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
