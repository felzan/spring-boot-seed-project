package com.project.seed.controller;

import com.project.seed.TokenUtils;
import com.project.seed.model.Token;
import com.project.seed.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

  private final TokenUtils tokenUtils;
  private final TokenRepository tokenRepository;

  @Autowired
  public ToDoController(TokenUtils tokenUtils, TokenRepository tokenRepository) {
    this.tokenUtils = tokenUtils;
    this.tokenRepository = tokenRepository;
  }

  @RequestMapping("/")
  public ResponseEntity doGet() throws Exception{
    String e = tokenUtils.encrypt("token#com#informacoes#secretas");
    Token token = new Token();
    token.setToken(e);
    return ResponseEntity.ok(tokenRepository.insert(token));
  }

}
