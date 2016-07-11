package com.admin.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.security.NoSuchAlgorithmException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.XMLWriter;

/**
 * 
 * 
 * @author hxw
 * 
 */
public class Dom4jUtil {

	public static void main(String[] args) throws NoSuchAlgorithmException,
			IOException {

		// createXML("F:/1234/ofeasy", "F:/1234/ofeasy.xml", "0.0.3.2");

		// createAddersXML("d:/adderss.xml");
		File file = new File("D:/data1/resources/aftermarket");
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				System.out.println(tempList[i].getName());
			}
			if (tempList[i].isDirectory()) {
				System.out.println(tempList[i].getName());
			}
		}
	}

	/**
	 * 根据一个文件夹下面的文件生成固定格式的XML文件
	 * 
	 * 
	 * 
	 * @param path
	 *            文件夹路径
	 * @param pathXML
	 *            生成后XML的路径
	 * @param version
	 *            XML的版本号
	 * @throws NoSuchAlgorithmException
	 */
	@SuppressWarnings("deprecation")
	public static void createXML(String path, String pathXML, String version)
			throws IOException, NoSuchAlgorithmException {
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element ofeasy = document.addElement("ofeasy");
		Element terminal = ofeasy.addElement("terminal");
		terminal.setAttributeValue("version", version);
		int length = path.length();
		getFliePath(path, terminal, length);
		File file = new File(pathXML);
		Writer fileWriter = new FileWriter(file);
		XMLWriter xmlWriter = new XMLWriter(fileWriter);
		xmlWriter.write(document);
		xmlWriter.close();

	}

	/**
	 * and element数据生成XML需要
	 * 
	 * @param path
	 *            文件的路径
	 * @param element
	 *            XML需要的元素
	 * @param pathLength
	 *            原文件夹的长度 ;true递归子目录中的文件
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 */
	private static void getFliePath(String path, Element element, int pathLength)
			throws NoSuchAlgorithmException, IOException {
		File file = new File(path);
		File[] tempList = file.listFiles();
		for (int i = 0; i < tempList.length; i++) {
			if (tempList[i].isFile()) {
				Element FileXML = element.addElement("file"); // 添加子节点
				Element pathFile = FileXML.addElement("path"); // 添加子节点
				Element size = FileXML.addElement("size"); // 添加子节点
				String pathString = tempList[i].toString();
				pathString = pathString.substring(pathLength,
						pathString.length());
				pathFile.setText(pathString); // 添加Text值；例：<a>abc</a>
				Element md5 = FileXML.addElement("Md5");
				md5.setText(FileMd5Util.getFileMD5(tempList[i]));
				size.setText(tempList[i].length() + "");
				// System.err.println(pathString);
			}
			if (tempList[i].isDirectory()) {
				getFliePath(tempList[i].toString(), element, pathLength);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static void createAddersXML(String pathXML) throws IOException,
			NoSuchAlgorithmException {
		Document document = DocumentHelper.createDocument(); // 创建文档
		Element ofeasy = document.addElement("ofeasy");
		Element province = ofeasy.addElement("province");
		province.setAttributeValue("id", "11"); // 添加属性；例：<a
		province.setAttributeValue("name", "北京"); // 添加属性；例：<a
		Element city = province.addElement("city");
		city.setAttributeValue("id", "123"); // 添加属性；例：<a
		city.setAttributeValue("name", "北京市"); // 添加
		Element county = city.addElement("county");
		county.setAttributeValue("id", "1223"); // 添加属性；例：<a
		county.setAttributeValue("name", "海定区"); // 添加

		File file = new File(pathXML);
		Writer fileWriter = new FileWriter(file);
		XMLWriter xmlWriter = new XMLWriter(fileWriter);
		xmlWriter.write(document);
		xmlWriter.close();

	}

}