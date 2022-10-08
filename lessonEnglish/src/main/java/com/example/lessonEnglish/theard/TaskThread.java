package com.example.lessonEnglish.theard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.lessonEnglish.entity.Users;
import com.example.lessonEnglish.repository.UserRepository;

public class TaskThread implements Runnable{
	public List<Users> users;
	@Autowired
	private UserRepository userRepository;
	
	public TaskThread(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
	@Override
	public void run() {
		users=userRepository.findAll();
	}

}
