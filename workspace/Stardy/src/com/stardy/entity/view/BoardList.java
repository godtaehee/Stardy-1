package com.stardy.entity.view;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class BoardList {

   private int id;
   private String title;
   private String content;
   private Date regDate;
   private int memberId;
   private int studyId;
   private Date updateDate;
   
   private int replyCnt; //댓글 수
   private int likes; //좋아요 수
   private String name; //작성자

}