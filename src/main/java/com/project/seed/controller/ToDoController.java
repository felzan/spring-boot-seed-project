package com.project.seed.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDoController {

  @RequestMapping("/")
  public String doGet() {
    return "felzan";
  }

}
