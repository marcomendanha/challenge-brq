package com.brq.importer.dto;

import java.sql.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.brq.importer.models.User;
import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO {

	private Integer userId;
	
	@NotNull
	private Integer companyId;
	
	@Email
	@NotBlank
	private String email;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull
	private Date birthdate;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public User transformDtoToModel() {
		User user = new User();
		user.setUserId(getUserId());
		user.setCompanyId(getCompanyId());
		user.setEmail(getEmail());
		user.setBirthdate(getBirthdate());
		return user;
	}
	
	public UserDTO transformModelToDto(User user) {
		UserDTO dto = new UserDTO();
		dto.setUserId(user.getUserId());
		dto.setCompanyId(user.getCompanyId());
		dto.setEmail(user.getEmail());
		dto.setBirthdate(user.getBirthdate());
		return dto;
	}
	
}
