package com.project.seed.controller;

import com.project.seed.model.Rating;
import com.project.seed.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ratings")
public class RatingController {

  private final RatingService service;

  @Autowired
  public RatingController(RatingService service) {
    this.service = service;
  }

  @PostMapping("/")
  public ResponseEntity postRating(@RequestBody Rating rating) throws Exception{
    return ResponseEntity.ok(service.insert(rating));
  }

  @GetMapping()
  public ResponseEntity getRating() {
    return ResponseEntity.ok("ratings");
  }

}
