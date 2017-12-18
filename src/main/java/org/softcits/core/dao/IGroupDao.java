package org.softcits.core.dao;

import java.util.List;

import org.softcits.basic.dao.IBaseDao;
import org.softcits.basic.model.Pager;
import org.softcits.core.model.Channel;
import org.softcits.core.model.ChannelTree;
import org.softcits.core.model.Group;
import org.softcits.core.model.GroupChannel;


public interface IGroupDao extends IBaseDao<Group> {
	public List<Group> listGroup();
	public Pager<Group> findGroup();
	public void deleteGroupUsers(int gid);
	public void addGroupChannel(Group group,Channel channel);
	public GroupChannel loadGroupChannel(int gid,int cid);
	public void clearGroupChannel(int gid);
	public void deleteGroupChannel(int gid,int cid);
	public List<Integer> listGroupChannelIds(int gid);
	public List<ChannelTree> generateGroupChannelTree(int gid);
	public List<ChannelTree> generateUserChannelTree(int uid);

}