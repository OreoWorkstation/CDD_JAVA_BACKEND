package ink.scotty.cdd.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse implements Serializable {

    private int status = -1;
    private String message = "need to handle";
    private Object data;

    public JsonResponse setSuccess(Object data) {
        this.status = 200;
        this.message = "ok";
        this.data = data;
        return this;
    }

    public JsonResponse setError(Object data) {
        this.status = 500;
        this.message = "error";
        this.data = data;
        return this;
    }

}
