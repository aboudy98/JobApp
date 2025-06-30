package com.embarxk.JobApp.JobFolder;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    void createJob(Job job);
    Job findById(Long id);
    
}
