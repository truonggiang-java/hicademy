package com.example.lessonEnglish.controller.admin;
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

import com.example.lessonEnglish.dto.PageableDto;
import com.example.lessonEnglish.dto.VideoDto;
import com.example.lessonEnglish.entity.Video;
import com.example.lessonEnglish.page.PageableBasic;
import com.example.lessonEnglish.service.VideoService;

@RestController
@RequestMapping("/api/v1/video")
@CrossOrigin(origins = "*")

public class VideoController {
    @Autowired
    public VideoService videoService;

    @PostMapping("/insertVideo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String inserVideo(@RequestBody VideoDto videoDto) {
        return videoService.insertVideo(videoDto);
    }

    @GetMapping("/findAllVideo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public PageableDto<Video> findAllVideo(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "7") Integer size,
			@RequestParam(name="input",required = false,defaultValue = "") String input) {
        return videoService.findAll(page,size,input);
    }

    @GetMapping("/findByIdVideo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Video findByIdVideo(@RequestParam("id") String id) {
        return videoService.findById(id);
    }

    @DeleteMapping("/deleteVideo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteVideoById(@RequestBody List<String> id) {
        return videoService.deleteVideoByListId(id);
    }

    @PutMapping("/updateVideo/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public String updateVideo(@RequestBody VideoDto videoDto, @PathVariable("id") String id) {
        return videoService.updateVideo(videoDto, id);
    }

    @GetMapping("/findVideoByParam")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public List<Video> findVideoByParam(@RequestParam("param") String param) {
        return videoService.findVideoByParam(param);
    }
}
