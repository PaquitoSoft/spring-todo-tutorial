package org.paquitosoft.tododemo.services;

import java.util.List;

import org.paquitosoft.tododemo.dtos.CategoryDto;

public interface CategoryService {
	
	CategoryDto save(CategoryDto category);

	List<CategoryDto> findAll();

	CategoryDto findById(Long id);

	List<CategoryDto> findAllByUserId(Long userId);

	void delete(Long id);

	List<CategoryDto> getAllTodoByCategoriesForToday(Long userId);
}
