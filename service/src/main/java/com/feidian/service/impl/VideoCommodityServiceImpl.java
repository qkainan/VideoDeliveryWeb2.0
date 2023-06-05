package com.feidian.service.impl;

import com.feidian.mapper.VideoCommodityMapper;
import com.feidian.po.VideoCommodityPO;
import com.feidian.service.VideoCommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoCommodityServiceImpl implements VideoCommodityService {

    @Autowired
    private VideoCommodityMapper videoCommodityMapper;

    @Override
    public void insertVideoCommodity(VideoCommodityPO videoCommodityPO) {
        videoCommodityMapper.insertVideoCommodity(videoCommodityPO);
    }

    @Override
    public List<VideoCommodityPO> findByVideoId(long videoId) {
        return videoCommodityMapper.findByVideoId(videoId);
    }

    @Override
    public void updateVideoCommodityMsg(VideoCommodityPO videoCommodityPO) {
        videoCommodityMapper.updateVideoCommodityMsg(videoCommodityPO);
    }
}
