package com.embarxk.JobApp.JobFolder.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.embarxk.JobApp.JobFolder.Job;
import com.embarxk.JobApp.JobFolder.JobService;

@Service
public class JobServiceImpl implements JobService{

    private List<Job> jobs = new ArrayList<>();
    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
       return jobs;
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobs.add(job);
        
    }

    @Override
    public Job findById(Long id) {
        return jobs.stream().filter(job -> job.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteJob(Long id) throws Exception {
        Job jobById = findById(id);
        if(jobById == null){
            throw new Exception("Job not found with id: " + id);
        }
        jobs.removeIf(job -> job.getId().equals(id));
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
        return existingJob;
    }
    
}
