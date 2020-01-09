package cn.xdd.utils.vcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *@author: xchb
 *@date: 2019年12月29日下午7:37:38
 *@description: 随机字符串图片验证码生成工具类
 */
public class RandomStringImageVC {
	
	private Integer width = 200;  //生成图片的宽
	private Integer height = 80; //生成图片的高
	private String imageSuffix = "jpg";  //生成图片的后缀名
	private String imageContent = "123456789abcdefghijklmnopqr"
			+ "stuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ"; //图片上可以显示的内容
	private Integer lineCount = 4; //生成的干扰线条数，默认是4条
	private String content = ""; //图片上显示的内容
	
	private Random random = new Random(); //随机对象
	private final Logger logger = LoggerFactory.getLogger(RandomStringImageVC.class);
	

	public RandomStringImageVC() {
		super();
	}
	
	public RandomStringImageVC(Integer width, Integer height) {
		super();
		this.width = width;
		this.height = height;
	}


	/**
	 * 生成图片，并返回图片内容
	 * @param outputStream
	 * @return
	 * @throws IOException
	 */
	public String toImage(OutputStream outputStream) throws IOException {
		//1、创建画布
		BufferedImage bufferImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//2、获取画笔并绘制白色背景
		Graphics2D graphics2d = (Graphics2D) bufferImage.getGraphics();
		graphics2d.setColor(Color.WHITE);
		graphics2d.fillRect(0, 0, width, height);
		//3、生成字符内容
		randomContent(4);
		//4、将字符绘制在图片上
		for(int i=0;i<content.length();i++) {
			graphics2d.setColor(randomColor());
			graphics2d.setFont(new Font("微软雅黑", Font.ITALIC,height/2+random.nextInt(5) ));
			graphics2d.drawString(String.valueOf(content.charAt(i)), 
					i*width/content.length()+5, height/2+random.nextInt(10));
		}
		//5、生成干扰线
		for(int i=0;i<lineCount;i++) {
			graphics2d.setColor(randomColor());
			graphics2d.setStroke(new BasicStroke(random.nextInt(10)));
			graphics2d.drawLine(random.nextInt(width/2), random.nextInt(height), 
					random.nextInt(width),random.nextInt(height));
		}
		
		//6、输出并关闭图片
		ImageIO.write(bufferImage, imageSuffix, outputStream);
		outputStream.flush();
		outputStream.close();
	
		return content;
	}
	

	/**
	 * 随机颜色（RGB)
	 * @return
	 */
	private Color randomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	/**
	 * 生成字符，最少四个
	 * @param count
	 * @return
	 */
	private void randomContent(int count) {
		if(content !=null && !content.trim().isEmpty() ) {
			return;
		}
		if(count<4) {
			count = 4;
		}
		for(int i=0;i<count;i++) {
			content += imageContent.charAt(random.nextInt(imageContent.length()));
		}
	}

	
	//各种get、set
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

	
	public Integer getWidth() {
		return width;
	}


	public void setWidth(Integer width) {
		if(width <= 0) return;
		this.width = width;
	}


	public Integer getHeight() {
		return height;
	}


	public void setHeight(Integer height) {
		if(height <= 0) return;
		this.height = height;
	}


	public String getImageSuffix() {
		return imageSuffix;
	}


	public void setImageSuffix(String imageSuffix) {
		this.imageSuffix = imageSuffix;
	}


	public Integer getLineCount() {
		return lineCount;
	}


	public void setLineCount(Integer lineCount) {
		if(lineCount < 0) return;
		this.lineCount = lineCount;
	}


	public static void main(String[] args) throws FileNotFoundException, IOException {
		for(int i=0;i<100;i++) {
			RandomStringImageVC ic = new RandomStringImageVC(120,80);
			ic.toImage(new FileOutputStream(new File("F:\\codes\\"+i+".jpg")));
			System.out.println("生成内容："+ic.getContent());
		}
	}
	
}
