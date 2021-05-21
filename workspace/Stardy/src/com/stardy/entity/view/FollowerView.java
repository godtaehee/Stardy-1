package com.stardy.entity.view;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FollowerView {

	private int originId; //상대방
	private int targetId; //나
	
	private String originNickname; //상대방 닉네임
	private String targetNickname; //내 닉네임
}
