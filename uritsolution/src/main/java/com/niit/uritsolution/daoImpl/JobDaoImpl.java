package com.niit.uritsolution.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.uritsolution.dao.JobDao;
import com.niit.uritsolution.model.Job;
import com.niit.uritsolution.model.JobApplication;

@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addJob(Job job) {
		
		sessionFactory.getCurrentSession().save(job);
	}

	@Override
	public void applyJob(JobApplication application) {
		
		sessionFactory.getCurrentSession().save(application);

	}

	@Override
	public List<JobApplication> listApplications(int jobId) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM JobApplication WHERE jobId = '"+jobId+"'",JobApplication.class).getResultList();
	}

	@Override
	public List<Job> listJobs() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Job",Job.class).list();
	}

}
