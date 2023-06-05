package com.feidian.service.impl;

import com.feidian.mapper.VideoMapper;
import com.feidian.po.VideoPO;
import com.feidian.service.VideoService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    @Override
    public void insertVideo(VideoPO videoPO) {
        videoMapper.insertVideo(videoPO);
    }

    @Override
    public long[] homeRecommend() {

        //创建一个新的数组
        long[] arr = new long[40];
        //把随机数存入数组当中
        Random r = new Random();
        for (Integer i = 0; i < arr.length; i++) {
            //每循环一次，就会生成一个随机数
            Integer num = r.nextInt(40 + 1);
            //把生成的随机数存入数组当中
            arr[i] = num;
        }

        return arr;
    }

    @Override
    public VideoPO findByVideoId(@Param("videoId") long videoId) {
        return videoMapper.findByVideoId(videoId);
    }

    @Override
    public List<VideoPO> findByUserId(long userId) {
        return videoMapper.findByUserId(userId);
    }

    @Override
    public void deleteVideo(long videoId) {
        videoMapper.deleteVideo(videoId);
    }

    @Override
    public void updateVideoMsg(VideoPO videoPO) {
        videoMapper.updateVideoMsg(videoPO);
    }


}

