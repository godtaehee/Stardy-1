package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Files {
	
	private String uuid;

	
	private String name;
	private String path;
	
	private int boardId;
}

