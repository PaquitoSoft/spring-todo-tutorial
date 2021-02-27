package org.paquitosoft.tododemo.controllers.api;

import java.util.List;

import javax.websocket.server.PathParam;

import org.paquitosoft.tododemo.dtos.CategoryDto;
import org.paquitosoft.tododemo.dtos.TodoDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.paquitosoft.tododemo.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/categories")
public interface CategoryApi {
	
	@ApiOperation(
		value = "Create category",
		notes = "Creates a new category",
		response = CategoryDto.class
	)
	@ApiResponses(
		@ApiResponse(code = 201, message = "The newly created category.")
	)
	@PostMapping(
		value = APP_ROOT + "/categories/create",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<CategoryDto> createCategory(
		@ApiParam(value = "Category DTO", required = true) @RequestBody CategoryDto category
	);


	@ApiOperation(
		value = "Update category",
		notes = "Updates an existing category",
		response = CategoryDto.class
	)
	@ApiResponses(
		@ApiResponse(code = 200, message = "The updated category.")
	)
	@PatchMapping(
		value = APP_ROOT + "/categories/create",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<CategoryDto> updateCategory(
		@ApiParam(value = "Category DTO", required = true) @RequestBody CategoryDto category
	);


	@ApiOperation(
		value = "Categories list",
		notes = "Get all available categories",
		responseContainer = "List<CategoryDto>"
	)
	@ApiResponses(
		@ApiResponse(code = 200, message = "Categories list.")
	)
	@GetMapping(
		value = APP_ROOT + "/categories/all",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<CategoryDto>> getAllCategories();


	@ApiOperation(
		value = "Todo details by category ID",
		notes = "Returns the list of the Todo of a selected category",
		responseContainer = "List<TodoDto>"
	)
	@ApiResponses(
		@ApiResponse(code = 200, message = "List of todos.")
	)
	@GetMapping(
		value = APP_ROOT + "/categories/todos/{id:.+}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<TodoDto>> getAllTodoByCategoriesId(
		@ApiParam(value = "Category ID", required = true) Long id
	);


    @ApiOperation(
		value = "List of all categories and Todo for today",
		notes = "Returns the list of the Todo of a selected category",
		responseContainer = "List<TodoDto>"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "List of the Todos"),
    })
	@GetMapping(
		value = APP_ROOT + "/categories/todos/today/{userId:.+}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<TodoDto>> getAllTodoByCategoriesForToday(
		@ApiParam(value = "User ID", required = true) @PathParam("userId") Long userId
	);


    @ApiOperation(
		value = "Category Details by user ID",
		notes = "Returns the list of the categories of a selected user",
		responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "List of the categories"),
    })
	@GetMapping(
		value = APP_ROOT + "/categories/users/{id}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<CategoryDto>> getAllCategoriesByUserId(
		@ApiParam(value = "User ID", required = true) Long id
	);


    @ApiOperation(
		value = "Category Details",
		notes = "Returns the list of the users",
		response = CategoryDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The Category"),
		@ApiResponse(code = 404, message = "Category not found")
    })
	@GetMapping(
		value = APP_ROOT + "/categories/{id:.+}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<CategoryDto> getCategory(
		@ApiParam(value = "Category ID", required = true) @PathParam(value = "id") Long id
	);


    @ApiOperation(
		value = "Delete category",
		notes = "Deletes a category by ID"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The category deleted"),
		@ApiResponse(code = 404, message = "Category not found")
    })
	@DeleteMapping(
		value = APP_ROOT + APP_ROOT + "/categories/delete/{id:.+}"
	)
	ResponseEntity deleteCategory(
		@ApiParam(value = "Category ID", required = true) Long id
	);
}
