package com.example.demo.springsecurityjwtproject.service;

import com.example.demo.springsecurityjwtproject.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId,ReviewDto reviewDto);
    List<ReviewDto> getReviewByPokemonId(int id);
    ReviewDto getReviewById(int reviewId,int pokemonId);
    ReviewDto updateReview(int pokemonId,int reviewId, ReviewDto reviewDto);
    void deleteReview(int pokemonId,int reviewId);
}
