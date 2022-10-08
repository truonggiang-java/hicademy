package com.example.lessonEnglish.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserTypeValidator implements ConstraintValidator<ValidationUserType, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		List<String> gender=Arrays.asList("MALE","FEMALE");
		return gender.contains(value);
	}

}
