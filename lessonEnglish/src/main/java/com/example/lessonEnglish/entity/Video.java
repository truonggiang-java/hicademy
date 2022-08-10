package com.example.lessonEnglish.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "video")
@NoArgsConstructor
@AllArgsConstructor

public class Video extends BaseEntity{
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name ="param")
    private String param;
}

