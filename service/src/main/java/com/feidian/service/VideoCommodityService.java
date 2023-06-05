package com.feidian.service;

import com.feidian.dto.VideoDTO;
import com.feidian.po.VideoCommodityPO;
import com.feidian.responseResult.ResponseResult;

import java.util.List;

public interface VideoCommodityService {
//    void insertVideoCommodity(VideoCommodityPO videoCommodityPO);
//
//    List<VideoCommodityPO> findByVideoId(long videoId);
//

    ResponseResult uploadVideoCommodity(VideoDTO videoDTO);

    ResponseResult updateVideoCommodityInfo(VideoDTO videoDTO);
}
