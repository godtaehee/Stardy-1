package com.stardy.entity.view;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReplyView {

	private int id;
	
	private Date regDate;
	private Date updateDate;
	private String content;
	
	private int memberId;
	private int boardId;
	
	private String name; //작성자
}