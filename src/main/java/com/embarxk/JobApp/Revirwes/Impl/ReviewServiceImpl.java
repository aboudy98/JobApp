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
}
