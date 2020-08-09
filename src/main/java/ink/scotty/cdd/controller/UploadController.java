package ink.scotty.cdd.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.R;
import ink.scotty.cdd.service.UploadService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author Scott
 * @date 2020/5/2
 */
@RestController
@RequestMapping("/upload")
public class UploadController extends ApiController {

    @Resource
    UploadService uploadService;

    @PostMapping
    public R<?> uploadFileYun(@RequestParam("file") MultipartFile file) {
        System.out.println("file.getName() = " + file.getName());
        return success(uploadService.uploadFile(file));
    }
}
