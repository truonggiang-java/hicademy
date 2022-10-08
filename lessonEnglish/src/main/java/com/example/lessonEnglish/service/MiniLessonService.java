package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.DlFileEntryDto;
import com.example.lessonEnglish.dto.MiniLessonDto;
import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableDto;
import com.example.lessonEnglish.dto.request.MiniLessonRequest;
import com.example.lessonEnglish.entity.DlFileEntry;
import com.example.lessonEnglish.entity.Lesson;
import com.example.lessonEnglish.entity.MiniLesson;
import com.example.lessonEnglish.projections.MiniLessonProjection;
import com.example.lessonEnglish.repository.DlFileEntryRepository;
import com.example.lessonEnglish.repository.LessonRepository;
import com.example.lessonEnglish.repository.MiniLessonRepository;

@Service
public class MiniLessonService {
	@Autowired
	private MiniLessonRepository miniLessonRepository;
	
	@Autowired
	private DlFileEntryRepository dlFileEntryRepository;
	
	@Autowired
	private LessonRepository lessonRepository;
	
	
	public ResponseEntity<?> insertMiniLesson(MiniLessonDto miniLessonDto){
		try {
			MiniLesson miniLesson=new MiniLesson();
			miniLesson.setIdDlFileEntry(miniLessonDto.getIdDlFileEntry());
			miniLesson.setIdLesson(miniLessonDto.getIdLesson());
			miniLessonRepository.save(miniLesson);
			return new ResponseEntity<>("Thêm ảnh con trong bài học thành công", HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK); 
		}
	}
	
	public ResponseEntity<?> updateMiniLesson(MiniLessonDto miniLessonDto,String id){
		try {
			
			MiniLesson miniLesson=miniLessonRepository.findById(id).get();
			miniLesson.setIdDlFileEntry(miniLessonDto.getIdDlFileEntry());
			miniLesson.setIdLesson(miniLessonDto.getIdLesson());
			miniLessonRepository.save(miniLesson);
			return new ResponseEntity<>("Cập nhật ảnh con trong bài học thành công", HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK); 
		}
	}
	
	public ResponseEntity<?> deleteMiniLesson(List<String> id){
		try {
			List<MiniLesson> listMiniLessons=miniLessonRepository.findListIdMiniLesson(id);
			miniLessonRepository.deleteAll(listMiniLessons);
			return new ResponseEntity<>("Xóa ảnh con trong bài học thành công", HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK); 
		}
	}
	public ResponseEntity<?> findAllMiniLesson(Integer page, Integer size, String input){
		try {
			
			PageableDto<MiniLessonRequest> pageAbleMiniLessonDto=new PageableDto<>();
			
			List<MiniLessonRequest> listMiniLessonDto=new ArrayList<>();
			List<MiniLessonProjection> listMiniProjections=miniLessonRepository.findAllMiniLesson(input, (page - 1) * size, size);
			Long listMiniLesson = miniLessonRepository.countMiniLesson(input);
			for (MiniLessonProjection miniLessonProjection : listMiniProjections) {
			
				List<DlFileEntryDto> listDlFileEntryDto=new ArrayList<>();
				if(miniLessonProjection.getIdDlFileEntry().contains(",")) {
					String[] idDlFileEntry=	miniLessonProjection.getIdDlFileEntry().split(",");
					for (String id : idDlFileEntry) {
						String name="";
						DlFileEntry dlFileEntry=dlFileEntryRepository.findById(id).get();
						String fileName = ServletUriComponentsBuilder.fromCurrentContextPath()
								.path("/api/v1/dlFileEntry/viewImage/").path(id).toUriString();
						if (dlFileEntry.getFileName().contains(".png")) {
							name = dlFileEntry.getFileName().replace(".png", "");
						} else if (dlFileEntry.getFileName().contains(".jpg")) {
							name = dlFileEntry.getFileName().replace(".jpg", "");
						}
						DlFileEntryDto dlFileEntryDto=new DlFileEntryDto(name, id, fileName);
						listDlFileEntryDto.add(dlFileEntryDto);
					}
				}else {
					String name="";
					DlFileEntry dlFileEntry=dlFileEntryRepository.findById(miniLessonProjection.getIdDlFileEntry()).get();
					if (dlFileEntry.getFileName().contains(".png")) {
						name = dlFileEntry.getFileName().replace(".png", "");
					} else if (dlFileEntry.getFileName().contains(".jpg")) {
						name = dlFileEntry.getFileName().replace(".jpg", "");
					}
					String fileName = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/v1/dlFileEntry/viewImage/").path(miniLessonProjection.getIdDlFileEntry()).toUriString();
					DlFileEntryDto dlFileEntryDto=new DlFileEntryDto(name, miniLessonProjection.getIdDlFileEntry(), fileName);
					listDlFileEntryDto.add(dlFileEntryDto);
				}
				MiniLessonRequest miniLessonDto = new MiniLessonRequest(miniLessonProjection.getId(),miniLessonProjection.getName(), miniLessonProjection.getDescription(), listDlFileEntryDto);
				listMiniLessonDto.add(miniLessonDto);
			}
			int totalPage = (int) Math.ceil((double) listMiniLesson / size);
			pageAbleMiniLessonDto.setPage(new PageDto(totalPage, listMiniLesson, listMiniProjections.size(), size));
			pageAbleMiniLessonDto.setData(listMiniLessonDto);
			return new ResponseEntity<>(pageAbleMiniLessonDto, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK); 
		}
	}
	public ResponseEntity<?> findById(String idFile){
		try {
			MiniLesson miniLesson=miniLessonRepository.findListIdMiniLessonById(idFile);
			Lesson lesson=lessonRepository.findById(idFile).get();
			List<DlFileEntryDto> listDlFileEntryDto=new ArrayList<>();
			if(miniLesson.getIdDlFileEntry().contains(",")) {
				String[] idDlFileEntry=	miniLesson.getIdDlFileEntry().split(",");
				for (String id : idDlFileEntry) {
					String name="";
					DlFileEntry dlFileEntry=dlFileEntryRepository.findById(id).get();
					String fileName = ServletUriComponentsBuilder.fromCurrentContextPath()
							.path("/api/v1/dlFileEntry/viewImage/").path(id).toUriString();
					if (dlFileEntry.getFileName().contains(".png")) {
						name = dlFileEntry.getFileName().replace(".png", "");
					} else if (dlFileEntry.getFileName().contains(".jpg")) {
						name = dlFileEntry.getFileName().replace(".jpg", "");
					}
					DlFileEntryDto dlFileEntryDto=new DlFileEntryDto(name, id, fileName);
					listDlFileEntryDto.add(dlFileEntryDto);
				}
			}else {
				String name="";
				DlFileEntry dlFileEntry=dlFileEntryRepository.findById(miniLesson.getIdDlFileEntry()).get();
				if (dlFileEntry.getFileName().contains(".png")) {
					name = dlFileEntry.getFileName().replace(".png", "");
				} else if (dlFileEntry.getFileName().contains(".jpg")) {
					name = dlFileEntry.getFileName().replace(".jpg", "");
				}
				String fileName = ServletUriComponentsBuilder.fromCurrentContextPath()
						.path("/api/v1/dlFileEntry/viewImage/").path(miniLesson.getIdDlFileEntry()).toUriString();
				DlFileEntryDto dlFileEntryDto=new DlFileEntryDto(name, miniLesson.getIdDlFileEntry(), fileName);
				listDlFileEntryDto.add(dlFileEntryDto);
			}
			MiniLessonRequest miniLessonDto = new MiniLessonRequest(lesson.getId(),lesson.getName(), lesson.getDescription(), listDlFileEntryDto);
			
			return new ResponseEntity<>(miniLessonDto, HttpStatus.OK); 
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK); 
		}
	}
}
