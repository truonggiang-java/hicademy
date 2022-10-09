package com.example.lessonEnglish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.LearnDto;
import com.example.lessonEnglish.entity.Learn;
import com.example.lessonEnglish.repository.LearnRepository;

@Service
public class LearnService {
	@Autowired
	private LearnRepository learnRepository;
	
	public String insertLearn(LearnDto learnDto) {
		try {
			Learn learn=new Learn();
			learn.setId_lesson(learnDto.getIdLesson());
			learn.setIdUser(learnDto.getIdUser());
			learn.setPoint(learnDto.getPoint());
			learnRepository.save(learn);
			return "thêm mới thành công";
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return "thêm mới thất bại";
		}
		
	}
}
