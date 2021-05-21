package com.stardy.service;

import java.util.List;

import com.stardy.entity.Study;

public interface IndexService {

	List<Study> getMyStudyList(int memberId);
	List<Study> getStudyList();
	//List<Exam> getExamList();
}
