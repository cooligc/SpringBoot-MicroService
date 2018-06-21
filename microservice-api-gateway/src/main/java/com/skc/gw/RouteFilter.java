/**
 * 
 */
package com.skc.gw;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * @author sitakant
 *
 */
public class RouteFilter extends ZuulFilter {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Override
	public String filterType() {
		return "route";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		
		System.out.println("Printing headers ----");
		
		Enumeration<String> headerName =  ctx.getRequest().getHeaderNames();
		
		while(headerName.hasMoreElements()){
			String _headerName = headerName.nextElement();
			headers.add(_headerName, ctx.getRequest().getHeader(_headerName));
			System.out.println(_headerName+"\t\t"+ ctx.getRequest().getHeader(_headerName));
		}
		
		for (Entry<String, String> entry : ctx.getZuulRequestHeaders().entrySet()) {
			headers.add(entry.getKey(), entry.getValue());
			System.out.println(entry.getKey()+"\t\t"+ entry.getValue());
		}
		System.out.println("Header set ---->");
		HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

		
        Map<String,String> response = restTemplate.exchange("http://UAA-SERVICE/users",HttpMethod.GET,entity,Map.class).getBody();
        if(response.get("status").equalsIgnoreCase("UNAUTHORIZED")) {
            ctx.setSendZuulResponse(false); //This makes request not forwarding to micro services
            ctx.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
            ctx.setResponseBody(response.toString());
            ctx.getResponse().setContentType("application/json");
        }
		return null;
	}
}
