package com.feidian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VideoDTO {

    private long id;
    private long userId;

    private String videoName;
    private String videoTitle;
    private String videoType;
    private String videoDescription;

    private String coverUrl;
    private String dataUrl;


}
