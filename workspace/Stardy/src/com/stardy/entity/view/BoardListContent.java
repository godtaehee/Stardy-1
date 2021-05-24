package com.stardy.entity.view;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor

public class BoardListContent {

    private int id;
    private String title;
    private String content;
    private Date regdate;
    private int memberId;
    private int studyId;
    private Date updateDate;
    private int likes;
    private String name;
    private int replyCnt;

}
