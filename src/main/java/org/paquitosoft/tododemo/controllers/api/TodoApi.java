package org.paquitosoft.tododemo.controllers.api;

import java.util.List;

import javax.websocket.server.PathParam;

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
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import static org.paquitosoft.tododemo.utils.Constants.APP_ROOT;

@Api(APP_ROOT + "/todos")
public interface TodoApi {
	
	@ApiOperation(
		value = "Create To do",
		notes = "Creates a new to do ",
		response = TodoDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 201, message = "The newly created To do.")
    })
	@PostMapping(
		value = APP_ROOT + "/todos/create",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<TodoDto> createTodo(
		@ApiParam(value = "Todo DTO", required = true) @RequestBody TodoDto todoDto
	);


    @ApiOperation(
		value = "Update Todo",
		notes = "Updates an existing Todo ",
		response = TodoDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The newly created Todo.")
    })
	@PatchMapping(
		value = APP_ROOT + "/todos/update",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<TodoDto> updateTodo(
		@ApiParam(value = "Todo DTO", required = true) @RequestBody TodoDto todoDto
	);


    @ApiOperation(
		value = "Todo Details",
		notes = "Returns the list of the Todos",
		responseContainer = "List<TodoDto>"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "List of the Todos"),
    })
	@GetMapping(
		value = APP_ROOT + "/todos/all",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<TodoDto>> getAllTodos();


    @ApiOperation(
		value = "Todo Details",
		notes = "Returns the Todo",
		response = TodoDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The Todo"),
		@ApiResponse(code = 404, message = "Todo not found")
    })
	@GetMapping(
		value = APP_ROOT + "/todos/{todoId:.+}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<TodoDto> getTodo(
		@ApiParam(value = "Todo ID", required = true) @PathParam(value = "todoId") Long todoId
	);


    @ApiOperation(
		value = "Delete Todo",
		notes = "Deletes a Todo by ID",
		response = TodoDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The Todo deleted"),
		@ApiResponse(code = 404, message = "Todo not found")
    })
	@DeleteMapping(
		value = APP_ROOT + "/todos/delete/{id:.+}"
	)
	ResponseEntity deleteTodo(
		@ApiParam(value = "Todo ID", required = true) Long todoId
	);
}
