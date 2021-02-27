package org.paquitosoft.tododemo.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.paquitosoft.tododemo.dtos.CategoryDto;
import org.paquitosoft.tododemo.dtos.TodoDto;
import org.paquitosoft.tododemo.exceptions.ErrorCodes;
import org.paquitosoft.tododemo.exceptions.InvalidEntityException;
import org.paquitosoft.tododemo.exceptions.EntityNotFoundException;
import org.paquitosoft.tododemo.models.Category;
import org.paquitosoft.tododemo.models.Todo;
import org.paquitosoft.tododemo.repositories.CategoryRepository;
import org.paquitosoft.tododemo.repositories.TodoRepository;
import org.paquitosoft.tododemo.services.TodoService;
import org.paquitosoft.tododemo.validators.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TodoServiceImpl implements TodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public TodoDto save(TodoDto todo) {
		List<String> errors = TodoValidator.validateTodo(todo);
		if (!errors.isEmpty()) {
			log.error("Todo is not valid {}", todo);
			throw new InvalidEntityException("Todo is not valid", ErrorCodes.TODO_NOT_VALID, errors);
		}
		return TodoDto.fromEntity(todoRepository.save(TodoDto.toEntity(todo)));
	}

	@Override
	public List<TodoDto> findAll() {
		return todoRepository.findAll()
			.stream()
			.map(TodoDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Override
	public TodoDto findById(Long id) {
		if (id == null) {
			log.error("Todo ID is null");
			return null;
		}
		
		final Long categoryId = categoryRepository.findCategoryByTodoId(id);
		Category category = new Category();
		category.setId(categoryId);

		final Optional<Todo> todo = todoRepository.findById(id);
		todo.ifPresent(value -> value.setCategory(category));

		final TodoDto todoDto = TodoDto.fromEntity(todo.get());
		CategoryDto categoryDto = CategoryDto.fromEntity(category);
		todoDto.setCategory(categoryDto);

		return Optional.of(todoDto)
			.orElseThrow(() -> new EntityNotFoundException("No Todo found with ID = " + id, ErrorCodes.TODO_NOT_FOUND));
	}

	@Override
	public List<TodoDto> findByCategory(Long categoryId) {
		return todoRepository.findTodoByCategoryId(categoryId)
			.stream()
			.map(TodoDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Override
	public void delete(Long id) {
		if (id == null) {
			log.error("Todo ID is null");
			return;
		}
		todoRepository.deleteById(id);
	}
	
}
