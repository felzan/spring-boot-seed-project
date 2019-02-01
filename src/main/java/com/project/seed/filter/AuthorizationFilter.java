package com.project.seed.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class AuthorizationFilter extends GenericFilterBean {

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest httpRequest = (HttpServletRequest)request;
    final HttpServletResponse httpResponse = (HttpServletResponse)response;

    final String auth = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
    if (auth == null || !auth.startsWith("Bearer")) {
      httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not authorized");
    }
    chain.doFilter(request, response);
  }

}
