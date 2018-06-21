package com.skc.uaa;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author sitakant
 *
 */
@RestController
public class UserEndPoint {
	
	private static final String USER_NAME="admin";
	private static final String PASSWORD="admin";

	@GetMapping("/users")
	public Map<String,Object> getUserDetails(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException{
		Map<String,Object> responseMap = new HashMap<String,Object>();
		
		String _authHeader = httpServletRequest.getHeader("Authorization");
		if(_authHeader == null){
			return unauthorizedResponse(responseMap);
		}
		
		String _finalAuth = _authHeader.replace("Basic ", "");
		String credentials[] = new String(Base64.decodeBase64(_finalAuth)).split(":");
		if(credentials[0].equalsIgnoreCase(USER_NAME) && credentials[1].equalsIgnoreCase(PASSWORD)){
			responseMap.put("status", "AUTHORIZED");
			responseMap.put("message", "Valid user");
			responseMap.put("statusCode", "APP_200");
		}else{
			return unauthorizedResponse(responseMap);
		}
	
		return responseMap;
	}

	private Map<String, Object> unauthorizedResponse(Map<String, Object> responseMap) {
		responseMap.put("status", "UNAUTHORIZED");
		responseMap.put("message", "Invalid user");
		responseMap.put("statusCode", "APP_401");
		return responseMap;
	}
	
}
