<h1>BaseServlet-------请求转换器</h1>
<ul>
	<li><h3>@author: XCHB</h3></li>
	<li><h3>@target: 简化Servlet开发</h3></li>
	<li><h3>@version: 1.0.1-SNAPSHOT</h3></li>
	<li><h3>@date: Tue Dec 24 14:53:39 CST 2019</h3></li>
</ul><hr/><hr/>

<h2>简介</h2>
<b>Servlet开发简化工具，能将请求映射到方法级别。</b>
<br/>
<h4>要求：</h4>
	<ul>
		<li>Servlet3.0环境</li>
		<li>Servlet使用@WebServlet注解完成请求映射</li>
		<li>处理的方法的参数必需是（顺序不能变）：HttpServletRequest和 HttpServletResponse。</li>
	</ul>
<h4>使用：</h4>
	<ul>
		<li>继承BaseServlet，在方法上使用对应的注解完成映射，例如：MyGet("/hello")，就映射GET请求/hello路径。</li>
		<li>目前支持的注解有：MyGet，MyDelete，MyHEAD,MyPost,MyPut。每一个注解对应一个请求方式。</li>
		<li>如果没有相应的注解，则将方法名作为映射路径，能处理任何格式的请求。</li>
	</ul>
<h4>原理：</h4>
	<ul>
		<li>初始化：BaseServlet类中覆写了父类的init()方法，在该方法中扫描了当前类中所有的方法，并将每个方法都封装成一个MethodInfo对象。</li>
		<li>匹配调用：在BaseServlet的service()方法中根据请求的url路径匹配对应的方法，根据方法名完成反射调用。</li>
	</ul>
