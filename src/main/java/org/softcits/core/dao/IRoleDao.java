package org.softcits.core.dao;

import java.util.List;

import org.softcits.basic.dao.IBaseDao;
import org.softcits.core.model.Role;

public interface IRoleDao extends IBaseDao<Role> {
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
}
