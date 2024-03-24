package com.jobcomapp.jobcompanyreview.job.Impl;

import com.jobcomapp.jobcompanyreview.job.Job;
import com.jobcomapp.jobcompanyreview.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class JobServiceImpl implements JobService {
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
    public Job getJobById(Long id){
        return jobs.get((int) (id-1));
    }

    @Override
    public boolean deleteJobById(Long id) {
        Iterator<Job> it = jobs.iterator();
        while(it.hasNext())
        {
            Job job = it.next();
            if(job.getId().equals(id)) {
                it.remove();
                return true;
            }
        }
        return false;
    }
@Override
    public boolean updateJobById(Long id, Job updateJob)
    {
       for(Job job : jobs)
        {
            if(job.getId().equals(id))
            {
                job.setJobdesc(updateJob.getJobdesc());
                job.setTitle(updateJob.getTitle());
                job.setMaxSalary(updateJob.getMaxSalary());
                job.setMinSalary(updateJob.getMinSalary());
                job.setLocation(updateJob.getLocation());
                return true;
            }
        }
        return false;
    }
}

