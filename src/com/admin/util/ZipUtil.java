package com.admin.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * 
 * 
 * @author hxw
 * 
 */
public class ZipUtil {

	public static void main(String[] args) throws Exception {

		String zippath = "D:/site-1.8.10";// /解压到的目标文件路径
		String zipDir = "D:/site-1.8.10.zip";// 要解压的压缩文件的存放路径

		readZip(zippath, zipDir);
		Dom4jUtil.createXML(zippath, "d:/site-1.8.10.xml", "10001");
	}

	/**
	 * 
	 * 解压缩功能. 将zippath目录文件解压到unzipPath目录下. @throws Exception
	 */
	public static void readZip(String zippath, String unzipPath)
			throws Exception {

		ZipFile zfile = new ZipFile(unzipPath);// 生成一个zip文件对象

		// System.out.println(zfile.getName());// 获取要解压的zip的文件名全路径

		@SuppressWarnings("rawtypes")
		Enumeration zList = zfile.entries();// 返回枚举对象

		ZipEntry ze = null;// 用于表示 ZIP 文件条目

		byte[] buf = new byte[1024];// 声明字节数组
		/**
		 * 循环获取zip文件中的每一个文件
		 */
		while (zList.hasMoreElements()) {
			// 从ZipFile中得到一个ZipEntry
			ze = (ZipEntry) zList.nextElement();
			if (ze.isDirectory())// 如果为目录条目，则返回 true，执行下列语句
			{
				continue;
			}
			int begin = zfile.getName().lastIndexOf("\\") + 1;
			int end = zfile.getName().lastIndexOf(".");
			String zipRealName = zfile.getName().substring(begin, end);
			// System.out.println("解压缩开始Extracting:" + ze.getName() + "\t"
			// + ze.getSize() + "\t" + ze.getCompressedSize());
			// 以ZipEntry为参数得到一个InputStream，并写到OutputStream中，并加上缓冲
			OutputStream os = new BufferedOutputStream(
					new FileOutputStream(getRealFileName(zippath + "\\"
							+ zipRealName, ze.getName())));
			InputStream is = new BufferedInputStream(zfile.getInputStream(ze));

			// String fileName = getRealFileName(zippath,
			// ze.getName()).getName();
			// System.out.println("解压出的文件名称：" + fileName);
			int readLen = 0;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				os.write(buf, 0, readLen);
			}
			is.close();
			os.close();
			// System.out.println("解压缩结束Extracted: "+ze.getName());
		}
		zfile.close();
	}

	/**
	 * 给定根目录，返回一个相对路径所对应的实际文件名.
	 * 
	 * @param zippath
	 *            指定根目录
	 * @param absFileName
	 *            相对路径名，来自于ZipEntry中的name
	 * @return java.io.File 实际的文件
	 */
	private static File getRealFileName(String zippath, String absFileName) {

		String[] dirs = absFileName.split("/", absFileName.length());

		File ret = new File(zippath);// 创建文件对象

		if (dirs.length > 1) {
			for (int i = 0; i < dirs.length - 1; i++) {
				ret = new File(ret, dirs[i]);

			}
		}

		if (!ret.exists()) {// 检测文件是否存在
			ret.mkdirs();// 创建此抽象路径名指定的目录
		}
		ret = new File(ret, dirs[dirs.length - 1]);// 根据 ret 抽象路径名和 child
		// 路径名字符串创建一个新 File 实例

		return ret;
	}

}