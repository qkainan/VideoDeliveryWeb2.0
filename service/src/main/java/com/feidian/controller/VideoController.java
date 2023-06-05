package com.feidian.controller;

import com.feidian.dto.DataAndCoverResource;
import com.feidian.dto.VideoDTO;
import com.feidian.po.CommodityPO;
import com.feidian.po.UserPO;
import com.feidian.po.VideoCommodityPO;
import com.feidian.po.VideoPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityService;
import com.feidian.service.UserService;
import com.feidian.service.VideoCommodityService;
import com.feidian.service.VideoService;
import com.feidian.util.JwtUtil;
import com.feidian.util.fileUploadUtil;
import com.feidian.vo.CoverVo;
import com.feidian.vo.DisplayVideoVO;
import com.feidian.vo.UploadVideoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    @Transactional
    @GetMapping("/getHomeRecommend")
    public ResponseResult homeRecommend() throws IOException, URISyntaxException {
        List<VideoPO> list = new ArrayList<>();

        HashMap<Long,byte[]> map = new HashMap<>();

        for (long videoId:videoService.homeRecommend()) {
            VideoPO videoPO = videoService.findByVideoId(videoId);
            list.add(videoPO);
            CoverVo coverVo = new CoverVo();
            /*DataAndCoverResource dataAndCoverResource =new DataAndCoverResource(fileUploadUtil.getFileVideo(videoPO.getDataUrl()).getFileByte(),
                    fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte());*/

            byte[] coverFile = fileUploadUtil.getFileImage(videoPO.getCoverUrl()).getFileByte();
            map.put(videoPO.getId(),coverFile);
        }
        return new ResponseResult(200, "操作成功",map);
    }


    @Transactional
    @GetMapping("/getDisplayVideo")
    public ResponseResult getDisplayVideoVo(long id) throws IOException, URISyntaxException {
        VideoPO videoPO = videoService.findByVideoId(id);
        UserPO userPO = userService.findById(videoPO.getUserId());

        List<VideoCommodityPO> videoCommodityPOList = videoCommodityService.findByVideoId(id);
        List<CommodityPO> commodityPOList = new ArrayList<>();

        for (VideoCommodityPO vc: videoCommodityPOList) {
            CommodityPO byCommodityIdPO = commodityService.findByCommodityId(vc.getCommodityId());
            commodityPOList.add(byCommodityIdPO);
        }

        DisplayVideoVO displayVideoVo = new DisplayVideoVO(videoPO.getId(), videoPO.getVideoTitle(), userPO.getId(),
                userPO.getUsername(), videoPO.getDataUrl(), videoPO.getCoverUrl(),
                videoPO.getVideoDescription(), videoPO.getCreateTime(), commodityPOList);

        DataAndCoverResource dataAndCoverResource = new DataAndCoverResource(
                fileUploadUtil.getFileVideo(displayVideoVo.getVideoDataUrl()).getFileByte(),
                fileUploadUtil.getFileImage(displayVideoVo.getVideoCoverUrl()).getFileByte());

        HashMap<DisplayVideoVO, DataAndCoverResource> map = new HashMap<>();
        map.put(displayVideoVo,dataAndCoverResource);
        return new ResponseResult(200,"操作成功",fileUploadUtil.getFileVideo(displayVideoVo.getVideoDataUrl()));
    }

    @Transactional
    @PostMapping("/postUploadVideo")
    public ResponseResult uploadVideo( @RequestPart("uploadVideoVo") UploadVideoVO uploadVideoVo,
                                       @RequestPart("dataFile") MultipartFile dataFile,
                                       @RequestPart("coverFile") MultipartFile coverFile){

        long userId = JwtUtil.getUserId();

        String videoDataUrl = "";
        String videoCoverUrl = "";

        //Todo 最好放在配置文件
        // 定义保存路径
        String uploadVideoCoverDir = "D:/uploads/videos/cover/";
        uploadVideoFile(coverFile,uploadVideoCoverDir);
        videoCoverUrl = uploadVideoFile(coverFile,uploadVideoCoverDir);
        uploadVideoVo.setCoverUrl(videoCoverUrl);

        String uploadVideoDataDir = "D:/uploads/videos/data/";
        uploadVideoFile(dataFile,uploadVideoDataDir);
        videoDataUrl = uploadVideoFile(dataFile,uploadVideoDataDir);
        uploadVideoVo.setDataUrl(videoDataUrl);
        uploadVideoVo.setVideoName(dataFile.getOriginalFilename());
        uploadVideoVo.setVideoName(dataFile.getOriginalFilename());

        uploadVideoVo.setUserId(userId);

        VideoPO videoPO = new VideoPO(uploadVideoVo.getUserId(), uploadVideoVo.getVideoName(),
                uploadVideoVo.getVideoTitle(), uploadVideoVo.getVideoType(), uploadVideoVo.getVideoDescription(),
                uploadVideoVo.getCoverUrl(), uploadVideoVo.getDataUrl(),1);

        videoService.insertVideo(videoPO);

        return new ResponseResult(200,"操作成功");
    }



    @Transactional
    @PostMapping("/postUploadVideoCommodity")
    public ResponseResult postUploadVideoCommodity(@RequestBody VideoDTO videoDTO){
        long userId = JwtUtil.getUserId();
        VideoPO videoPO = videoService.findByVideoId(videoDTO.getVideoId());

        //将商品安排到video推荐商品里
        for (long cId : videoDTO.getCommodityIdList()) {
            VideoCommodityPO videoCommodityPO = new VideoCommodityPO(userId, videoDTO.getVideoId(), cId,
                    videoPO.getVideoStatus());
            videoCommodityService.insertVideoCommodity(videoCommodityPO);
        }

        return new ResponseResult(200,"操作成功");
    }

    @Transactional
    @PostMapping ("/postDeleteVideo")
    public ResponseResult deleteVideo(long videoId){
        videoService.deleteVideo(videoId);
        return new ResponseResult(200, "操作成功");
    }
    @Transactional
    @PutMapping("/putUpdateVideoMsg")
    public ResponseResult putUpdateVideoMsg(@RequestBody VideoDTO videoDTO) {
        VideoPO videoPO = new VideoPO(videoDTO.getVideoId(),videoDTO.getVideoTitle(), videoDTO.getVideoType(),
                videoDTO.getVideoDescription());
        videoService.updateVideoMsg(videoPO);
        return new ResponseResult(200,"操作成功");
    }
    @Transactional
    @PutMapping("/putUpdateVideoCommodityMsg")
    public ResponseResult putUpdateVideoCommodityMsg(@RequestBody VideoDTO videoDTO){

        for (Long cId:videoDTO.getCommodityIdList()) {
            VideoCommodityPO videoCommodityPO = new VideoCommodityPO(videoDTO.getVideoId(),cId);
            videoCommodityService.updateVideoCommodityMsg(videoCommodityPO);
        }
        return new ResponseResult(200,"操作成功");
    }

    public String uploadVideoFile(MultipartFile file,String uploadDir) {
        // 处理文件上传逻辑
        // 进行视频文件的保存、处理等操作
        // 返回相应的结果
        if (file.isEmpty()) {
            return  "0";
        }
        try {
            // 定义保存路径
            // 创建保存目录（如果不存在）
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            // 生成保存文件的路径
            String filePath = uploadDir + file.getOriginalFilename();
            // 保存文件
            file.transferTo(new File(filePath));
            // 在此可以对文件进行进一步处理，例如调用视频处理库进行处理操作
            return filePath;
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Transactional
    @PostMapping("/insertTestVideo")
    public ResponseResult insertTestVideo(UploadVideoVO uploadVideoVo){
        VideoPO videoPO = new VideoPO(uploadVideoVo.getUserId(), uploadVideoVo.getVideoName(),
                uploadVideoVo.getVideoTitle(), uploadVideoVo.getVideoType(), uploadVideoVo.getVideoDescription(),
                uploadVideoVo.getCoverUrl(), uploadVideoVo.getDataUrl(),1);
        videoService.insertVideo(videoPO);
        return new ResponseResult(200,"上传成功");
    }


}
