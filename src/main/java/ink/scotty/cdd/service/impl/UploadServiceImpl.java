package ink.scotty.cdd.service.impl;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import ink.scotty.cdd.config.UploadConfig;
import ink.scotty.cdd.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @author Scott
 * @date 2020/5/2
 */
@Service("uploadService")
public class UploadServiceImpl implements UploadService {

    @Autowired
    private UploadConfig uploadConfig;

    // 构造一个带指定Region对象的配置类，region0：华东
    private Configuration cfg = new Configuration(Region.region0());

    private UploadManager uploadManager = new UploadManager(cfg);

    @Override
    public String uploadFile(MultipartFile file) {
        System.out.println("uploadConfig = " + uploadConfig);
        Auth auth = Auth.create(uploadConfig.getAccessKey(), uploadConfig.getSecretKey());
        String token = auth.uploadToken(uploadConfig.getBucket());
        try {
            // 获取文件原名称
            String originalFilename = file.getOriginalFilename();
            System.out.println("originalFilename = " + originalFilename);
            // 文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileKey = UUID.randomUUID().toString() + suffix;
            Response response = uploadManager.put(file.getInputStream(), fileKey, token, null, null);
            System.out.println("response.address = " + response.address);
            return uploadConfig.getDomain() + "/" + fileKey;
        } catch (QiniuException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
