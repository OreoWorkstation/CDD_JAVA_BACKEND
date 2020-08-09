package ink.scotty.cdd.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Scott
 * @date 2020/5/2
 */
@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadConfig {

    private String domain;
    private String accessKey;
    private String secretKey;
    private String bucket;
}
