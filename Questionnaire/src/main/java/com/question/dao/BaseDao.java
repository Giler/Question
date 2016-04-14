package com.question.dao;

import java.util.List;



//基础Dao
//使用泛型与接口
public interface BaseDao <T>{
	
	//登录通过hql语句
	public List<T> getMore(String hql);
	
	//登录通过hibernate的方法
	public Boolean loginByMethod(T t);
	
	//保存或修改
	public void saveOrUpdate(T t);
	
	//删除
	public void delete(T t);
	
	//根据hql语句查询多条数据
	public List<Integer> getBySql(String Sql);
	
	//根据hql语句查询一条数据
	public T getOneByHql(String hql);
	
	//根据实体查询一条数据
	public T getOneByEntity(T t);
	
	//通过hql语句实现分页
	public List<T> pageByHql(String hql,int start,int pageNum);
	
	//通过Hibernate提供的方法实现分页
	public List<T> pageByMethod(int start,int pageNum);
	
	//通过hql语句得到想要得到的行数
	public int getCount(String hql);
	//保存实体类
	public int save(T t);
	//根据hql语言删除
	public void delete(String hql);
}
