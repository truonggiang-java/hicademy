package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.LearnDto;
import com.example.lessonEnglish.dto.request.LearnCustomerRequest;
import com.example.lessonEnglish.entity.Customer;
import com.example.lessonEnglish.entity.Learn;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.repository.CustomerRepository;
import com.example.lessonEnglish.repository.LearnRepository;
import com.example.lessonEnglish.repository.LessonRepository;
import com.example.lessonEnglish.repository.UserRepository;

@Service
public class LearnService {
	@Autowired
	private LearnRepository learnRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private LessonRepository lessonRepository;
	
	public String insertLearn(LearnDto learnDto) {
		try {
			Learn learnUser=learnRepository.findAllLessonById(learnDto.getIdUser(), learnDto.getIdLesson());
			if(learnUser!=null){
				return "bài học này đã được tính điểm";
			}else{

				Learn learn=new Learn();
				learn.setId_lesson(learnDto.getIdLesson());
				learn.setIdUser(learnDto.getIdUser());
				learn.setPoint(learnDto.getPoint());
				learnRepository.save(learn);
				return "thêm mới thành công";
			}
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return "thêm mới thất bại";
		}
		
	}

	public LearnCustomerRequest findLearn(String idUser) {
		try {
			List<Learn> learnUser=learnRepository.findAllLessonByIdUser(idUser);
			Customer customer=customerRepository.findByIdUser(idUser);
			LearnCustomerRequest learnCustomerRequest=new LearnCustomerRequest();
			Integer number=0;
			List<String> listlesson=new ArrayList<>();
			for (Learn learn : learnUser) {
				Lesson lesson=lessonRepository.findById(learn.getId_lesson()).get();
				listlesson.add(lesson.getName());
				number+=learn.getPoint();
			}
			learnCustomerRequest.setLesson(listlesson);
			learnCustomerRequest.setName(customer.getName());
			learnCustomerRequest.setPoint(number);
			return learnCustomerRequest;
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}
		
	}

	public String checkLearn(String idUser,String idLesson){
		try {
			Learn learnUser=learnRepository.findAllLessonById(idUser, idLesson);
			if(learnUser !=null){
				return "Đã học";
			}else{
				return "Chưa học";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}
}
