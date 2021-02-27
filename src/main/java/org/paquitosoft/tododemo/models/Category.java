package org.paquitosoft.tododemo.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@ManyToOne
	@JoinColumn(name="userId")
	@JsonIgnore
	private User user;

	@OneToMany(mappedBy="category", fetch=FetchType.EAGER)
	private List<Todo> todoList;

}
