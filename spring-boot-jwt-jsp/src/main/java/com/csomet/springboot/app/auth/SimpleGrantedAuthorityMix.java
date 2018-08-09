package com.csomet.springboot.app.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityMix {
	
	@JsonCreator
	public SimpleGrantedAuthorityMix(@JsonProperty("authority") String role) {}

}
