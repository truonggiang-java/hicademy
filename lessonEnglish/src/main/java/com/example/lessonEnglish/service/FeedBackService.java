package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.FeedBackDto;
import com.example.lessonEnglish.dto.request.FeedBackRequest;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.entity.FeedBack;
import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.repository.CustomerRepository;
import com.example.lessonEnglish.repository.FeedBackReository;

@Service
public class FeedBackService {
	@Autowired
	public FeedBackReository feedBackRepository;

	@Autowired
	public CustomerRepository customerRepository;
	
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

	public List<FeedBackRequest> findAllFeedBack(){
		try {
			List<FeedBackRequest> lisFeedBackRequests=new ArrayList<>();
			List<FeedBack> lisFeedBacks=feedBackRepository.findAll();
			for (FeedBack feedBack : lisFeedBacks) {
				FeedBackRequest feedBackRequest=new FeedBackRequest();
				feedBackRequest.setParentStundent(feedBack.getName());
				feedBackRequest.setDescription(feedBack.getDescription());
				Customer customer=customerRepository.findById(feedBack.getIdUser()).get();
				feedBackRequest.setNameStudent(customer.getName());
				lisFeedBackRequests.add(feedBackRequest);
			}
			return lisFeedBackRequests;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
}
