package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Member {

	private String email;
	private String password;
	private String nickname;
	private Date regDate;
	private String profile;
	private String status;
	private String path;
	private int id;
	
}