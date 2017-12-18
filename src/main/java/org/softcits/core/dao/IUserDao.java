package org.softcits.core.dao;

import java.util.List;

import org.softcits.core.model.Group;
import org.softcits.core.model.Role;
import org.softcits.core.model.RoleType;
import org.softcits.core.model.User;
import org.softcits.core.model.UserGroup;
import org.softcits.core.model.UserRole;
import org.softcits.basic.dao.IBaseDao;
import org.softcits.basic.model.Pager;

public interface IUserDao extends IBaseDao<User> {

	public List<Role> listUserRoles(int userId);
	public List<Integer> listUserRoleIds(int userId);
	public List<Group> listUserGroups(int userId);
	public List<Integer> listUserGroupIds(int userId);
	public UserRole loadUserRole(int userId,int roleId);
	public UserGroup loadUserGroup(int userId,int groupId);
	public User loadByUsername(String username);
	public List<User> listRoleUsers(int roleId);
	public List<User> listRoleUsers(RoleType roleType);
	public List<User> listGroupUsers(int gid);
	public void addUserRole(User user,Role role);
	public void addUserGroup(User user,Group group);
	public void deleteUserRoles(int uid);
	public void deleteUserGroups(int gid);	
	public Pager<User> findUser();
	public void deleteUserRole(int uid,int rid);
	public void deleteUserGroup(int uid,int gid);
}
