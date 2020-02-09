package filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter2 implements Filter{
	FilterConfig config;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain nextFilter)
			throws IOException, ServletException {
		//서블릿을 실행하기 전에 수행할 작업
//		request.setCharacterEncoding("UTF-8");
		System.out.println("2");
		nextFilter.doFilter(request,response); // 얘 기준으로
		System.out.println("3");
		
		
		//여기가 서블릿을 실행한 후 수행할 작업은 밑에 
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		System.out.println("ready2");
	}
}
