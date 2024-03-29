package top.erhuoduoduo.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.erhuoduoduo.response.Result;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @program: Erhuoduoduo_Platform_Springboot_System
 * @description: 文件上传接口类
 * @author: collapsar
 * @create: 2021/12/13 16:15
 */
@RestController
@RequestMapping("/file")
@Api(value = "提供文件上传的接口",tags = "文件上传")
@Slf4j
public class FileController {
    /**
     * 时间格式化
     */
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");

    /**
     * 图片保存路径，自动从yml文件中获取数据
     *   示例： E:/images/
     */
    @Value("${file-save-path}")
    private String fileSavePath;

    @PostMapping(path = "/upload")
    public Result uploadFile(MultipartFile file, HttpServletRequest request) {
        //1.后半段目录：  2020/03/15
        String directory = simpleDateFormat.format(new Date());
        /**
         *  2.文件保存目录  E:/images/2020/03/15/
         *  如果目录不存在，则创建
         */
        File dir = new File(fileSavePath + directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        log.info("图片上传，保存位置：" + fileSavePath + directory);
        //3.给文件重新设置一个名字
        //后缀
        //System.out.println(file.getOriginalFilename());
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newFileName= UUID.randomUUID().toString().replaceAll("-", "")+suffix;
        //4.创建这个新文件
        File newFile = new File(fileSavePath + directory + newFileName);
        //5.复制操作
        try {
            file.transferTo(newFile);
            //协议 :// ip地址 ：端口号 / 文件目录(/images/2020/03/15/xxx.jpg)
            String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/images/" + directory + newFileName;
//            log.info("图片上传，访问URL：" + url);
            // return JsonResult.builder().data(url).message("上传成功！").code("1").build();
            return Result.ok().message("上传成功！").data("url", url);
        } catch (IOException e) {
            // return JsonResult.builder().data(null).message("IO异常！").code("-1").build();
            return Result.error().message("IO异常！");
        }
    }
}
