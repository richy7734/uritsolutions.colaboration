package com.niit.uritsolution.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.niit.uritsolution.dao.JobDao;
import com.niit.uritsolution.model.Job;
import com.niit.uritsolution.model.JobApplication;

@RestController
public class JobController {

	@Autowired
	JobDao jobDao;
	
	@RequestMapping("add/job")
	public ResponseEntity<Job> addJob(@RequestBody Job job){
		jobDao.addJob(job);
		return new ResponseEntity<Job>(HttpStatus.OK);
	}
	
	@RequestMapping("list/jobs")
	public List<Job> listJobs(){
		return jobDao.listJobs();
		
	}
	
	@RequestMapping("add/job/application")
	public ResponseEntity<JobApplication> addJobApplication(@RequestBody JobApplication jobApplication){
		jobDao.applyJob(jobApplication);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping("get/applications/{jobId}")
	public List<JobApplication> getListApplication(@PathVariable("jobId") int jobId){
		return jobDao.listApplications(jobId);
	}
}
