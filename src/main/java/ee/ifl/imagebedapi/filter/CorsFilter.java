package ee.ifl.imagebedapi.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //response里添加处理允许跨域的响应头信息
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        //允许任意源发起跨域请求
        httpResponse.addHeader("Access-Control-Allow-Origin", "*");
        //允许自定义请求头
        httpResponse.addHeader("Access-Control-Allow-Headers", "*");
        //允许非get或post的请求方式
        httpResponse.addHeader("Access-Control-Allow-Methods", "*");

        chain.doFilter(request, response);
    }
}
