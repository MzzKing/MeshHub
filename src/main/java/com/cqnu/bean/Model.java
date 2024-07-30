package com.cqnu.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class Model {
    private Integer id;
    private String name;
    private Integer userId;
    private String type;
    private String fileForm;
    private String description;
    private String label;
    private String uploadDate;
    private Integer downloadCount;
    private String url;
    private String imageUrl;

    public Model(String name, Integer userId, String type, String fileForm, String description, String label, String uploadDate, Integer downloadCount, String url, String imageUrl) {
        this.name = name;
        this.userId = userId;
        this.type = type;
        this.fileForm = fileForm;
        this.description = description;
        this.label = label;
        this.uploadDate = uploadDate;
        this.downloadCount = downloadCount;
        this.url = url;
        this.imageUrl = imageUrl;
    }



}
