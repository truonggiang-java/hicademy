package com.example.lessonEnglish.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.lessonEnglish.dto.VideoDto;
import com.example.lessonEnglish.entity.Video;
import com.example.lessonEnglish.repository.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;

    public String insertVideo(VideoDto videoDto) {
        try {
            Video video = new Video();
            video.setName(videoDto.getName());
            video.setDescription(videoDto.getDescription());
            video.setLink(videoDto.getLink());
            video.setParam(videoDto.getParam());
            videoRepository.save(video);
            return "Bạn đã thêm video " + video.getName() + " thành công";
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
            return "Thêm video thất bại";
        }
    }

    public java.util.List<Video> findAll() {
        return videoRepository.findAll();
    }

    public Video findById(String id) {
        Video video = videoRepository.findById(id).get();
        return video;
    }

    public String deleteVideoByListId(java.util.List<String> id) {
        java.util.List<Video> video = videoRepository.deleteListVideo(id);
        videoRepository.deleteAll(video);
        return "Bạn đã xóa video thành công";
    }

    public String updateVideo( VideoDto videoDto , String id) {
        Video video = videoRepository.findById(id).get();
        video.setName(videoDto.getName());
        video.setDescription(videoDto.getDescription());
        video.setLink(videoDto.getLink());
        video.setParam(videoDto.getParam());
        videoRepository.save(video);
        return "Bạn đã cập nhật vieo thành công ";
    }
}