package com.question.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.StatisticalDao;
import com.question.entity.Statistical;
@Transactional
@Repository("statisticalDao")
public class StatisticalImpl extends BaseDaoImpl<Statistical> implements StatisticalDao {

	
}
