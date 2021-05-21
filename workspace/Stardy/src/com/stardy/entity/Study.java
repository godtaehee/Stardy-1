package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Study {

	   private int id;
	   private String title;
	   private String intro;
	   private String open;
	   private String limit;
	   
	   private Date regDate;
	   private Date updateDate;
	   private Date dueDate;
	   
	   private String bg;
	   private String path;
	   
	   private int memberId;
	   private int categoryId;
}