package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.Job;
import com.niit.uritsolution.model.JobApplication;

public interface JobDao {

	public void addJob(Job job);
	public void applyJob(JobApplication application);
	public List<JobApplication> listApplications(int jobId);
	public List<Job> listJobs();
}
