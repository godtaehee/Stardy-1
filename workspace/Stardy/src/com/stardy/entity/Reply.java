package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Reply {
	
	private int id;
	
	private Date regDate;
	private String content;
	
	private int memberId;
	private int boardId;
	
}
