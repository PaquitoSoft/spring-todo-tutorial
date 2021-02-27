package org.paquitosoft.tododemo.services.impl;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.paquitosoft.tododemo.dtos.CategoryDto;
import org.paquitosoft.tododemo.exceptions.EntityNotFoundException;
import org.paquitosoft.tododemo.exceptions.ErrorCodes;
import org.paquitosoft.tododemo.exceptions.InvalidEntityException;
import org.paquitosoft.tododemo.repositories.CategoryRepository;
import org.paquitosoft.tododemo.services.CategoryService;
import org.paquitosoft.tododemo.validators.CategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public CategoryDto save(CategoryDto category) {
		List<String> errors = CategoryValidator.validateCategory(category);
		if (!errors.isEmpty()) {
			log.error("Category is not valid {}", category);
			throw new InvalidEntityException("Category is not valid", ErrorCodes.CATEGORY_NOT_VALID, errors);
		}
		return CategoryDto.fromEntity(categoryRepository.save(CategoryDto.toEntity(category)));
	}

	@Override
	public List<CategoryDto> findAll() {
		return categoryRepository.findAll()
			.stream()
			.map(CategoryDto::fromEntity)
			.collect(Collectors.toList());
	}

	@Override
	public CategoryDto findById(Long id) {
		if (id == null) {
			log.error("Category ID is null");
			return null;
		}
		return categoryRepository.findById(id)
			.map(CategoryDto::fromEntity)
			.orElseThrow(() -> new EntityNotFoundException("No Category found with Id = " + id, ErrorCodes.USER_NOT_FOUND));
	}

	@Override
	public List<CategoryDto> findAllByUserId(Long userId) {
		return categoryRepository.findCategoryByUserId(userId)
			.stream()
			.map(CategoryDto::fromEntity)
			.collect(Collectors.toList());
		}

	@Override
	public void delete(Long id) {
		if (id == null) {
			log.error("Category ID is null");
		}
		categoryRepository.deleteById(id);
	}

	@Override
	public List<CategoryDto> getAllTodoByCategoriesForToday(Long userId) {
		return categoryRepository.getAllTodoByCategoriesForToday(
			ZonedDateTime.now().withHour(0).withMinute(0),
			ZonedDateTime.now().withHour(23).withMinute(59),
			userId
		).stream()
		.map(CategoryDto::fromEntity)
		.collect(Collectors.toList());
	}
	
}
