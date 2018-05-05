package com.handsome.shop.framework;

import com.handsome.shop.util.GsonConverter;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * by wangrongjun on 2018/5/3.
 */
public class RestfulApiReturnValueHandler implements HandlerMethodReturnValueHandler {

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        // 只有这里返回true，springmvc才会调用handleReturnValue方法。
        return true;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest) throws Exception {
        mavContainer.setRequestHandled(true);
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);

        if (!request.getServletPath().startsWith("/rest/")) {
            return;
        }

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        response.setContentType("application/json;charset=UTF-8");

        ReturnObjectToJsonIgnoreFields ignoreFieldsAnno = returnType.getMethodAnnotation(ReturnObjectToJsonIgnoreFields.class);
        String[] ignoreFields = null;
        if (ignoreFieldsAnno != null) {
            ignoreFields = ignoreFieldsAnno.value();
        }
        String json = GsonConverter.toJson(returnValue, ignoreFields);
        response.getWriter().append(json).flush();
    }

}
