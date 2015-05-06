package org.dannis.cms.controller;

import org.apache.commons.io.FileUtils;
import org.dannis.cms.MobilePhoneNumberExcelParser;
import org.dannis.cms.model.MobilePhoneNumber;
import org.dannis.cms.result.BaseResult;
import org.dannis.cms.service.MobilePhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author deng.zhang
 * @version 1.0.0
 * @date 2015-04-26 12:03
 * @since 1.0.0
 */
@Controller
@RequestMapping(value = "/mobilePhoneNumber")
public class MobilePhoneNumberController {
    /**
     *
     */
    @Autowired
    private MobilePhoneService mobilePhoneService;

    @RequestMapping(value = "/save.json")
    @ResponseBody
    public void save() {
        mobilePhoneService.save(null);
    }

    @RequestMapping(value = "/upload.json", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult upload(@RequestParam("phoneNumberExcel") MultipartFile file,HttpServletRequest request) throws IOException {
        BaseResult baseResult = new BaseResult();
        if (!file.isEmpty()) {
            ServletContext sc = request.getSession().getServletContext();
            String dir = sc.getRealPath("/upload");    //设定文件保存的目录

            List<MobilePhoneNumber> mobilePhoneNumbers = MobilePhoneNumberExcelParser.parseExcel(file.getInputStream());

            String filename = file.getOriginalFilename();    //得到上传时的文件名
            FileUtils.writeByteArrayToFile(new File(dir, filename), file.getBytes());

            baseResult.setSuccess(true);
            baseResult.setMessage("导入成功，共导入" + mobilePhoneNumbers.size() + "条手机号码");
        }
        return baseResult;
    }
}
