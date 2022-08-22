package com.example.lessonEnglish.Admin.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1/video")
@CrossOrigin(origins = "*")

public class VideoController {
    @Autowired
    public VideoService videoService;

    @PostMapping("/insertVideo")
    public String inserVideo(@RequestBody VideoDto videoDto) {
        return videoService.insertVideo(videoDto);
    }

    @GetMapping("/findAllVideo")
    public List<Video> findAllVideo(@RequestParam(name="input",required = false,defaultValue = "") String input) {
        return videoService.findAll(input);
    }

    @GetMapping("/findByIdVideo")
    public Video findByIdVideo(@RequestParam("id") String id) {
        return videoService.findById(id);
    }

    @DeleteMapping("/deleteVideo")
    public String deleteVideoById(@RequestBody List<String> id) {
        return videoService.deleteVideoByListId(id);
    }

    @PutMapping("/updateVideo/{id}")
    public String updateVideo(@RequestBody VideoDto videoDto, @PathVariable("id") String id) {
        return videoService.updateVideo(videoDto, id);
    }

    @GetMapping("/findVideoByParam")
    public List<Video> findVideoByParam(@RequestParam("param") String param) {
        return videoService.findVideoByParam(param);
    }
}
