package com.example.lessonEnglish.page;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableDto;

@Component
public class PageableBasic<T> {
	public PageableDto<T> pageableBasic(List<T> t,PageDto pageDto){
		PageableDto<T> pageable=new PageableDto<>();
		pageable.setData(t);
		pageable.setPage(pageDto);
		return pageable;
	}
}
