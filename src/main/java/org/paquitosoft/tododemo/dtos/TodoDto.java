package org.paquitosoft.tododemo.dtos;

import java.time.ZonedDateTime;

import org.paquitosoft.tododemo.models.Todo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TodoDto {
	
	private Long id;

	private String title;

	private String description;

	private ZonedDateTime startDate;

	private Boolean done;

	private Boolean favorite;

	// @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
	private CategoryDto category;

	public static Todo toEntity(TodoDto todoDto) {
		final Todo todo = new Todo();
		todo.setId(todoDto.getId());
		todo.setTitle(todoDto.getTitle());
		todo.setDescription(todoDto.getDescription());
		todo.setDone(todoDto.getDone());
		todo.setFavorite(todoDto.getFavorite());
		todo.setStartDate(todoDto.getStartDate());
		todo.setCategory(CategoryDto.toEntity(todoDto.getCategory()));

		return todo;
	}

	public static TodoDto fromEntity(Todo todo) {
		return TodoDto.builder()
			.id(todo.getId())
			.title(todo.getTitle())
			.description(todo.getDescription())
			.startDate(todo.getStartDate())
			.done(todo.getDone())
			.favorite(todo.getFavorite())
			.build();
	}
}
