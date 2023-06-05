package com.feidian.mapper;

import com.feidian.po.VideoCommodityPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoCommodityMapper {
    void insertVideoCommodity(VideoCommodityPO videoCommodityPO);

    List<VideoCommodityPO> findByVideoId(long videoId);

    void updateVideoCommodityMsg(VideoCommodityPO videoCommodityPO);
}
