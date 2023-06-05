package com.feidian.service;

import com.feidian.po.VideoPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VideoService {

    void insertVideo(VideoPO videoPOVo);

    long[] homeRecommend();

    VideoPO findByVideoId(@Param("videoId") long videoId);

    List<VideoPO> findByUserId(long userId);


    void deleteVideo(long videoId);

    void updateVideoMsg(VideoPO videoPO);
}
