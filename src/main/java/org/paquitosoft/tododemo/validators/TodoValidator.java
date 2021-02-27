package org.paquitosoft.tododemo.validators;

import java.util.ArrayList;
import java.util.List;

import org.paquitosoft.tododemo.dtos.TodoDto;
import org.springframework.util.StringUtils;

public class TodoValidator {
	
	public static List<String> validateTodo(TodoDto todoDto) {
		List<String> errors = new ArrayList<String>();

		if (todoDto == null) {
			errors.add("Please fill the name");
			errors.add("Please fill the description");
			errors.add("Please select a category");
			return errors;
		}

		if (StringUtils.isEmpty(todoDto.getTitle())) {
			errors.add("Please fill the name");
		}

		if (StringUtils.isEmpty(todoDto.getDescription())) {
			errors.add("Please fill the description");
		}

		if (todoDto.getCategory() == null || todoDto.getCategory().getId() == null) {
			errors.add("Please select a category");
		}

		return errors;
	}

}
