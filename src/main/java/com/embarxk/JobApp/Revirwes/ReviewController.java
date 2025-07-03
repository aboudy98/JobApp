package com.embarxk.JobApp.Revirwes;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/companies/{companyId}")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findReviewsByCompanyId(@PathVariable Long companyId){
        List<Review> reviews = reviewService.getAllReview(companyId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);

    }

    @PostMapping("/reviews")
    public ResponseEntity<Review> addReview(@RequestBody Review review, @PathVariable Long companyId) throws Exception {
        Review createReview = reviewService.createReview(review, companyId);
        return new ResponseEntity<>(createReview,HttpStatus.CREATED);
    }
    
}
