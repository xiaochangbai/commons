package cn.xdd.utils.servlet;

import java.io.IOException;
import java.lang.annotation.Annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xdd.utils.servlet.annotatioin.*;


/**
 *@author: xchb
 *@date: 2019年12月23日下午4:51:54
 *@description: good good study,day day up
 *基础Servlet，能实现方法级别的请求映射
 *	要求：1、Servlet3.0环境
 *		2、Servlet使用@WebServlet注解完成请求映射
 *		3、处理的方法的参数必需是（顺序不能变）：HttpServletRequest和 HttpServletResponse。
 *	
 *	使用：继承该类就行，在方法上使用对应的注解完成映射，例如：MyGet("/hello")，就映射GET请求/hello路径。
 *		目前支持的注解有：MyGet，MyDelete，MyHEAD,MyPost,MyPut。每一个注解对应一个请求方式。
 *		如果没有相应的注解，则将方法名作为映射路径，能处理任何格式的请求。
 *
 */


public abstract class BaseServlet extends HttpServlet {
	
	/**
	 * 方法映射路径集合
	 */
	private ArrayList<MethodInfo> ms = null; 
	
	private static Logger logger = LoggerFactory.getLogger(BaseServlet.class);
	
	private final static List<Class<? extends Annotation>> ANNOTATION  = 
			Arrays.asList(MyPost.class,MyGet.class,MyDelete.class,MyPut.class,MyHEAD.class);
	
	/**
	 * 初始化
	 */
	@Override
	public void init() throws ServletException {
		super.init();
		ms = new ArrayList<MethodInfo>();
		Method[] methods = this.getClass().getDeclaredMethods();
		MethodInfo mInfo = null;
		for(int i=0;i<methods.length;i++) {
			mInfo = new MethodInfo();
			Method method = methods[i];
			mInfo.setName(method.getName());
			Annotation[] annotations = method.getAnnotations();
			for(int j=0;j<annotations.length;j++) {
				Annotation annotation = annotations[j];
				if(ANNOTATION.contains(annotation.annotationType())) {
					try {
						Class<?> annotationClass = annotation.getClass();
						String type = (String) annotationClass
								.getDeclaredMethod("type").invoke(annotation, null);
						String path = (String) annotationClass
								.getDeclaredMethod("value").invoke(annotation, null);
						mInfo.setRequestType(type);
						mInfo.setRequestPath(path);
						break;
					} catch (Exception e) {
						logger.error("初始化异常\n"+e.getMessage());
					}
				}
			}
			this.ms.add(mInfo);
		}	
		logger.debug("初始化完成\n"+ms);
	}

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String totalMappingPath = req.getPathInfo();
		String requetMethod = req.getMethod();
		if(totalMappingPath==null || requetMethod==null) {
			logger.error("请求参数不能为空【映射路径："+totalMappingPath+", 请求格式："+requetMethod+"】");
			return;
		}
		if(ms == null) {
			logger.error("初始化失败，请勿覆盖init方法。");
		}
		//去掉类上面的映射路径
		String mappingPath = totalMappingPath.replace(this.getClassMappingPath(), "");
		//查看容器中查找出对应的方法名
		String methodName = checkPath(ms,mappingPath,requetMethod);
		if(methodName!=null) {
			Class<? extends BaseServlet> class1 = this.getClass();
			try {
				Method method = class1.getMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
				try {
					//执行对应的方法
					method.setAccessible(true);
					method.invoke(this, req,resp);
					logger.debug("执行完毕【请求："+totalMappingPath+"==>>"+methodName+"】");
				} catch (Exception e) {
					logger.error("方法执行异常"+method.getName()+",\n"+e.getMessage());
				}
					
			} catch (Exception e) {
				logger.error("方法匹配异常，方法名【"+methodName+"】\n"+e.getMessage());
			}
		}else {
			logger.debug("没有对应的处理方法，【映射路径："+totalMappingPath+",请求方式："+requetMethod+"】");
			return;
		}
	}
	
	/**
	 * 匹配方法名
	 * @param ms2
	 * @param mappingPath
	 * @param requetMethod
	 * @return
	 */
	private String checkPath(ArrayList<MethodInfo> ms2, String mappingPath,String requetMethod) {
		Iterator<MethodInfo> iterator = ms2.iterator();
		String methodName = null;
		while(iterator.hasNext()) {
			MethodInfo next = iterator.next();
			//注解为空时，根据方法名匹配
			if(next.getRequestType() == null && mappingPath.substring(1).equals(next.getName()) ) {
				methodName = next.getName();
			}
			//注解不为空，根据注解类型和方法名进行匹配
			if(next.getRequestType()!=null 
					&& next.getRequestType().toUpperCase().equals(requetMethod.toUpperCase())
					&& mappingPath.equals(next.getRequestPath())) {
				methodName = next.getName();
			}
		}
		return methodName;
	}
	
	/**
	 * 获取类映射路径
	 * @return
	 */
	public String getClassMappingPath() {
		String classMappingPath = null;
		WebServlet webServlet = this.getClass().getAnnotation(WebServlet.class);
		if(webServlet!=null) {
			System.out.println(webServlet);
			String[] invoke = null;
			try {
				invoke = (String[]) webServlet.getClass().getDeclaredMethod("value").invoke(webServlet);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			if(invoke.length>0) {
				classMappingPath = invoke[0];
				logger.debug("类请求路径 = "+classMappingPath);
			}
		}else {
			logger.error("采用的不是Servlet3.0新特性，获取类映射路径失败");
		}
		return classMappingPath;
	}


	public abstract void hello(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException;
}
