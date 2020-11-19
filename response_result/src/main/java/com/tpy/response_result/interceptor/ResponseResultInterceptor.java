package com.tpy.response_result.interceptor;

import com.tpy.response_result.annototion.ResponseResult;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class ResponseResultInterceptor implements HandlerInterceptor {
    private static final String RESPONSE_RESULT_ANN = "RESPONSE_RESULT_ANN";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if(handler instanceof HandlerMethod){
            final HandlerMethod handlerMethod = (HandlerMethod) handler;
            Class<?> clazz = handlerMethod.getBeanType();
            Method method = handlerMethod.getMethod();

            // 判断是否在在类上添加注解
            if(clazz.isAnnotationPresent(ResponseResult.class)){
                // 设置此请求的返回体，需要包装、往下传递，在ResponseBodyAdvice接口进行判断
                request.setAttribute(RESPONSE_RESULT_ANN,clazz.getAnnotation(ResponseResult.class));
            }else if(method.isAnnotationPresent(ResponseResult.class)){
                request.setAttribute(RESPONSE_RESULT_ANN,method.getAnnotation(ResponseResult.class));
            }
        }
        return true;
    }
}
