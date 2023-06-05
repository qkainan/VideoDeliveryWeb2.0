package com.feidian.controller;


import com.feidian.po.CommodityImagePO;
import com.feidian.po.CommodityPO;
import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityImageService;
import com.feidian.service.CommodityService;
import com.feidian.util.JwtUtil;
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
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityImageService commodityImageService;
    @Transactional
    @GetMapping("/getCommodity")
    public ResponseResult findByCommodityId(long commodityId) throws IOException, URISyntaxException {
        CommodityPO commodityPO = commodityService.findByCommodityId(commodityId);

        if (commodityPO != null) {
            CommodityVO commodityVo = new CommodityVO(commodityPO.getId(), commodityPO.getCommodityName(),
                    commodityPO.getCommodityType(), commodityPO.getPrice(), commodityPO.getCommodityAddress(),
                    commodityPO.getCommodityDescription(), commodityPO.getCoverUrl());

            List<byte[]> imageFileList = new ArrayList<>();
            List<CommodityImagePO> commodityImageList = commodityImageService.findByCommodityId(commodityVo.getId());

            if (commodityImageList != null) {
                for (CommodityImagePO ci : commodityImageList) {
                    imageFileList.add(fileUploadUtil.getFileImage(ci.getImageUrl()).getFileByte());
                }
            }

            ImageAndCoverResource imageAndCoverResource = new ImageAndCoverResource(
                    fileUploadUtil.getFileImage(commodityVo.getCoverUrl()).getFileByte(), imageFileList);

            Map<CommodityVO, ImageAndCoverResource> map = new HashMap<>();
            map.put(commodityVo, imageAndCoverResource);
            return new ResponseResult(200, "操作成功", map);
        } else {
            return new ResponseResult(404, "未找到商品", null);
        }
    }

    @Transactional
    @GetMapping("/getDisplayCommodity")
    public ResponseResult getDisplayCommodity(long commodityId) throws IOException, URISyntaxException {
        HashMap<Long, ImageAndCoverResource> map = new HashMap<>();
        CommodityPO commodityPO = commodityService.findByCommodityId(commodityId);
        List<CommodityImagePO> commodityImageList = commodityImageService.findByCommodityId(commodityId);
        List<byte[]> imageResourceList = new ArrayList<>();

        for (CommodityImagePO commodityImage : commodityImageList) {
            imageResourceList.add(fileUploadUtil.getFileImage(commodityImage.getImageUrl()).getFileByte());
        }

        ImageAndCoverResource imageAndCoverResource =
                new ImageAndCoverResource(fileUploadUtil.getFileImage(commodityPO.getCoverUrl()).getFileByte(),
                        imageResourceList);
        map.put(commodityPO.getId(), imageAndCoverResource);
        return new ResponseResult(200, "操作成功", map);
    }
    @Transactional
    @PutMapping("/putUpdateCommodityMsg")
    public ResponseResult updateCommodityMsg(@RequestBody CommodityDTO commodityDTO){

        CommodityPO commodityPO = new CommodityPO(commodityDTO.getCommodityId(), JwtUtil.getUserId(),
                commodityDTO.getCommodityName(), commodityDTO.getCommodityType(),
                commodityDTO.getPrice(), commodityDTO.getCommodityDescription(),
                commodityDTO.getCommodityAddress(), commodityDTO.getCoverUrl());

        commodityService.updateCommodity(commodityDTO);

        return new ResponseResult(200,"更改成功");
    }

    Transactional
    @PostMapping("/deleteCommodity")
    public ResponseResult deleteCommodity(long commodityId){
        commodityService.deleteCommodity(commodityId);
        return new ResponseResult(200, "删除成功");
    }


    public String saveCommodityFile(MultipartFile file, String uploadDir) {
        // 处理文件上传逻辑
        // 进行视频文件的保存、处理等操作
        // 返回相应的结果

        if (file.isEmpty()) {
            return "未选择文件";
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

            return filePath;
        } catch (IOException e) {
            return "上传失败" + e.getMessage();
        }
    }

    @Transactional
   @PostMapping(value = "/postUploadCommodity", consumes = "multipart/form-data")
    public ResponseResult uploadCommodity(@RequestPart(name = "commodityDTO") CommodityDTO commodityDTO,
                                          @RequestPart(name = "coverFile") MultipartFile coverFile,
                                          @RequestPart(name = "imageFile") MultipartFile[] imageFile) {

        long userId = JwtUtil.getUserId();

        String commodityCoverUrl = "";
        String uploadCommodityCoverDir = "D:/uploads/commodities/cover/";
        saveCommodityFile(coverFile, uploadCommodityCoverDir);
        commodityCoverUrl = saveCommodityFile(coverFile, uploadCommodityCoverDir);
        commodityDTO.setCoverUrl(commodityCoverUrl);


        commodityDTO.setUserId(userId);

        CommodityPO commodityPO = new CommodityPO(commodityDTO.getCommodityId(), commodityDTO.getUserId(),
                commodityDTO.getCommodityName(), commodityDTO.getCommodityType(),
                commodityDTO.getPrice(), commodityDTO.getCommodityDescription(),
                commodityDTO.getCommodityAddress(), commodityDTO.getCoverUrl());


        String commodityImageUrl = "";
        String uploadCommodityImageDir = "D:/uploads/commodities/image/";
        for (MultipartFile multipartFile :imageFile) {
            saveCommodityFile(multipartFile, uploadCommodityImageDir);
            commodityImageUrl = saveCommodityFile(multipartFile, uploadCommodityImageDir);
            commodityImageService.insertCommodityImage(commodityPO.getId(), commodityImageUrl, 1);
        }

        commodityService.insertCommodity(commodityPO);

        return new ResponseResult(200, "操作成功");
    }



}
