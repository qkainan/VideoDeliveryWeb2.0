package com.feidian.service;

import com.feidian.po.VideoCommodityPO;

import java.util.List;

public interface VideoCommodityService {
    void insertVideoCommodity(VideoCommodityPO videoCommodityPO);

    List<VideoCommodityPO> findByVideoId(long videoId);

    void updateVideoCommodityMsg(VideoCommodityPO videoCommodityPO);
}
