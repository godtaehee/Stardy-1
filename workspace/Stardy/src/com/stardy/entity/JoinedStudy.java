package com.stardy.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JoinedStudy {

	private int memberId;
	private int studyId;
	private Date regDate;
}
