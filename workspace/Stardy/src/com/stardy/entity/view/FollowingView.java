package com.stardy.entity.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowingView {

	private int originId; //나
	private int targetId; //상대방
	
	private String originNickname; //내 닉네임
	private String targetNickname; //상대방 닉네임
}

/*
CREATE VIEW FOLLOWING AS 
SELECT M.ID ORIGIN_ID, M.NICKNAME ORIGIN_NICKANME, F.TARGET_ID, MM.NICKNAME TARGET_NICKNAME FROM MEMBER M 
    LEFT JOIN FRIEND F ON F.ORIGIN_ID = M.ID
    INNER JOIN MEMBER MM ON MM.ID = F.TARGET_ID
*/
