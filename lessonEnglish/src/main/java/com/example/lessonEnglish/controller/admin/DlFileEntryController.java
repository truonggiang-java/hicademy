package com.example.lessonEnglish.controller.admin;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.lessonEnglish.dto.PageableDlFileEntryDto;
import com.example.lessonEnglish.service.DlFileEntrySerivce;

@RestController
@RequestMapping("/api/v1/dlFileEntry")
@CrossOrigin(origins =  "*")
public class DlFileEntryController {
	
	@Autowired
	private DlFileEntrySerivce dlFileEntryService;
	

	@PostMapping("/uploadFile")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String uploadToFile(@RequestParam("file") List<MultipartFile> file) {
		return dlFileEntryService.uploadToProject(file);
	}
	@GetMapping("/viewImage/{id}")
	public ResponseEntity<byte[]> viewImage(@PathVariable String id) throws IOException{
		return dlFileEntryService.viewImage(id);
	}
	
	@DeleteMapping("/deleteFile")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String deleteFile(@RequestBody List<String> id) {
		return dlFileEntryService.deleteFile(id);
	}
	
	@GetMapping("/findAll")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public PageableDlFileEntryDto findAllPage(@RequestParam(name="page",required = false, defaultValue = "1") Integer page,
			@RequestParam(name="size",required = false, defaultValue = "6") Integer size,
			@RequestParam(name="input",required = false,defaultValue = ".") String input) {
		return dlFileEntryService.findAllDlFileEntry(page, size, input);
	}
	
	@GetMapping("/findById")
	@PreAuthorize("hasAnyAuthority('ADMIN','USER')")
	public String findByIdDlFileEntry(@RequestParam(name="id") String id) {
		return dlFileEntryService.findByIdDlFileEntry(id);
	}
}
