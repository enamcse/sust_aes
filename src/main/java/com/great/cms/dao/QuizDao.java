package com.great.cms.dao;

import java.util.List;

import com.great.cms.entity.Quiz;
import com.great.cms.entity.Teacher;
import com.great.cms.entity.Teaches;
import com.great.cms.entity.User;

public interface QuizDao extends GenericDao<Quiz, Long> {

	public List<Quiz> findByTeachesId(Teaches teachesId);

}