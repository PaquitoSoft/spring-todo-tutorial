package org.paquitosoft.tododemo.repositories;

import java.util.List;

import org.paquitosoft.tododemo.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
	
	List<Todo> findTodoByCategoryId(Long categoryId);

}
