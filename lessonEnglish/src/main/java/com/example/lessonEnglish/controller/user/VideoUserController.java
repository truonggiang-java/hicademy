package com.example.lessonEnglish.controller.user;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.lessonEnglish.dto.VideoDto;
import com.example.lessonEnglish.entity.Video;
import com.example.lessonEnglish.service.VideoService;

@RestController
@RequestMapping("/api/v2/video")
@CrossOrigin(origins = "*")

public class VideoUserController {
    @Autowired
    public VideoService videoService;

    @GetMapping("/findAllVideo")
    // @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Video> findAllVideo(@RequestParam(name="input",required = false,defaultValue = "") String input) {
        return videoService.findAll(input);
    }

    @GetMapping("/findByIdVideo")
    // @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Video findByIdVideo(@RequestParam("id") String id) {
        return videoService.findById(id);
    }

    @GetMapping("/findVideoByParam")
    // @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Video> findVideoByParam(@RequestParam("param") String param) {
        return videoService.findVideoByParam(param);
    }
}

