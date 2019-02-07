package com.jhallat.simple.kanban.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class CurrentUserUtility {

	@Autowired
	private JwtTokenProvider tokenProvider;	
	
	public long getCurrentUserId(HttpHeaders headers) {
		
		String jwt = getJwtFromHeaders(headers);
		if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			return tokenProvider.getUserIdFromJWT(jwt);
		} else {
			return 0;
		}
	}
	
	private String getJwtFromHeaders(HttpHeaders headers) {
		String bearerToken = headers.getFirst("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}	
}
