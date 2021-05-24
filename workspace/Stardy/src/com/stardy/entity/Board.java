package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

	public Board(int id, String title, String content, Date regDate, Date updateDate, int studyId, int memberId) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.regDate = regDate;
		this.updateDate = updateDate;
		this.studyId = studyId;
		this.memberId = memberId;
		
	}
	private int id;
	private String title;
	private String content;
	private Date regDate;
	private int memberId;
	private int studyId;
	private Date updateDate;
	
}
