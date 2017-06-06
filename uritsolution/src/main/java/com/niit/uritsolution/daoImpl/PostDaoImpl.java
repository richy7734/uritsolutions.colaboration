package com.niit.uritsolution.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.uritsolution.dao.PostDao;
import com.niit.uritsolution.model.Comment;
import com.niit.uritsolution.model.Post;

@Repository("PostDao")
@Transactional
public class PostDaoImpl implements PostDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	public List<Post> getPost() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Post").list();
	}

	public void addPost(Post post) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(post);
	}

	@Override
	public Post getPostByPostTitle(String postTitle) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Post WHERE title = '"+postTitle+"'",Post.class).getSingleResult();
	}

	@Override
	public List<Comment> getComment(int id) {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Comment WHERE pid = '"+id+"'",Comment.class).list();
	}

	@Override
	public void comment(Comment comment) {
		
		sessionFactory.getCurrentSession().save(comment);
	}

}
