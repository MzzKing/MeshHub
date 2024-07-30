package com.cqnu.controller;

import com.cqnu.bean.Model;
import com.cqnu.bean.User;
import com.cqnu.service.ModelService;
import com.cqnu.utils.AjaxResult;
import org.apache.tomcat.util.http.fileupload.util.mime.MimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

//@RestController
@Controller
@RequestMapping("api/")
public class ModelUploadController {

    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private ModelService modelService;
    @PostMapping("upload")
    public AjaxResult modelUpload(@RequestParam("file") MultipartFile file,Model model,Integer userId) throws IOException {
        System.out.println(userId);
        //保存模型
        AjaxResult ajaxResult = new AjaxResult();
        if (file ==null||file.isEmpty()){
            ajaxResult.setFlag(false);
            ajaxResult.setMessage("上传失败，上传文件为空！");
            return ajaxResult;
        }
        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取扩展名
        String filenameExtension = StringUtils.getFilenameExtension(originalFilename);
        String newFileName = UUID.randomUUID().toString() + "." + filenameExtension;
        //处理文件
        Path temPath = Paths.get(uploadFolder+ File.separator+newFileName);
        Files.write(temPath,file.getBytes());
//        Path objPath = Paths.get(uploadFolder+File.separator+newFileName.replace("."+filenameExtension,".obj"));
        ajaxResult.setFlag(true);
        ajaxResult.setMessage("上传成功");

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CANADA);
        Model model1 = new Model(model.getName(), userId, model.getType(), filenameExtension.toUpperCase(), model.getDescription(), model.getType(),simpleDateFormat.format(new Date()) , 0, "/static/models/" + newFileName, "/static/images/logoTop.png");
        if(modelService.insert(model1)==1){
            ajaxResult.setFlag(true);
        }else ajaxResult.setFlag(false);
        return ajaxResult;
    }

}
