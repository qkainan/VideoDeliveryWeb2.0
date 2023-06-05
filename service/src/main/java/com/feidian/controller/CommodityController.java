package com.feidian.controller;

import com.feidian.dto.CommodityDTO;

import com.feidian.responseResult.ResponseResult;
import com.feidian.service.CommodityImageService;
import com.feidian.service.CommodityService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.URISyntaxException;



@RestController
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    @Autowired
    private CommodityImageService commodityImageService;

    @PostMapping(value = "/uploadCommodity", consumes = "multipart/form-data")
    public ResponseResult uploadCommodity(@RequestPart(name = "commodityDTO") CommodityDTO commodityDTO,
                                          @RequestPart(name = "coverFile") MultipartFile coverFile,
                                          @RequestPart(name = "imageFile") MultipartFile[] imageFile) {
        return commodityService.uploadCommodity(commodityDTO, coverFile, imageFile);
    }

    @GetMapping("/displayCommodity")
    public ResponseResult displayCommodity(long commodityId) throws IOException, URISyntaxException {
        return commodityService.displayCommodity(commodityId);
    }

    @PostMapping("/deleteCommodity")
    public ResponseResult deleteCommodity(long commodityId){
        return commodityService.deleteCommodity(commodityId);
    }

    @PutMapping("/putUpdateCommodityMsg")
    public ResponseResult updateCommodityMsg(@RequestBody CommodityDTO commodityDTO){
        return commodityService.updateCommodity(commodityDTO);
    }


}
