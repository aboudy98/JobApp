package com.embarxk.JobApp.Revirwes.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarxk.JobApp.Company.Company;
import com.embarxk.JobApp.Company.CompanyRepository;
import com.embarxk.JobApp.Revirwes.Review;
import com.embarxk.JobApp.Revirwes.ReviewRepository;
import com.embarxk.JobApp.Revirwes.ReviewService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final CompanyRepository companyRepository;

    @Override
    public List<Review> getAllReview(Long companyId) {
        return reviewRepository.findByCompanyId(companyId);
    }

    @Override
    public Review createReview(Review review, Long companyId) throws Exception {
        Company newCompany = companyRepository.findById(companyId).orElseThrow(()-> new Exception("Company not found with id: " + companyId));
        review.setCompany(newCompany);
        return reviewRepository.save(review);
    }

    @Override
    public Review getReviewById(Long reviewId, Long companyId) throws Exception {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new Exception("Company not found with id: " + companyId));
                Review review = company.getReviews().stream()
                .filter(r -> r.getId().equals(reviewId))
                .findFirst()
                .orElseThrow(() -> new Exception("Review not found with id: " + reviewId));
        return review;
    }

    @Override
    public Review UpdateReviewByComapnyId(Long reviewId, Long companyId, Review review) throws Exception {

        if(companyId == null){
            throw new Exception("Company not exist");
        }
       Review newReview = getReviewById(reviewId, companyId);
       
        newReview.setTitle(review.getTitle());
        newReview.setDescription(review.getDescription());
        newReview.setRating(review.getRating());
        System.out.println("Review updated: " + newReview);
        return reviewRepository.save(newReview);
    }

    @Override
    public void deleteReview(Long reviewId, Long companyId) throws Exception {
       Review review = getReviewById(reviewId, companyId);
       if (review == null) {
            throw new Exception("Review not found with id: " + reviewId);
        }
        reviewRepository.delete(review);
       
    }
}
