package com.example.lessonEnglish.service;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableDto;
import com.example.lessonEnglish.dto.PageableLessonDto;
import com.example.lessonEnglish.dto.VideoDto;
import com.example.lessonEnglish.entity.Video;
import com.example.lessonEnglish.page.PageableBasic;
import com.example.lessonEnglish.repository.VideoRepository;

@Service
public class VideoService {
    @Autowired
    private VideoRepository videoRepository;
    
    @Autowired
    private PageableBasic<Video> pageableBasic;
   
    public String insertVideo(VideoDto videoDto) {
        try {
            Video video = new Video();
            video.setName(WordUtils.capitalizeFully(videoDto.getName()));
            video.setDescription(videoDto.getDescription().substring(0, 1).toUpperCase() + videoDto.getDescription().substring(1, videoDto.getDescription().length()));
            String link=videoDto.getLink();
            if(link.contains("?v=")) {
            	link=link.replace("watch?v=","embed/");
            }
            video.setLink(link);
            video.setParam(videoDto.getParam());
            videoRepository.save(video);
            return "Bạn đã thêm video " + video.getName() + " thành công";
        } catch (Exception e) {
            e.printStackTrace();
            //TODO: handle exception
            return "Thêm video thất bại";
        }
    }

    public PageableDto<Video> findAll(Integer page, Integer size,String input) {
    	Long listVideo = videoRepository.countVideo(input);
    	List<Video> pageVideo=videoRepository.findAllVideo(input,(page - 1) * size, size);
    	int totalPage = (int) Math.ceil((double) listVideo / size);
    	PageableDto<Video> data = pageableBasic.pageableBasic(pageVideo, new PageDto(totalPage, listVideo, pageVideo.size(), size));
        return data;
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
    	try {
			
    		Video video = videoRepository.findById(id).get();
    		video.setName(videoDto.getName());
    		video.setDescription(videoDto.getDescription());
    		video.setLink(videoDto.getLink());
    		video.setParam(videoDto.getParam());
    		videoRepository.save(video);
    		return "Bạn đã cập nhật vieo thành công ";
		} catch (Exception e) {
			e.printStackTrace();
			return "Bạn đã cập nhật vieo thất bại";
			// TODO: handle exception
		}
    }

    public List<Video> findVideoByParam ( String param){
        return videoRepository.findVideoByParam(param);
    }    
}