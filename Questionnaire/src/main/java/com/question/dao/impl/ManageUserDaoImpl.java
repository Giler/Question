package com.question.dao.impl;

import org.springframework.stereotype.Repository;

import com.question.dao.ManageUserDao;
import com.question.entity.Manageuser;
@Repository("manageUserDao")
public class ManageUserDaoImpl extends BaseDaoImpl<Manageuser> implements ManageUserDao {
}
