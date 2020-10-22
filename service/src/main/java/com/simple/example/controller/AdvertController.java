package com.simple.example.controller;

import com.simple.common.api.BaseResponse;

import com.simple.common.api.GenericRequest;
import com.simple.common.api.GenericResponse;
import com.simple.common.controller.BaseController;
import com.simple.example.dto.BannerVO;
import com.simple.example.service.AdvertService;

import com.simple.core.data.pageBean.SinglePageBean;

import com.simple.common.auth.LoginRequired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

/**
 * 广告资讯业务控制层
 *
 * @author hejinguo
 * @version $Id: ApiAdvertController.java, v 0.1 2020年7月25日 下午12:03:02
 */
@RestController
@RequestMapping("/advertService/pc")
public class AdvertController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);
    @Autowired
    private AdvertService advertService;


    /**
     * 获取广告列表列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/getAdvertList"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse getAdvertList(@RequestBody GenericRequest req) {

        Integer currentPage = req.getInt("currentPage");
        Integer pageSize = req.getInt("pageSize");
        GenericResponse res = GenericResponse.build();
        try {
            SinglePageBean<BannerVO> bannerList = this.advertService.getAdvertList(currentPage, pageSize);
            res.addKey$Value("advertList",
                    bannerList.getList() != null ? bannerList.getList() : new ArrayList<BannerVO>());
            res.addKey$Value("currentPage", bannerList.getCurrentPage());
            res.addKey$Value("totalPage", bannerList.getTotalPage());
            return res;
        } catch (Exception e) {
            return this.handleExeption(e, "failed to get advert list");
        }

    }

    /**
     * 添加广告
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/addAdvert"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse addAdvert(@RequestBody GenericRequest req) {
        try {
            this.advertService.addAdvert(req);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to add advert");
        }
    }


    /**
     * 修改广告
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/updateAdvert"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody BaseResponse updateAdvert(@RequestBody GenericRequest req) {
        try {
            this.advertService.updateAdvert(req);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to update advert");
        }

    }

    /**
     * 删除广告
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/deleteAdvert"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse deleteAdvert(@RequestBody GenericRequest req) {
        try {
            Integer advertId = req.getInteger("id");
            this.advertService.deleteAdvert(advertId);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to delete advert");
        }

    }


    /**
     * 获取资讯列表
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/getInformationList"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse getInformationList(@RequestBody GenericRequest req) {
        try {

            Integer currentPage = req.getInt("currentPage");
            Integer pageSize = req.getInt("pageSize");
            SinglePageBean<BannerVO> bannerList = this.advertService
                    .getPcInformationList(currentPage,pageSize);

            GenericResponse res = GenericResponse.build();
            res.addKey$Value("informationList",
                    bannerList.getList() != null ? bannerList.getList() : new ArrayList<BannerVO>());
            res.addKey$Value("currentPage", bannerList.getCurrentPage());
            res.addKey$Value("totalPage", bannerList.getTotalPage());
            return res;
        } catch (Exception e) {
            return this.handleExeption(e, "failed to getInformationList");
        }

    }

    /**
     * 添加资讯
     * @param req
     * @return
     */
    @PostMapping(value = {"/addInformation"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse addInformation(@RequestBody GenericRequest req) {
        try {
            this.advertService.addInformation(req);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to add advert");
        }
    }

    /**
     * 修改资讯
     *
     * @param req
     * @return
     */
    @PostMapping(value = {"/updateInformation"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse updateInformation(@RequestBody GenericRequest req) {
        try {
            this.advertService.updateInformation(req);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to update information");
        }

    }


    /**
     * 删除资讯
     * @param req
     * @return
     */
    @PostMapping(value = {"/deleteInformation"}, consumes = {"application/json"}, produces = {"application/json"})
    @LoginRequired
    public @ResponseBody
    BaseResponse deleteInformation(@RequestBody GenericRequest req) {
        try {
            Integer advertId = req.getInteger("id");
            this.advertService.deleteInformation(advertId);
            return BaseResponse.build();
        } catch (Exception e) {
            return this.handleExeption(e, "failed to delete information");
        }

    }



}
