package ink.scotty.cdd.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Scott
 * @date 2020/5/2
 */
public interface UploadService {
    String uploadFile(MultipartFile file);
}
