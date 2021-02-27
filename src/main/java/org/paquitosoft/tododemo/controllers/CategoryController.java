package org.paquitosoft.tododemo.controllers;

import java.util.List;

import org.paquitosoft.tododemo.controllers.api.CategoryApi;
import org.paquitosoft.tododemo.dtos.CategoryDto;
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
public class CategoryController implements CategoryApi {

	@Autowired
	private TodoService todoService;

	@Autowired
	private CategoryService categoryService;

	@Override
	public ResponseEntity<CategoryDto> createCategory(CategoryDto category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<CategoryDto> updateCategory(CategoryDto category) {
		return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategories() {
		return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(Long id) {
		return new ResponseEntity<>(todoService.findByCategory(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(Long userId) {
		return new ResponseEntity(categoryService.getAllTodoByCategoriesForToday(userId), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<CategoryDto>> getAllCategoriesByUserId(Long id) {
		return new ResponseEntity<>(categoryService.findAllByUserId(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<CategoryDto> getCategory(Long id) {
		return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity deleteCategory(Long id) {
		categoryService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
	}
	
}
