package com.example.lessonEnglish.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackRequest {
    public String nameStudent;
    public String parentStundent;
    public String description;
}
