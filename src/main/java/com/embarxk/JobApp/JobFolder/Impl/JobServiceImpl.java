package com.embarxk.JobApp.JobFolder.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.embarxk.JobApp.JobFolder.Job;
import com.embarxk.JobApp.JobFolder.JobRepository;
import com.embarxk.JobApp.JobFolder.JobService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService{

    private final JobRepository jobRepository;
 

    @Override
    public List<Job> findAll() {
       return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
       jobRepository.save(job);
        
        
    }

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteJob(Long id) throws Exception {
        Job jobById = findById(id);
        if(jobById == null){
            throw new Exception("Job not found with id: " + id);
        }
        jobRepository.deleteById(id);
    }

    @Override
    public Job updateJob(Long id, Job job) throws Exception {
       
        Job existingJob = findById(id);
        if(existingJob == null) {
            throw new Exception("Job not found with id: " + id);
        }
        
        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setMinSalary(job.getMinSalary());
        existingJob.setMaxSalary(job.getMaxSalary());
        existingJob.setLocation(job.getLocation());
        jobRepository.save(existingJob);
        return existingJob;
    }
    
}
