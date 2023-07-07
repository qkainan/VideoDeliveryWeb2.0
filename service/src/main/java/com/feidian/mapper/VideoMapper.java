package com.feidian.mapper;

import com.feidian.bo.VideoBO;
import com.feidian.po.Video;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoMapper {
    void insertVideo(VideoBO videoBO);

    Video findByVideoId(@Param("videoId") Long videoId);

    void updateVideoInfo(VideoBO videoBO);

    void deleteVideo(Long videoId);

    List<Video> findByUserId(Long userId);



}
