package com.example.lessonEnglish.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VideoDto {
    private String description;
    private String name;
    private String link;
    private String param;
}
