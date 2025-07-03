package com.embarxk.JobApp.Revirwes;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReview(Long companyId);
    Review createReview(Review review, Long companyId) throws Exception;
    
}
