package com.project.seed.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class ContentTypeFilter extends GenericFilterBean {

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
    final HttpServletRequest req = (HttpServletRequest)request;
    final HttpServletResponse res = (HttpServletResponse)response;

    if (req.getContentType() == null || !req.getContentType().equals(MediaType.APPLICATION_JSON_VALUE)) {
      res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Content-Type MUST be application/json");
    }
    chain.doFilter(request, response);
  }
}
