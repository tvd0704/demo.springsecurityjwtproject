package com.example.demo.springsecurityjwtproject.controller;

import com.example.demo.springsecurityjwtproject.dto.ReviewDto;
import com.example.demo.springsecurityjwtproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/created/{pokemonId}/review")
    public ResponseEntity<ReviewDto> createReview (@PathVariable int pokemonId, @RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("/getReviews/pokemon/{pokemonId}")
    public ResponseEntity<List<ReviewDto>> getReviews(@PathVariable int pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewByPokemonId(pokemonId),HttpStatus.OK);
    }

    @GetMapping("/review/{reviewId}/pokemon/{pokemonId}")
    public ResponseEntity<ReviewDto> getReview(@PathVariable int reviewId, @PathVariable int pokemonId) {
        return new ResponseEntity<>(reviewService.getReviewById(reviewId,pokemonId),HttpStatus.OK);
    }

    @PutMapping("/updated/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable int pokemonId,
                                                  @PathVariable int reviewId,@RequestBody ReviewDto reviewDto) {
        return new ResponseEntity<>(reviewService.updateReview(pokemonId, reviewId, reviewDto),HttpStatus.OK);
    }

    @DeleteMapping("/deleted/pokemon/{pokemonId}/review/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable int reviewId, @PathVariable int pokemonId) {
        reviewService.deleteReview(pokemonId,reviewId);
        return new ResponseEntity<>("delete review by id done",HttpStatus.OK);
    }

}
