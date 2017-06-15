package com.niit.uritsolution.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.uritsolution.dao.ForumDao;
import com.niit.uritsolution.model.Forum;
import com.niit.uritsolution.model.People;

@Repository
@Transactional
public class ForumDaoImpl implements ForumDao {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public People createForum(People people) {

		sessionFactory.getCurrentSession().persist(people);
		return people;
	}

	@Override
	public void joinForum(People people) {
		sessionFactory.getCurrentSession().saveOrUpdate(people);

	}

	@Override
	public List<People> listPeople() {

		return sessionFactory.getCurrentSession().createQuery("FROM People WHERE  enabled = '"+0+"'", People.class).getResultList();
	}

	@Override
	public People getPeopleById(int id) {
		
		return sessionFactory.getCurrentSession().get(People.class, id);
	}

	@Override
	public Forum getGroupByName(String grpName) {

		return sessionFactory.getCurrentSession().createQuery("FROM Group WHERE name = '"+grpName+"'", Forum.class).getSingleResult();
	}

	@Override
	public People getPeopleByIdAndGrp(int grpId, Forum group) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM People WHERE userId = '"+grpId+"' AND group = '"+group+"'",People.class).getSingleResult();
	}

	@Override
	public Forum createGroup(Forum group) {
		sessionFactory.getCurrentSession().persist(group);
		return group;
	}

	@Override
	public List<Forum> listForum() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Forum", Forum.class).list();
	}

	@Override
	public Forum getForumById(int frmId) {
		
		return sessionFactory.getCurrentSession().get(Forum.class, frmId);
	}

	@Override
	public List<People> getPeopleForApproval(int grpId) {

		return sessionFactory.getCurrentSession().createQuery("FROM People WHERE grpId = '"+grpId+"' AND enabled = '"+0+"' AND role != 'ADMIN'", People.class).list();
	}

	@Override
	public List<People> getPeopleByCurrentuser(int userId) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM People WHERE userId = '"+userId+"'", People.class).getResultList();
	}

	@Override
	public void acceptRequest(People peole) {
		
		sessionFactory.getCurrentSession().update(peole);
	}

	@Override
	public boolean checkPeople(int frmId, int userId) {
		try{
			sessionFactory.getCurrentSession().createQuery("FROM People WHERE grpId ='"+frmId+"' AND usrId = '"+userId+"'",People.class).getSingleResult();
			return true;
		}catch (Exception e) {
			return false;
		}
	}

}
