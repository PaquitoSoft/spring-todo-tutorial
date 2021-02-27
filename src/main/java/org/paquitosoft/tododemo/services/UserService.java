package org.paquitosoft.tododemo.services;

import java.util.List;

import org.paquitosoft.tododemo.dtos.UserDto;

public interface UserService {
	
	UserDto save(UserDto user);

	List<UserDto> findAll();

	UserDto findById(Long id);

	void delete(Long id);

	UserDto login(UserDto user);
}
