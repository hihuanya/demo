package com.example.demo.filter;

import com.example.demo.util.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chuliuhuan
 * @date 2022-11-20 17:43
 */

public class AuthFilter implements Filter {


    private JwtUtils jwtUitls;

    public static  final String CONTAINS_STR= "queryEnergyListByDate";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Map<String,String> map=new HashMap<>();
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if(requestURI!=null){
//            if(StringUtils.equalsIgnoreCase("/login",requestURI) ){  // 只有非登录接口会验证token
            // 只要包含 /queryEnergyListByDate 才会走token
            if (!requestURI.contains(CONTAINS_STR)) {
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            } else {
                String token = ((HttpServletRequest) servletRequest).getHeader("aaaa");
                if(StringUtils.isNotBlank(token)){
                    //token验证结果

                    int verify  = JwtUtils.verify(token,(HttpServletRequest) servletRequest);
                    if(verify != 1){
                        //验证失败
                        if(verify == 2){
                            map.put("500","token已过期");
                        }else if(verify == 0){
                            map.put("500","用户信息验证失败");
                        }
                    }else if(verify  == 1){
                        //验证成功，放行
                        filterChain.doFilter(servletRequest,servletResponse);
                        return;
                    }
                }else {
                    map.put("500","未携带token信息");
                }
            }
        }


        servletResponse.setContentType("application/json");
        servletResponse.setCharacterEncoding("utf-8");
        PrintWriter writer = servletResponse.getWriter();
        writer.write(map.toString());
        writer.flush();
        writer.close();

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

