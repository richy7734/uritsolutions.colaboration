package com.niit.uritsolution.dao;

import java.util.List;

import com.niit.uritsolution.model.Forum;
import com.niit.uritsolution.model.People;

public interface ForumDao {

	public People createForum(People people);
	public void joinForum(People people);
	public People getPeopleById(int id);
	public List<People> listPeople();
	public Forum getGroupByName(String grpName);
	public People getPeopleByIdAndGrp(int grpId, Forum group);
	public Forum createGroup(Forum group);
	public List<Forum> listForum();
	public Forum getForumById(int frmId);
	public List<People> getPeopleForApproval(int grpId);
	public List<People> getPeopleByCurrentuser(int userId);
	public void acceptRequest (People peole);
}
