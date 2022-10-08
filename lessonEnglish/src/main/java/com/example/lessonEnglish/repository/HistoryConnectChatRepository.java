package com.example.lessonEnglish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lessonEnglish.entity.HistorySigin;

@Repository
public interface HistoryConnectChatRepository extends JpaRepository<HistorySigin, String>{

}
