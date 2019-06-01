package com.project.seed.service;

import com.project.seed.model.Rating;
import com.project.seed.repository.RatingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingService {


    private final RatingRepository repository;

    @Autowired
    public RatingService(RatingRepository repository) {
        this.repository = repository;
    }

    public Integer insert(Rating rating) throws Exception {
        return repository.insert(rating);
    }
}
