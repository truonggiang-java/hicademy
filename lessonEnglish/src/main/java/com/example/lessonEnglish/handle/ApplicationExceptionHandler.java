package com.example.lessonEnglish.handle;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // hiểu đơn giản là khi người dùng truyền một lỗi gì đó thì sẽ ko bắn ra lỗi hệ thống mà sẽ bắn ra lỗi mình tự custom
public class ApplicationExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String,String> handleMethodArgumentRequest(MethodArgumentNotValidException ex){
		Map<String,String> maps=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(e ->{
			maps.put(e.getField(), e.getDefaultMessage());
		});
		return maps;
	}
}
