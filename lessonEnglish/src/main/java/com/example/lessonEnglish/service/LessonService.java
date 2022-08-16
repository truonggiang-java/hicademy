package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.LessonDto;
import com.example.lessonEnglish.dto.LessonImageDto;
import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableLessonDto;
import com.example.lessonEnglish.entity.Course;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.projections.CourseLessonProjection;
import com.example.lessonEnglish.projections.LessonProjection;
import com.example.lessonEnglish.repository.CourseRepository;
import com.example.lessonEnglish.repository.LessonRepository;

@Service
public class LessonService {
	@Autowired
	private LessonRepository lessonRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	public String insertLesson(LessonDto lessonDto) {
		try {
			Lesson lesson=new Lesson();
			List<Course> courses=courseRepository.findListCourse(lessonDto.getIdCourse());
			lesson.setDescription(lessonDto.getDescription());
			lesson.setIdDlfileEntry(lessonDto.getIdDlfileEntry());
			lesson.setName(lessonDto.getName());
			lesson.setCourses(courses);
			lessonRepository.save(lesson);
			return "Bạn thêm bài học thành công";
		} catch (Exception e) {
			e.printStackTrace();
			return "Bạn thêm bài học thất bại";
			// TODO: handle exception
		}
	}

	public String updateLesson(LessonDto lessonDto, String id) {
		Lesson lesson = lessonRepository.findById(id).get();
		lesson.setName(lessonDto.getName());
		lesson.setDescription(lessonDto.getDescription());
		lesson.setIdDlfileEntry(lessonDto.getIdDlfileEntry());
		lessonRepository.save(lesson);
		return "Bạn đã cập nhật bài học thành công";
	}	

	public PageableLessonDto findAll(Integer page,Integer size,String input) {
		PageableLessonDto pageableCourseDto=new PageableLessonDto();
		Long listLesson=lessonRepository.countLesson(input);
		List<LessonProjection> pageLesson=lessonRepository.findAllLesson(input,(page-1)*size,size);
		List<LessonImageDto> listImage = new ArrayList<>();
		for (LessonProjection lesson : pageLesson) {
			LessonImageDto lessonImageDto = new LessonImageDto();
			lessonImageDto.setDescription(lesson.getDescription());
			lessonImageDto.setId(lesson.getId());
			lessonImageDto.setName(lesson.getName());
			lessonImageDto.setCountCourse(lesson.getCountCourse());
			String fileName = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/dlFileEntry/viewImage/")
					.path(lesson.getIdDlFileEntry()).toUriString();
			lessonImageDto.setLink(fileName);
			//lesson
			List<CourseLessonProjection> listCourse=lessonRepository.findCourseByIdLesson(lesson.getId());
			String course="";
			if(listCourse.size()>0) {
				for(int i=0;i<listCourse.size();i++) {
					if(i<listCourse.size()-1) {
						course+=listCourse.get(i).getName() + ", ";
					}else {
						course+=listCourse.get(i).getName();
					}
				}
			}else {
				course="Không có bài học nào trong khóa học này";
			}
			lessonImageDto.setCourse(course);
			listImage.add(lessonImageDto);
		}
//		listImage.add(lessonImageDto);
		int totalPage = (int) Math.ceil((double) listLesson / size);
		pageableCourseDto.setPage(new PageDto(totalPage, listLesson, pageLesson.size(), size));
		pageableCourseDto.setLessonImage(listImage);
		return pageableCourseDto;
	}
	
	public List<Lesson> findAllLesson() {
		return lessonRepository.findAll();
	}
}
