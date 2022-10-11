package com.example.lessonEnglish.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.LearnDto;
import com.example.lessonEnglish.dto.request.LearnCustomerRequest;
import com.example.lessonEnglish.service.LearnService;

@RestController
@RequestMapping("/api/v2/learn")
@CrossOrigin(origins =  "*")
public class LearnControllerUser {
	@Autowired
	private LearnService learnService;
	
	@PostMapping("/insertLearn")
	public String inserLearn(@RequestBody LearnDto learnDto) {
		return learnService.insertLearn(learnDto);
	}

	@GetMapping("/findById/{idUser}")
	public LearnCustomerRequest findByIdLearn(@PathVariable("idUser") String idUser){
		return learnService.findLearn(idUser);
	}

	@GetMapping("/checkLesson")
	public String checkLesson(@RequestParam("idUser") String idUser,@RequestParam("idLesson") String idLesson){
		return learnService.checkLearn(idUser, idLesson);
	}
}
