package com.example.lessonEnglish.controller.user;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

import com.example.lessonEnglish.dto.ResultResponse;
import com.example.lessonEnglish.error.CustomError;

public class BaseController {

	protected ResponseEntity<?> response(ResultResponse entity) {
		return new ResponseEntity<>(entity, HttpStatus.OK);
	}

	protected ResponseEntity<?> errorHttp(ResultResponse entity) {
		return new ResponseEntity<>(entity, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<?> errorsValidation(Errors error) {
		String errorValidation = error.getFieldErrors().stream()
				.map(errors -> errors.getField() + ": " + errors.getDefaultMessage()).collect(Collectors.joining(", "));
		return new ResponseEntity<>(errorValidation, HttpStatus.BAD_REQUEST);
	}
	
	protected ResponseEntity<?> validation(String text) {
		
		return new ResponseEntity<>(text, HttpStatus.OK);
	}
	protected ResultResponse ok(Object data) {
		ResultResponse resultEntity = new ResultResponse();
		resultEntity.setMessage("Success");
		resultEntity.setData(data);
		resultEntity.setCode(200);
		return resultEntity;
	}

	protected ResultResponse error(Exception ex) {

		ResultResponse ResultResponse = new ResultResponse();

		if (ex instanceof CustomError) {
			CustomError customException = (CustomError) ex;
			String message = ex.getMessage();
			if (message.contains("java.lang.Exception:")) {
				message = message.substring(20);
			}
			ResultResponse.setMessage(message);

			ResultResponse.setCode(400);
			ResultResponse.setData(customException.getData());
		} else {
			ResultResponse.setCode(500);
			ResultResponse.setMessage("Internal Service Error " + ex.getMessage());
		}
		return ResultResponse;
	}
}
