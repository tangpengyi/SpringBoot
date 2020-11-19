package com.tpy.response_result.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ResponseResult {

    private Integer code;
    private String msg;
    private Object obj;

    public static ResponseResult success(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(200);
        responseResult.setMsg("成功");

        return responseResult;
    }

    public static ResponseResult failed(){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(500);
        responseResult.setMsg("失败");

        return responseResult;
    }
}
