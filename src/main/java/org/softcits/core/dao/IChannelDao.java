package org.softcits.core.dao;


import java.util.List;

import org.softcits.basic.dao.IBaseDao;
import org.softcits.core.model.Channel;
import org.softcits.core.model.ChannelTree;
import org.softcits.core.model.ChannelType;


public interface IChannelDao extends IBaseDao<Channel> {

	public List<Channel> listByParent(Integer pid);

	public int getMaxOrderByParent(Integer pid);

	public List<ChannelTree> generateTree();

	public List<ChannelTree> generateTreeByParent(Integer pid);

	public void updateSort(Integer[] ids);

	public List<Channel> listPublishChannel();

	public List<Channel> listAllIndexChannel(ChannelType ct);
	
	public List<Channel> listTopNavChannel();

	public void deleteChannelGroups(int cid);

	public Channel loadFirstChannelByNav(int cid);
	
	public List<Channel> listUseChannelByParent(Integer cid);

	public List<Channel> listChannelByType(ChannelType ct);
}
