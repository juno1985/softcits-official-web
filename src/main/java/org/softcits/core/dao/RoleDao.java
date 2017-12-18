package org.softcits.core.dao;

import org.softcits.core.model.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.softcits.basic.dao.BaseDao;

@Repository("roleDao")
public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public List<Role> listRole() {
		return this.list("from Role");
	}

	@Override
	public void deleteRoleUsers(int rid) {
		this.updateByHql("delete UserRole ur where ur.role.id=?",rid);
	}


}