package com.example.lessonEnglish.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class PageBasic {
	public Pageable page(Integer page,Integer size){
		
		Pageable pageable=PageRequest.of(page, size);
		return pageable;
	}
}
