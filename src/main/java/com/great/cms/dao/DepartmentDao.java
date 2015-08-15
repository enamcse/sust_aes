package com.great.cms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.great.cms.entity.Department;


public interface DepartmentDao  extends GenericDao<Department, Integer> {

	public void deleteAll();

}
