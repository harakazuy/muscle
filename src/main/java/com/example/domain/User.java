package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String name;
	@JsonIgnore
	private String password;
	private String role;
	private boolean isDeleted;
}
