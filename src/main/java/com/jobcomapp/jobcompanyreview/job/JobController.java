package com.jobcomapp.jobcompanyreview.job;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {

    private JobService jobService;

    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping("/jobs") // GET /jobs
    public ResponseEntity<List<Job>> findall(){
        return ResponseEntity.ok(jobService.findAll());
    }
    @GetMapping("/jobs/{id}")
    public ResponseEntity<Job> getJobById(@PathVariable Long id){
        Job job=jobService.getJobById(id);
        if(job != null)
            return new ResponseEntity<>(job, HttpStatusCode.valueOf(200));
        return new ResponseEntity<>(HttpStatusCode.valueOf(404));
    }
    @PostMapping("jobs")
    public ResponseEntity<String> createJob(@RequestBody Job job) //String -> ResponseEntity<String>
    {
        jobService.createJob(job);
        return new ResponseEntity<>("Success", HttpStatusCode.valueOf(200));
    }


    @DeleteMapping("jobs/{id}")
public ResponseEntity<String> deleteJobById(@PathVariable Long id)
{
   boolean deleted = jobService.deleteJobById(id);
   if(deleted)
        return ResponseEntity.ok(String.format("Job with id %s deleted sucdessfully",id));
   return new ResponseEntity<>(HttpStatusCode.valueOf(404));
}

//@PutMapping("jobs/{id}")
    @RequestMapping(value = "jobs/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateJobById(@PathVariable Long id, @RequestBody Job updatedJob){
        boolean updated=jobService.updateJobById(id,updatedJob);
        if(updated)
            return ResponseEntity.ok(String.format("Job with id %s was updated successfully",id));
        return new ResponseEntity<>(HttpStatusCode.valueOf(403));
    }
    //GET /jobs/{id}:
//POST /jobs
//PUT /jobs/{id}:
//DELETE /jobs/{id}:
//GET /jobs/{id}/company
}
