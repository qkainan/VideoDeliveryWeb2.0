package com.feidian.controller;

import com.feidian.bo.VideoBO;
import com.feidian.dto.VideoDTO;

import com.feidian.po.VideoCommodityPO;
import com.feidian.po.VideoPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityService;
import com.feidian.service.UserService;
import com.feidian.service.VideoCommodityService;
import com.feidian.service.VideoService;


import com.feidian.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;


@RestController
public class VideoController {

    @Autowired
    private UserService userService;

    @Autowired
    private VideoService videoService;

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private VideoCommodityService videoCommodityService;


    //上传视频
    @PostMapping("/receivingVideo")
    public ResponseResult receivingVideo( @RequestPart("uploadVideoVo") VideoDTO receivingVideoDTO,
                                          @RequestPart("dataFile") MultipartFile dataFile,
                                          @RequestPart("coverFile") MultipartFile coverFile){
        return videoService.receivingVideo(receivingVideoDTO, dataFile, coverFile);
    }

    //展示视频
    @GetMapping("/displayVideo")
    public ResponseResult displayVideo(long id) throws IOException, URISyntaxException {
       return videoService.displayVideo(id);
    }

    @PutMapping("/updateVideoInfo")
    public ResponseResult updateVideoInfo(@RequestBody VideoDTO videoDTO) {
        return videoService.updateVideoInfo(videoDTO);
    }

    @PostMapping ("/deleteVideo")
    public ResponseResult deleteVideo(long videoId){
        return videoService.deleteVideo(videoId);
    }

    //给视频添加推荐商品
    @PostMapping("/uploadVideoCommodity")
    public ResponseResult uploadVideoCommodity(@RequestBody VideoDTO videoDTO){
        return videoCommodityService.uploadVideoCommodity(videoDTO);
    }

    //更改商品的推荐商品
    @PutMapping("/updateVideoCommodityInfo")
    public ResponseResult putUpdateVideoCommodityMsg(@RequestBody VideoDTO videoDTO){
        return videoCommodityService.updateVideoCommodityInfo(videoDTO);
    }

//    @GetMapping("/getHomeRecommend")
//    public ResponseResult homeRecommend() throws IOException, URISyntaxException {
//        List<VideoPO> list = new ArrayList<>();
//
//        HashMap<Long,byte[]> map = new HashMap<>();
//
//        for (long videoId:videoService.homeRecommend()) {
//            VideoPO videoPO = videoService.findByVideoId(videoId);
//            list.add(videoPO);
//            CoverVo coverVo = new CoverVo();
//            /*DataAndCoverResource dataAndCoverResource =new DataAndCoverResource(fileUploadUtil.getFileVideo(videoPO.getDataUrl()).getFileByte(),
//                    fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte());*/
//
//            byte[] coverFile = fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte();
//            map.put(videoPO.getId(),coverFile);
//        }
//        return new ResponseResult(200, "操作成功",map);
//    }


}
