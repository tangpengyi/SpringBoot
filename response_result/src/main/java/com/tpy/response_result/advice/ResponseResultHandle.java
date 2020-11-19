package com.tpy.response_result.advice;

import com.google.gson.Gson;
import com.tpy.response_result.annototion.ResponseResult;
import com.tpy.response_result.api.ErrorResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResponseResultHandle implements ResponseBodyAdvice<Object> {

    private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

    @Override
    public boolean supports(MethodParameter methodParameter, Class<? extends HttpMessageConverter<?>> aClass) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        // 判断请求 是否有包装标记
        ResponseResult attribute = (ResponseResult) request.getAttribute(RESPONSE_RESULT_ANN);

        return attribute == null ? false : true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class<? extends HttpMessageConverter<?>> aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if(o instanceof ErrorResult){
            com.tpy.response_result.api.ResponseResult failed = com.tpy.response_result.api.ResponseResult.failed();
            ErrorResult errorResult = (ErrorResult) o;
            failed.setObj(errorResult.getMsg());
            return failed;
        }
        com.tpy.response_result.api.ResponseResult success = com.tpy.response_result.api.ResponseResult.success();
        success.setObj(o);
//        return success;
        return new Gson().toJson(success);
    }
}
