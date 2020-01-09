package cn.xdd.utils.vcode;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;

/**
 * @author: xchb
 * @date: 2019年12月30日下午4:07:00
 * @description: 随机算式图片验证码生成工具类
 */
public class RandomFormulaImageVC {

	private Integer width = 200; // 图片的宽
	private Integer height = 120; // 图片的高
	private Integer fontSize = null; // 文字大小
	private Integer value; // 算式计算的值
	private String symbols = "+-";  //算式符号
	private Integer lineCount = 4; //生成的干扰线条数，默认是4条
	private Random random = new Random(); // 随机对象

	public int toImage(OutputStream outputStream) throws IOException {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取画笔
		Graphics2D pen = (Graphics2D) image.getGraphics();
		// 填充背景色
		pen.setColor(new Color(255, 255, 255));
		pen.fillRect(0, 0, width, height);
		// 随机生成算式
		char symbol = symbols.charAt(random.nextInt(symbols.length()));
		int number1 = random.nextInt(100);
		int number2 = random.nextInt(100);
		switch (symbol) {
		case '+':
			value = number1 + number2;
			break;
		case '-':
			value = number1 - number2;
			break;
		}
		String content = "" + number1 + symbol + number2 + " = ?";
		// 绘制内容
		for (int i = 0; i < content.length(); i++) {
			pen.setColor(randomColor());
			fontSize = fontSize == null ? height / 3 + random.nextInt(5) : fontSize;
			pen.setFont(new Font("微软雅黑", Font.ITALIC, fontSize));
			pen.drawString(String.valueOf(content.charAt(i)), i * width / content.length(), height / 2);
		}

		// 5、生成干扰线
		for (int i = 0; i < lineCount; i++) {
			pen.setColor(randomColor());
			pen.setStroke(new BasicStroke(random.nextInt(10)));
			pen.drawLine(random.nextInt(width / 2), random.nextInt(height), random.nextInt(width),
					random.nextInt(height));
		}

		// 输出图片，并关闭资源
		ImageIO.write(image, "jpg", outputStream);
		outputStream.flush();
		outputStream.close();

		return value;
	}

	/**
	 * 随机颜色（RGB)
	 * 
	 * @return
	 */
	private Color randomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		for (int i = 0; i < 100; i++) {
			RandomFormulaImageVC ic = new RandomFormulaImageVC();
			int result = ic.toImage(new FileOutputStream(new File("F:\\codes\\" + i + ".jpg")));
			System.out.println("生成内容：" + result);
		}
	}
}
