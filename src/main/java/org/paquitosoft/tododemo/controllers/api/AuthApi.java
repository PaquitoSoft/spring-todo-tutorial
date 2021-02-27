package org.paquitosoft.tododemo.controllers.api;

import org.paquitosoft.tododemo.dtos.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.paquitosoft.tododemo.utils.Constants.APP_ROOT;

@Api("authApi")
public interface AuthApi {
	
	@ApiOperation(
		value="Login user", notes="Creates a new user", response=UserDto.class
	)
	@ApiResponses(
		value={ @ApiResponse(code=201, message="The connected user.")}
		)
	@PostMapping(
		value = APP_ROOT + "/auth/login",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	ResponseEntity<UserDto> loginUser(
		@ApiParam(value="User DTO", required=true) @RequestBody UserDto user
	);
}
