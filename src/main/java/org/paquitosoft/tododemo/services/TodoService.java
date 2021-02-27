package org.paquitosoft.tododemo.services;

import java.util.List;

import org.paquitosoft.tododemo.dtos.TodoDto;

public interface TodoService {
	
	TodoDto save(TodoDto todo);

	List<TodoDto> findAll();

	TodoDto findById(Long id);

	List<TodoDto> findByCategory(Long categoryId);

	void delete(Long id);
}
