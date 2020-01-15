package com.joey.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpServletRequest;

@Component
@CrossOrigin
public class ManagerFilter extends ZuulFilter {
    @Override
    public String filterType() {
        /*表示在请求前或后执行,
        pre 请求被路由前调用
        route 路由请求时调用
        error 错误时调用
        post 在路由和error过滤器之后被调用*/
        return "pre";
    }

    @Override
    public int filterOrder() {
        /*数字越小越先执行*/
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        /*当前过滤器是否开启*/
        return true;
    }


    /*过滤器内执行的操作，返回任何值(包括Null)都表示继续执行
     * */
    @Override
    public Object run() throws ZuulException {
        System.out.println("经过了过滤器");

//        /*得到request上下文*/
//        RequestContext requestContext = RequestContext.getCurrentContext();
//        /*得到request域*/
//        HttpServletRequest request = requestContext.getRequest();
//        /*获取头信息*/
//        String header = request.getHeader("Authorization");
//        /*通过网关时会丢失头信息，因此在此把头信息继续传下去*/
//        if (!StringUtil.isNullOrEmpty(header)) {
//            System.out.println(header);
//            requestContext.addZuulRequestHeader("Authorization",header);
//        }
        return null;
    }
}
