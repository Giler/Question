package com.question.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.question.dao.BaseDao;

@Repository("baseDaoImpl")
@Transactional
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	@Resource
    public void setSessionFacotry(SessionFactory sessionFacotry) {  
        super.setSessionFactory(sessionFacotry);  
    } 
	
	private Session getCurrentSession(){
			return super.getHibernateTemplate().getSessionFactory().getCurrentSession();
		
	}
	
	public List<T> getMore(String hql) {
		@SuppressWarnings("unchecked")
		List<T> t =this.getCurrentSession().createQuery(hql).list();
		return t;
	}

	public Boolean loginByMethod(T t) {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveOrUpdate(T t) {
		this.getCurrentSession().saveOrUpdate(t);
		
	}

	public void delete(T t) {
		this.getCurrentSession().delete(t);
		
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getBySql(String Sql) {
		// TODO Auto-generated method stub	
		List<Integer> list = null;
		if(Sql!=null){
			list =  this.getCurrentSession().createSQLQuery(Sql).list();
			if(list!=null){
				return list;
			}else{
				//等待完善抛出list空异常
				return null;
			}
		}else{
			//等待完善抛出sql空异常
			return null;
		}
		
	}

	@SuppressWarnings("unchecked")
	public T getOneByHql(String hql) {
//		使用list方法如果列表为空则抛出空异常
//		return (T) this.getCurrentSession().createQuery(hql).list().iterator().next();
//		使用uniqueResult方法当搜索结果为空的时候返回空，不抛出异常；
		return (T) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public T getOneByEntity(T t) {
		return t;
	}

	public List<T> pageByHql(String hql, int start, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<T> pageByMethod(int start, int pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getCount(String hql) {
		Query query = this.getCurrentSession().createQuery(hql);
		int count = ((Long)query.uniqueResult()).intValue();
		return count;
	}
	
	public int save(T t){
		return (Integer)this.getCurrentSession().save(t);
	}
	
	public void delete(String hql) {
		this.getCurrentSession().createQuery(hql).executeUpdate();
		
	}
}
