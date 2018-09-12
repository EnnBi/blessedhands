package com.example.medico.api.model;

import java.util.ArrayList;
import java.util.List;

public class Token {

	private String token;
	private List<String> role = new ArrayList<>();

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}
	

}
