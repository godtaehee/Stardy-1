package com.stardy.entity.view;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudyView {


	public StudyView(int id2, String title2, String intro2, String open2, String limit2, Date regDate2,
			Date updateDate2, Date dueDate2, String bg2, String path2, int memberId2, int categoryId2) {
		// TODO Auto-generated constructor stub
	}
	

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
	
	private int cnt; //현재 인원 수
	private String name; //카테고리 명
}

/*
CREATE VIEW STUDY_LIST AS
SELECT 
    S.ID, S.TITLE, S.MEMBER_ID, S.CATEGORY_ID, S.LIMIT, S.OPEN, S.DUEDATE, TO_CHAR(S.INTRO) INTRO, S.REGDATE, S.UPDATEDATE, S.BG, S.PATH, COUNT(J.MEMBER_ID) CNT, C.NAME NAME 
FROM 
    STUDY S 
    LEFT JOIN CATEGORY C ON C.ID = S.CATEGORY_ID 
    LEFT JOIN JOINED_STUDY J ON J.STUDY_ID = S.ID 
GROUP BY 
    S.ID, S.TITLE, S.MEMBER_ID, S.CATEGORY_ID, S.LIMIT, S.OPEN, S.DUEDATE, TO_CHAR(S.INTRO), S.REGDATE, S.UPDATEDATE, S.BG, S.PATH, C.NAME 
ORDER BY S.ID DESC;
*/

/*
스터디 목록 - 제목으로 검색 + 페이징
SELECT SL.* FROM 
(
SELECT 
    S.*, ROWNUM RN 
FROM 
    STUDY_LIST S
WHERE
    S.TITLE LIKE '%?%'
) SL
WHERE RN > ? AND RN <= ?
*/

/*
스터디 목록 - 제목 + 카테고리 + 검색 + 페이징
SELECT * FROM
(
SELECT 
    S.*, ROWNUM RN 
FROM 
    STUDY_LIST S 
WHERE
    CATEGORY_ID = ? AND TITLE LIKE '%?%'
)
WHERE RN > ? AND RN <= ?;
*/