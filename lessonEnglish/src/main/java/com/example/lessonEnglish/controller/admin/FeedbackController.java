package com.example.lessonEnglish.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.lessonEnglish.dto.request.FeedBackRequest;
import com.example.lessonEnglish.service.FeedBackService;

@RestController
@RequestMapping("/api/v1/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {
    @Autowired
    private FeedBackService feedBackService;

    @GetMapping("/findAll")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<FeedBackRequest> listFeedBackRequest(){
        return feedBackService.findAllFeedBack();
    }
}
