package com.csomet.springboot.app.auth;

import org.springframework.util.Base64Utils;

public final class SecurityConst {
	
	
	public static final String SECRET_KEY =  Base64Utils.encodeToString("clave.secreta".getBytes());
	public static final long EXP_DATE = 14000000L;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER = "Authorization";

	
}
