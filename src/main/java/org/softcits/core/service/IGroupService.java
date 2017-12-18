package org.softcits.core.service;

import java.util.List;

import org.softcits.basic.model.Pager;
import org.softcits.core.model.ChannelTree;
import org.softcits.core.model.Group;
import org.softcits.core.model.GroupChannel;



public interface IGroupService {
	public void add(Group group);
	public void delete(int id);
	public Group load(int id);
	public void update(Group group);
	
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public void deleteGroupUsers(int gid);
	

	public void addGroupChannel(int gid,int cid);

	public GroupChannel loadGroupChannel(int gid,int cid);

	public void clearGroupChannel(int gid);

	public void deleteGroupChannel(int gid,int cid);

	public List<Integer> listGroupChannelIds(int gid);

	public List<ChannelTree> generateGroupChannelTree(int gid);

	public List<ChannelTree> generateUserChannelTree(int uid);
}
