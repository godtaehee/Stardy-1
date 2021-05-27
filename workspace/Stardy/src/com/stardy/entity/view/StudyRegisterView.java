package com.stardy.entity.view;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class StudyRegisterView {
	private int studyId;
	private int memberId;
	private int id;
	private String title;
	private int leader;
	private int categoryId;
	private int limit;
	private String open;
	private Date dueDate;
	private String intro;
	private Date regDate;
	private Date updateDate;
	private String bg;
	private String path;
	private int cnt;
	private String name;
	
}
