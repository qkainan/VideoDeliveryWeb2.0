package com.feidian.service;

import com.feidian.dto.VideoDTO;
import com.feidian.po.VideoPO;
import com.feidian.responseResult.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


public interface VideoService {

//    void insertVideo(VideoPO videoPOVo);
//
//    long[] homeRecommend();
//
//    VideoPO findByVideoId(@Param("videoId") long videoId);
//
//    List<VideoPO> findByUserId(long userId);
//
//
//    void deleteVideo(long videoId);
//
//    void updateVideoMsg(VideoPO videoPO);

    ResponseResult receivingVideo(VideoDTO receivingVideoDTO, MultipartFile dataFile, MultipartFile coverFile);

    ResponseResult displayVideo(long id) throws IOException, URISyntaxException;

    ResponseResult updateVideoInfo(VideoDTO videoDTO);
}
