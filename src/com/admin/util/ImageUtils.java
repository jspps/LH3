package com.admin.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * 图片工具类， 图片水印，文字水印，缩放，补白等
 * 
 * @author Carl He
 */
public final class ImageUtils {
	/** 图片格式：JPG */
	private static final String PICTRUE_FORMATE_JPG = "jpg";
	// JPG、JPEG、GIF、PNG
	public static final String TYPE_JPG = "JPG";
	public static final String TYPE_JPEG = "JPEG";
	public static final String TYPE_GIF = "GIF";
	public static final String TYPE_PNG = "PNG";

	private ImageUtils() {
	}

	/**
	 * 添加图片水印
	 * 
	 * @param targetImg
	 *            目标图片路径，如：C://myPictrue//1.jpg
	 * @param waterImg
	 *            水印图片路径，如：C://myPictrue//logo.png
	 * @param x
	 *            水印图片距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y
	 *            水印图片距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha
	 *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 */
	public final static void pressImage(String targetImg, String waterImg,
			String newImg, int x, int y, float alpha) {
		try {
			File file = new File(targetImg);
			File newFile = new File(newImg);
			Image image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);

			Image waterImage = ImageIO.read(new File(waterImg)); // 水印文件
			int width_1 = waterImage.getWidth(null);
			int height_1 = waterImage.getHeight(null);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			int widthDiff = width - width_1;
			int heightDiff = height - height_1;
			if (x < 0) {
				x = widthDiff / 2;
			} else if (x > widthDiff) {
				x = widthDiff;
			}
			if (y < 0) {
				y = heightDiff / 2;
			} else if (y > heightDiff) {
				y = heightDiff;
			}
			g.drawImage(waterImage, x, y, width_1, height_1, null); // 水印文件结束
			g.dispose();
			ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, newFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 添加文字水印
	 * 
	 * @param targetImg
	 *            目标图片路径，如：C://myPictrue//1.jpg
	 * @param pressText
	 *            水印文字， 如：宝丽菲尔
	 * @param fontName
	 *            字体名称， 如：宋体
	 * @param fontStyle
	 *            字体样式，如：粗体和斜体(Font.BOLD|Font.ITALIC)
	 * @param fontSize
	 *            字体大小，单位为像素
	 * @param color
	 *            字体颜色
	 * @param x
	 *            水印文字距离目标图片左侧的偏移量，如果x<0, 则在正中间
	 * @param y
	 *            水印文字距离目标图片上侧的偏移量，如果y<0, 则在正中间
	 * @param alpha
	 *            透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
	 */
	public static void pressText(String targetImg, String newImg,
			String pressText, String fontName, int fontStyle, int fontSize,
			Color color, int x, int y, float alpha) {
		try {
			File file = new File(targetImg);
			File newFile = new File(newImg);
			Image image = ImageIO.read(file);
			int width = image.getWidth(null);
			int height = image.getHeight(null);
			BufferedImage bufferedImage = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = bufferedImage.createGraphics();
			g.drawImage(image, 0, 0, width, height, null);
			g.setFont(new Font(fontName, fontStyle, fontSize));
			g.setColor(color);
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
					alpha));

			int width_1 = fontSize * getLength(pressText);
			int height_1 = fontSize;
			int widthDiff = width - width_1;
			int heightDiff = height - height_1;
			if (x < 0) {
				x = widthDiff / 2;
			} else if (x > widthDiff) {
				x = widthDiff;
			}
			if (y < 0) {
				y = heightDiff / 2;
			} else if (y > heightDiff) {
				y = heightDiff;
			}

			g.drawString(pressText, x, y + height_1);
			g.dispose();
			ImageIO.write(bufferedImage, PICTRUE_FORMATE_JPG, newFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取字符长度，一个汉字作为 1 个字符, 一个英文字母作为 0.5 个字符
	 * 
	 * @param text
	 * @return 字符长度，如：text="中国",返回 2；text="test",返回 2；text="中国ABC",返回 4.
	 */
	public static int getLength(String text) {
		int textLength = text.length();
		int length = textLength;
		for (int i = 0; i < textLength; i++) {
			if (String.valueOf(text.charAt(i)).getBytes().length > 1) {
				length++;
			}
		}
		return (length % 2 == 0) ? length / 2 : length / 2 + 1;
	}

	/**
	 * 图片缩放
	 * 
	 * @param filePath
	 *            图片路径
	 * @param height
	 *            高度
	 * @param width
	 *            宽度
	 * @param bb
	 *            比例不对时是否需要补白
	 */
	public static void resize(String filePath, int height, int width, boolean bb) {
		try {
			double ratio = 0; // 缩放比例
			File f = new File(filePath);
			BufferedImage bi = ImageIO.read(f);
			Image itemp = bi.getScaledInstance(width, height,
					BufferedImage.SCALE_SMOOTH);
			// 计算比例
			if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
				if (bi.getHeight() > bi.getWidth()) {
					ratio = (new Integer(height)).doubleValue()
							/ bi.getHeight();
				} else {
					ratio = (new Integer(width)).doubleValue() / bi.getWidth();
				}
				AffineTransformOp op = new AffineTransformOp(
						AffineTransform.getScaleInstance(ratio, ratio), null);
				itemp = op.filter(bi, null);
			}
			if (bb) {
				BufferedImage image = new BufferedImage(width, height,
						BufferedImage.TYPE_INT_RGB);
				Graphics2D g = image.createGraphics();
				g.setColor(Color.white);
				g.fillRect(0, 0, width, height);
				if (width == itemp.getWidth(null))
					g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				else
					g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0,
							itemp.getWidth(null), itemp.getHeight(null),
							Color.white, null);
				g.dispose();
				itemp = image;
			}
			ImageIO.write((BufferedImage) itemp, "jpg", f);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按像素切割图片
	 * 
	 * @param srcImgPath
	 *            待切割图片路径
	 * @param destImgPath
	 *            切割后图片路径
	 * @param destImgW
	 *            所需宽度
	 * @param destImgH
	 *            所需高度
	 */
	public static void createThumb(String srcImgPath, String destImgPath,
			int destImgW, int destImgH) {

		// 原图片等比例缩小或放大之后的图片
		int narrowImgW;
		int narrowImgH;
		// 原图片大小
		int srcImgW;
		int srcImgH;
		try {
			BufferedImage bi = ImageIO.read(new File(srcImgPath));
			srcImgW = bi.getWidth();
			srcImgH = bi.getHeight();
			// 转换图片尺寸与目标尺寸比较 ， 如果转换图片较小，说明转换图片相对于目标图片来说高较小，需要以高为基准进行缩放。
			if ((float) srcImgW / srcImgH > (float) destImgW / destImgH) {
				narrowImgW = (int) (((float) destImgH / (float) srcImgH) * srcImgW);
				narrowImgH = destImgH;
				// 按照原图以高为基准等比例缩放、或放大。这一步高为所需图片的高度，宽度肯定会比目标宽度宽。
				int cutNarrowImgSize = (narrowImgW - destImgW) / 2;
				BufferedImage narrowImg = new BufferedImage(narrowImgW,
						narrowImgH, BufferedImage.TYPE_INT_RGB);
				narrowImg.getGraphics().drawImage(
						bi.getScaledInstance(narrowImgW, narrowImgH,
								Image.SCALE_SMOOTH), 0, 0, null);
				// 等比例缩放完成后宽度与目标尺寸宽度相比较 ， 将多余宽的部分分为两份 ，左边删除一部分
				Image image = narrowImg.getScaledInstance(narrowImgW,
						narrowImgH, Image.SCALE_DEFAULT);
				CropImageFilter cropFilter = new CropImageFilter(
						cutNarrowImgSize, 0, narrowImgW - cutNarrowImgSize,
						narrowImgH);
				Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage cutLiftNarrowImg = new BufferedImage(narrowImgW
						- cutNarrowImgSize, narrowImgH,
						BufferedImage.TYPE_INT_RGB);
				cutLiftNarrowImg.getGraphics().drawImage(img, 0, 0, null);
				// 右边删除一部分
				image = cutLiftNarrowImg.getScaledInstance(narrowImgW
						- cutNarrowImgSize, narrowImgH, Image.SCALE_DEFAULT);
				cropFilter = new CropImageFilter(0, 0, narrowImgW
						- cutNarrowImgSize * 2, narrowImgH);
				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));
				BufferedImage cutRightNarrowImg = new BufferedImage(narrowImgW
						- cutNarrowImgSize * 2, narrowImgH,
						BufferedImage.TYPE_INT_RGB);
				Graphics g = cutRightNarrowImg.getGraphics();
				g.drawImage(img, 0, 0, null); // 绘制截取后的图
				g.dispose();
				// 输出为文件 最终为所需要的格式
				ImageIO.write(cutRightNarrowImg, "JPEG", new File(destImgPath));
			} else { // 以宽度为基准
				narrowImgW = destImgW;
				narrowImgH = (int) (((float) destImgW / (float) srcImgW) * srcImgH);
				int cutNarrowImgSize = (narrowImgH - destImgH) / 2;

				BufferedImage narrowImg = new BufferedImage(narrowImgW,
						narrowImgH, BufferedImage.TYPE_INT_RGB);
				narrowImg.getGraphics().drawImage(
						bi.getScaledInstance(narrowImgW, narrowImgH,
								Image.SCALE_SMOOTH), 0, 0, null);
				Image image = narrowImg.getScaledInstance(narrowImgW,
						narrowImgH, Image.SCALE_DEFAULT);

				CropImageFilter cropFilter = new CropImageFilter(0,
						cutNarrowImgSize, narrowImgW, narrowImgH
								- cutNarrowImgSize);

				Image img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));

				BufferedImage cutTopNarrowImg = new BufferedImage(narrowImgW,
						narrowImgH - cutNarrowImgSize,
						BufferedImage.TYPE_INT_RGB);

				cutTopNarrowImg.getGraphics().drawImage(img, 0, 0, null);
				image = cutTopNarrowImg.getScaledInstance(narrowImgW,
						narrowImgH - cutNarrowImgSize, Image.SCALE_DEFAULT);

				cropFilter = new CropImageFilter(0, 0, narrowImgW, narrowImgH
						- cutNarrowImgSize * 2);

				img = Toolkit.getDefaultToolkit().createImage(
						new FilteredImageSource(image.getSource(), cropFilter));

				BufferedImage cutBottomNarrowImg = new BufferedImage(
						narrowImgW, narrowImgH - cutNarrowImgSize * 2,
						BufferedImage.TYPE_INT_RGB);

				Graphics g = cutBottomNarrowImg.getGraphics();

				g.drawImage(img, 0, 0, null);

				g.dispose();

				ImageIO.write(cutBottomNarrowImg, "JPEG", new File(destImgPath));

			}

		} catch (IOException e) {

			// e.printStackTrace();

		}
	}

	/**
	 * 切割图片
	 * 
	 * @param saveFileName
	 * @param locPath
	 *            void
	 */
	public static void cuttingImg(String saveFileName, String locPath,
			String[] sm) {
		if (saveFileName!=null && !"".endsWith(saveFileName)) {
			String nameString = saveFileName.split("[.]")[1];
			// JPG、JPEG、GIF、PNG
			if (nameString.toUpperCase().equals(TYPE_JPG)
					|| nameString.toUpperCase().equals(TYPE_JPEG)
					|| nameString.toUpperCase().equals(TYPE_GIF)
					|| nameString.toUpperCase().equals(TYPE_PNG)) {
				// 生成176的图片

				if (sm != null) {
					for (int i = 0; i < sm.length; i++) {
						String px = sm[i];
						if (px.indexOf("X") != -1) {
							String webPathString = locPath + "/" + px;
							File fm = new File(webPathString);
							if (!fm.exists()) {
								fm.mkdirs();
							}
							String locPathd = locPath + "/" + saveFileName;
							ImageUtils.createThumb(locPathd, webPathString
									+ "/" + saveFileName,
									Integer.parseInt(px.split("X")[0]),
									Integer.parseInt(px.split("X")[1]));
						}
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		/* pressImage("D://1.jpg", "D://2.png","D://3.jpg", 100, 100, 1f); */
		/*
		 * pressText("D://1.jpg","D://4.jpg", "宝丽菲尔", "宋体", Font.BOLD |
		 * Font.ITALIC, 20, Color.RED, 400, 400, 1f);
		 */
		/* resize("D://123.jpg", 300, 300, true); */
	}
}