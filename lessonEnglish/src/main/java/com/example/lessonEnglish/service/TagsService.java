package com.example.lessonEnglish.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.DlFileEntryDto;
import com.example.lessonEnglish.dto.TagsDto;
import com.example.lessonEnglish.dto.TagsImageDto;
import com.example.lessonEnglish.entity.DlFileEntry;
import com.example.lessonEnglish.entity.Tags;
import com.example.lessonEnglish.repository.DlFileEntryRepository;
import com.example.lessonEnglish.repository.TagsRepository;

@Service
public class TagsService {
	@Autowired
	private TagsRepository tagsRepository;
	
	@Autowired
	private DlFileEntryRepository dlFileEntryRepository;
	
	public String insertTags(TagsDto tagsDto) {
		try {
			DlFileEntry dlFileEntry=dlFileEntryRepository.findById(tagsDto.getIdDlFileEntry()).get();
			Tags tags=new Tags();
			tags.setFileName(dlFileEntry.getFileName());
			tags.setParam(tagsDto.getParam().toUpperCase());
			tags.setDescription(tagsDto.getDescription());
			tagsRepository.save(tags);
			return "Thêm thành công tags";
		} catch (Exception e) {
			e.printStackTrace();
			return "Thêm thất bại tags";
		}
	}
	
	public List<TagsImageDto> listTagsImageDto(){
		List<TagsImageDto> listTagsImageDtos=new ArrayList<>();
		List<Tags> listTags=tagsRepository.findAll(Sort.by(Direction.DESC, "updatedDate"));
		for (Tags tags : listTags) {
			DlFileEntry dlFileEntry=dlFileEntryRepository.findListDlFileEntryByName(tags.getFileName());
			TagsImageDto tagsImageDto = new TagsImageDto();
			tagsImageDto.setId(tags.getId());
			String name = "abc";
			if (tags.getFileName().contains(".png")) {
				name = tags.getFileName().replace(".png", "");
			} else if (tags.getFileName().contains(".jpg")) {
				name = tags.getFileName().replace(".jpg", "");
			}

			tagsImageDto.setName(name);
			
			String link = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/dlFileEntry/viewImage/")
					.path(dlFileEntry.getId()).toUriString();
			tagsImageDto.setLink(link);
			listTagsImageDtos.add(tagsImageDto);
		}
		return listTagsImageDtos;
	}
	
	public List<String> randomTags(Integer number){
		List<String> listTags=new ArrayList<>();
		List<String> paramTags=tagsRepository.randomParamTags();
		Integer i = (int) Math.floor((Math.random() * paramTags.size()));
		List<Tags> listTagsRandom=tagsRepository.findListTagsRandom(paramTags.get(i), number*2);
		for (Tags tags : listTagsRandom) {
			String name=tags.getFileName();
			listTags.add(name);
			listTags.add(name);
		}
		return listTags;
	}
}
