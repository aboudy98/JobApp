package com.embarxk.JobApp.Revirwes;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);
    Review createReview(Review review, Long companyId) throws Exception;
    Review getReviewById(Long reviewId, Long companyId) throws Exception;
    Review UpdateReviewByComapnyId(Long reviewId, Long companyId, Review review) throws Exception;
    void deleteReview(Long reviewId, Long companyId) throws Exception;
    
}
