package com.stardy.entity.view;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubView {

	private int id;
	private String title;
	private Date regDate;
	private Date updateDate;
	
	private String name; //작성자
}
