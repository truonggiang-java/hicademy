package com.example.lessonEnglish.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.LogoDto;
import com.example.lessonEnglish.entity.Logo;
import com.example.lessonEnglish.repository.LogoRepository;

@Service
public class LogoService {
	@Autowired
	private LogoRepository logoRepository;

	private String uploadFolderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static" + "\\logo";

	public LogoDto uploadToProject(MultipartFile file) {
		try {
			LogoDto logoDto=new LogoDto();
			File folder = new File(uploadFolderPath);
			if (!folder.exists()) {
				folder.mkdir();
			}

			byte[] data = file.getBytes();
			String fileName = StringUtils.cleanPath(file.getOriginalFilename());
			String[] filename=fileName.split("\\.");
			Date date=new Date();
			String fileNameDatabase=filename[0]+ " - "+String.valueOf(date.getTime())+"."+filename[1];
			Path path = Paths.get(uploadFolderPath + "\\" + fileNameDatabase);
			Files.write(path, data);
			Logo logo = new Logo();
			logo.setFileName(fileNameDatabase);
			logo.setFileType(file.getContentType());
			logoRepository.save(logo);
			String link = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/logo/view/")
					.path(logo.getId()).toUriString();
			logoDto.setId(logo.getId());
			logoDto.setLink(link);
			return logoDto;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
	}

	public ResponseEntity<byte[]> viewImage(String id) throws IOException {
		Optional<Logo> logo = logoRepository.findById(id);
		if (!logo.isPresent()) {
			ClassPathResource classpath1 = new ClassPathResource(
					"static/image/" + "truy-tim-nguyen-nhan-va-cach-sua-chua-loi-tra-cuu-404-not-found-1.jpg");
			byte[] imageBytes = StreamUtils.copyToByteArray(classpath1.getInputStream());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		} else {
			ClassPathResource classpath = new ClassPathResource("static/logo/" + logo.get().getFileName());

			byte[] imageBytes = StreamUtils.copyToByteArray(classpath.getInputStream());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		}
	}
}
