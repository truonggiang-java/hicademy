package com.example.lessonEnglish.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.request.LearnRequest;
import com.example.lessonEnglish.entity.Learn;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.repository.LearnRepository;
import com.example.lessonEnglish.repository.LessonRepository;
import com.example.lessonEnglish.service.LearnService;

@RestController
@RequestMapping("/api/v1/learn")
@CrossOrigin(origins = "*")
public class LearnController {
    @Autowired
    private LearnService learnService;

    @Autowired
    private LearnRepository learnRepository;

    @Autowired
    private LessonRepository lessonRepository;
    
    @GetMapping("findById/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<LearnRequest> findByIdLearn(@PathVariable("userId") String userId){
        List<Learn> learns=learnRepository.findAllLessonByIdUser(userId);
        List<LearnRequest> list=new ArrayList<>();
        for (Learn learn : learns) {
            Lesson lesson=lessonRepository.findById(learn.getId_lesson()).get();
            LearnRequest learnRequest=new LearnRequest(lesson.getName());
            list.add(learnRequest);
        }
        return list;
    }

    
}
