package cn.xdd.utils.components;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@author: xchb
 *@date: 2020年1月9日下午4:53:23
 *@description: 类扫描器
 */
public class ClassScannerHandler {
	
	/**
	 * 存放类的全路径f
	 */
	private ArrayList<String> fullClassName;
	
	
	private Logger logger = LoggerFactory.getLogger(ClassScannerHandler.class);
	
	public ClassScannerHandler() {
		fullClassName = new ArrayList<String>();
	}
	
	/**
	 * 根据包名扫描所有子类
	 * @param packages
	 * @return
	 */
	public List<String> scanner(String packages){
		if(packages == null) {
			logger.debug("包名不允许为空");
			return null;
		}
		//将包名分隔符“.”换成文件路径分隔符
		String filePath = packages.replace(".", "/");
		URL resource = this.getClass().getClassLoader().getResource(filePath);
		if(resource == null) {
			logger.debug("【"+packages+"】包路径不存在");
			return null;
		}
		File packageFile = new File(resource.getFile());
		//File 
		if(packageFile.isDirectory()) {
			//将文件夹下的所有类的全类名放入List容器中
			findClass(packageFile,packages);
		}
		return fullClassName;
	}
	
	
	/**
	 * 将文件夹下面的所有类放入容器中
	 * @param packageFile 目录文件对象
	 * @param prefix  包名前缀
	 */
	private void findClass(File packageFile,String prefix) {
		File[] listFiles = packageFile.listFiles();
		for(int i = 0 ; i < listFiles.length ; i++) {
			File file = listFiles[i];
			if(file.isFile()) {
				String fn = file.getName();
				if(fn.length()-fn.lastIndexOf(".class")-6 == 0) {
					fullClassName.add(prefix+"."+fn.substring(0,fn.length()-6));
				}
			}else if(file.isDirectory()) {
				this.findClass(file, prefix+"."+file.getName());
			}
		}
	}


}
