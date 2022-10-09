package com.example.lessonEnglish.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.FeedBackDto;
import com.example.lessonEnglish.entity.FeedBack;
import com.example.lessonEnglish.repository.FeedBackReository;

@Service
public class FeedBackService {
	@Autowired
	public FeedBackReository feedBackRepository;
	
	public String insertFeedBack(FeedBackDto feedBackDto) {
		try {
			FeedBack feedBack=new FeedBack();
			feedBack.setDescription(feedBackDto.getDescription());
			feedBack.setIdUser(feedBackDto.getIdUser());
			feedBack.setName(feedBackDto.getName());
			feedBackRepository.save(feedBack);
			return "thêm phản hồi thành công";
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return "thêm phản hồi thất bại";
		}
	}
}
