package com.stardy.entity;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class View {

	//Member
	private int m_id;
	private String m_nickname;
	private String m_email;
	private String m_password;
	private Date m_regDate;
	private String m_profile;
	private String m_status;
	private String m_path;
	private boolean m_enable;
	
	//Board
	private int b_id;
	private String b_title;
	private String b_content;
	private Date b_regDate;
	private int b_memberId;
	private int b_studyId;
	private Date b_updateDate;
	
	//Study
	private int s_id;
    private String s_title;
    private String s_intro;
    private String s_open;
    private String s_limit;
   
    private Date s_regDate;
    private Date s_updateDate;
    private Date s_dueDate;
   
    private String s_bg;
    private String s_path;
   
    private int s_memberId;
    private int s_categoryId;
    
	//Reply
	
	//Sub
	
	//Files
	
	//Like
	
	//Friend
	
	//Joined_Study
	
	//Category
	
	
}
