package com.example.lessonEnglish.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.lessonEnglish.dto.DlFileEntryDto;
import com.example.lessonEnglish.dto.PageDto;
import com.example.lessonEnglish.dto.PageableDlFileEntryDto;
import com.example.lessonEnglish.entity.DlFileEntry;
import com.example.lessonEnglish.page.PageBasic;
import com.example.lessonEnglish.repository.DlFileEntryRepository;

@Service
public class DlFileEntrySerivce {

	@Autowired
	private DlFileEntryRepository dlfileEntryRepository;

	@Autowired
	private PageBasic pageBasic;

	private String uploadFolderPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static" + "\\image";

//	public DlFileEntry updateImage(MultipartFile file,String id) {
//		
//	}
	public String uploadToProject(List<MultipartFile> file) {
		try {
			File folder = new File(uploadFolderPath);
			if (!folder.exists()) {
				folder.mkdir();
			}
			for (MultipartFile multipartFile : file) {
				byte[] data = multipartFile.getBytes();
				Path path = Paths.get(uploadFolderPath + "\\" + multipartFile.getOriginalFilename());
				Files.write(path, data);
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				DlFileEntry dlFileEntry = new DlFileEntry();
				dlFileEntry.setFileName(fileName);
				dlFileEntry.setFileType(multipartFile.getContentType());
				dlfileEntryRepository.save(dlFileEntry);
			}
			return "Thêm ảnh thành công " + uploadFolderPath;
		} catch (Exception e) {
			e.printStackTrace();
			return "Thêm ảnh thất bại";
			// TODO: handle exception
		}
	}

	public ResponseEntity<byte[]> viewImage(String id) throws IOException {
		Optional<DlFileEntry> dlFileEntry = dlfileEntryRepository.findById(id);
		if (!dlFileEntry.isPresent()) {
			ClassPathResource classpath1 = new ClassPathResource(
					"static/image/" + "truy-tim-nguyen-nhan-va-cach-sua-chua-loi-tra-cuu-404-not-found-1.jpg");
			byte[] imageBytes = StreamUtils.copyToByteArray(classpath1.getInputStream());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		}else {
			ClassPathResource classpath = new ClassPathResource("static/image/" + dlFileEntry.get().getFileName());
			
			byte[] imageBytes = StreamUtils.copyToByteArray(classpath.getInputStream());
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
		}
	}

	public String deleteFile(List<String> id) {
		List<DlFileEntry> listDlFileEntries = dlfileEntryRepository.findListDlFileEntry(id);
		if (listDlFileEntries.size() > 0) {

			for (DlFileEntry dlFileEntry : listDlFileEntries) {
				File file = new File(uploadFolderPath + "\\" + dlFileEntry.getFileName());
				file.delete();

			}
			dlfileEntryRepository.deleteAll(listDlFileEntries);
			return "Xóa tất cả file thành công";
		} else {
			return "Xóa file thất bại";
		}

	}

	public PageableDlFileEntryDto findAllDlFileEntry(Integer page, Integer size, String input) {
		PageableDlFileEntryDto pageableDlFileEntryDto = new PageableDlFileEntryDto();
		Page<DlFileEntry> pageDlfileEntry = dlfileEntryRepository.findAllDlFileEntry(input, pageBasic.page(page, size));
		List<DlFileEntryDto> listDlFile = new ArrayList<>();
		for (DlFileEntry dlFileEntry : pageDlfileEntry.getContent()) {
			DlFileEntryDto dlFileEntryDto = new DlFileEntryDto();
			dlFileEntryDto.setId(dlFileEntry.getId());
			String name = "abc";
			if (dlFileEntry.getFileName().contains(".png")) {
				name = dlFileEntry.getFileName().replace(".png", "");
			} else if (dlFileEntry.getFileName().contains(".jpg")) {
				name = dlFileEntry.getFileName().replace(".jpg", "");
			}

			dlFileEntryDto.setName(name);
			String link = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/dlFileEntry/viewImage/")
					.path(dlFileEntry.getId()).toUriString();
			dlFileEntryDto.setLink(link);
			listDlFile.add(dlFileEntryDto);
		}
		pageableDlFileEntryDto.setDlFile(listDlFile);
		pageableDlFileEntryDto.setPage(new PageDto(pageDlfileEntry.getTotalPages(), pageDlfileEntry.getTotalElements(),
				pageDlfileEntry.getNumberOfElements(), pageDlfileEntry.getSize()));
		return pageableDlFileEntryDto;
	}
	
	public String findByIdDlFileEntry(String id) {
		DlFileEntry dlFileEntry=dlfileEntryRepository.findById(id).get();
		String link=ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/dlFileEntry/viewImage/")
				.path(dlFileEntry.getId()).toUriString();
		return link;
	}

}
