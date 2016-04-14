package com.question.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.AnSheetDao;
import com.question.entity.AnSheet;
@Transactional
@Repository("anSheetDao")
public class AnSheetImpl extends BaseDaoImpl<AnSheet> implements AnSheetDao {

	
}
