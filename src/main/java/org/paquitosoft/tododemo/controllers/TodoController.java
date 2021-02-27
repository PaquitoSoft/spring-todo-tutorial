package org.paquitosoft.tododemo.controllers;

import java.util.List;

import org.paquitosoft.tododemo.controllers.api.TodoApi;
import org.paquitosoft.tododemo.dtos.TodoDto;
import org.paquitosoft.tododemo.services.CategoryService;
import org.paquitosoft.tododemo.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class TodoController implements TodoApi {

	@Autowired
	private TodoService todoService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<TodoDto> createTodo(TodoDto todoDto) {
		return new ResponseEntity<>(todoService.save(todoDto), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<TodoDto> updateTodo(TodoDto todoDto) {
		return new ResponseEntity<>(todoService.save(todoDto), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TodoDto>> getAllTodos() {
		return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<TodoDto> getTodo(Long todoId) {
		return  new ResponseEntity<>(todoService.findById(todoId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteTodo(Long todoId) {
		todoService.delete(todoId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
}
