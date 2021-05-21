package com.stardy.service;


import java.sql.SQLException;

import com.stardy.entity.Member;

public interface MemberService {
	boolean isExistNick(String nickname);
	Member get(int id);
	Member login(String email, String password);
	boolean insertMember(Member user);
	void modify(Member member);
	void deleteUser(int id);
	int updatePW(String password, String id);
	int changPwByEmail(String password, String email) ;
	Member getUser(String email);

}
