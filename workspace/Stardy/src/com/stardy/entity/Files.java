package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Files {
	
	private int id;
	
	private String name;
	private String path;
	
	private int boardId;
}

