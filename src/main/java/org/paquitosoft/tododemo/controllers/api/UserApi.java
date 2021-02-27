package org.paquitosoft.tododemo.controllers.api;

import java.util.List;

import org.paquitosoft.tododemo.dtos.UserDto;
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

@Api(APP_ROOT + "/users")
public interface UserApi {

	@ApiOperation(
		value = "Create user",
		notes = "Creates a new user ",
		response = UserDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 201, message = "The newly created user.")
    })
	@PostMapping(
		value = APP_ROOT + "/users/create",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<UserDto> createUser(
		@ApiParam(value = "User DTO", required = true) @RequestBody UserDto userDto
	);


    @ApiOperation(
		value = "Update user",
		notes = "Updates an existing user ",
		response = UserDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The newly created user.")
    })
	@PatchMapping(
		value = APP_ROOT + "/users/{id}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<UserDto> updateUser(
		@ApiParam(value = "User ID", required = true) Long id,
		@ApiParam(value = "User DTO", required = true) @RequestBody UserDto userDto
	);


    @ApiOperation(
		value = "User Details",
		notes = "Returns the list of the users",
		responseContainer = "List<UserDto>"
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "List of the users"),
    })
	@GetMapping(
		value = APP_ROOT + "/users/all",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<List<UserDto>> getAllUsers();


    @ApiOperation(
		value = "User Details",
		notes = "Returns the list of the users",
		response = UserDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The user"),
		@ApiResponse(code = 404, message = "User not found")
    })
	@GetMapping(
		value = APP_ROOT + "/users/{id:.+}",
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<UserDto> getUser(
		@ApiParam(value = "User ID", required = true) Long id
	);


    @ApiOperation(
		value = "Delete a user",
		notes = "Deletes a user by ID",
		response = UserDto.class
	)
    @ApiResponses(value = {
		@ApiResponse(code = 200, message = "The user"),
		@ApiResponse(code = 404, message = "User not found")
    })
	@DeleteMapping(
		value = APP_ROOT + "/users/delete/{id:.+}"
	)
	ResponseEntity deleteUser(
		@ApiParam(value = "User ID", required = true) Long id
	);
}
