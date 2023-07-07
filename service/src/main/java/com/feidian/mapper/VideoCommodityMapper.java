package com.feidian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.feidian.bo.VideoCommodityBO;
import com.feidian.po.VideoCommodity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface VideoCommodityMapper extends BaseMapper<VideoCommodity> {
    void insertVideoCommodity(VideoCommodityBO videoCommodityBO);

    void updateVideoCommodityInfo(VideoCommodityBO videoCommodityBO);

    List<VideoCommodity> findByVideoId(Long videoId);

}
