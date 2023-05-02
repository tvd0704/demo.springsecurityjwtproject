package com.example.demo.springsecurityjwtproject.service.serviceIml;

import com.example.demo.springsecurityjwtproject.dto.ReviewDto;
import com.example.demo.springsecurityjwtproject.exception.PokemonNotFoundException;
import com.example.demo.springsecurityjwtproject.exception.ReviewNotFoundException;
import com.example.demo.springsecurityjwtproject.model.Pokemon;
import com.example.demo.springsecurityjwtproject.model.Review;
import com.example.demo.springsecurityjwtproject.repository.PokemonRepository;
import com.example.demo.springsecurityjwtproject.repository.ReviewRepository;
import com.example.demo.springsecurityjwtproject.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceIml implements ReviewService {
    @Autowired
    private PokemonRepository pokemonRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        review.setPokemon(pokemon);
        return mapToDto(reviewRepository.save(review));
    }

    @Override
    public List<ReviewDto> getReviewByPokemonId(int id) {
        List<Review> reviewList = reviewRepository.findByPokemonId(id);
        return reviewList.stream().map(p ->mapToDto(p)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated review not found"));
        if(review.getPokemon().getId() != pokemon.getId()) {
            throw  new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        return mapToDto(review);

    }

    @Override
    public ReviewDto updateReview(int pokemonId, int reviewId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated review not found"));
        if(review.getPokemon().getId()!= pokemon.getId()) {
            throw new ReviewNotFoundException("this review does not belong to a pokemon");
        }
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStarts(reviewDto.getStars());
        return mapToDto(reviewRepository.save(review));
    }

    @Override
    public void deleteReview(int pokemonId, int reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(()-> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(()-> new ReviewNotFoundException("Review with associated review not found"));
        if(review.getPokemon().getId()!= pokemon.getId()) {
            throw new ReviewNotFoundException("this review does not belong to a pokemon");
        }
        reviewRepository.delete(review);
    }

    private ReviewDto mapToDto (Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStarts());
        return reviewDto;
    }

    private Review mapToEntity (ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStarts(reviewDto.getStars());
        return review;
    }
}
