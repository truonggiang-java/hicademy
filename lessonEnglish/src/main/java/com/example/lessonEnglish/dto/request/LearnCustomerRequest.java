package com.example.lessonEnglish.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LearnCustomerRequest {
    private String name;
    private Integer point;
    private List<String> lesson=new ArrayList<>();
}
