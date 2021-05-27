package com.stardy.service;

import java.sql.SQLException;

import java.util.List;

import com.stardy.entity.Study;
import com.stardy.entity.view.StudyView;

public interface StudyService {

   
   List<StudyView> getList(); 
   
   List<StudyView> getList(int page); 
   
   
   List<StudyView> getList(String category, String query,int page);
   
   int paging();
   
   int paging(String category, String query);

   int getCrnt(Study study) throws SQLException;

   String getLeader(int id) throws SQLException;

   boolean isLeader(int memberId, int studyId) throws SQLException;

   boolean isMember(int memberId, int studyId) throws SQLException;

   StudyView getStudy(int id) throws SQLException;

   int getDuringTime(int id) throws SQLException;
   

   

}