package com.question.service;

import java.util.Map;

import com.question.entity.Manageuser;

public interface ManageUserService {
	public String checkLogin(Manageuser m,Map<String,Object> map);
}
