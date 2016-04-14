package com.question.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.DeptDao;
import com.question.entity.Dept;
@Transactional
@Repository("deptDao")
public class DeptImpl extends BaseDaoImpl<Dept> implements DeptDao {

	
}
