package com.learnhall.logic.controll.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.admin.util.DateUtil;
import com.admin.util.StringUtil;
import com.learnhall.logic.Utls;
import com.learnhall.logic.controll.onesigle.SigleSetController;
import com.learnhall.logic.json.Json4Upload;

/**
 * 上传文件类型
 * 
 * @author hxw
 * 
 */
@SuppressWarnings({ "rawtypes", "unused" })
@Controller
@RequestMapping("/doClient")
public class UploadFileController {

	/*** 图片上传 **/
	@RequestMapping("/uploadImg")
	public void uploadImg(HttpServletRequest request,
			MultipartHttpServletRequest multiRequest,
			HttpServletResponse response) {
		response.setContentType("text/plain;charset=utf-8");
		Map result = new HashMap();
		try {
			List<MultipartFile> files = multiRequest.getFiles("uploadImg");
			if (files.size() == 1) {
				MultipartFile file = files.get(0);
				result = upload(file, "upload.file", request, new String[] {
						".jpg", ".jpeg", ".png", ".gif" });

				// 添加了一个保存头像的方法
				SigleSetController.resetImg4BaseInfo(request,
						(String) result.get("msg"));
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "上传文件解析出错!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "文件上传出错，请重试!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 音频上传 **/
	@RequestMapping("/uploadMp3")
	public void uploadMp3(HttpServletRequest request,
			MultipartHttpServletRequest multiRequest,
			HttpServletResponse response) {
		response.setContentType("text/plain;charset=utf-8");

		Map result = new HashMap();
		try {
			List<MultipartFile> files = multiRequest.getFiles("uploadMp3");
			if (files.size() == 1) {
				MultipartFile file = files.get(0);
				result = upload(file, "upload.file", request,
						new String[] { ".mp3" });
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "上传文件解析出错!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "文件上传出错，请重试!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 视频上传 **/
	@RequestMapping("/uploadMp4")
	public void uploadMp4(HttpServletRequest request,
			MultipartHttpServletRequest multiRequest,
			HttpServletResponse response) {
		response.setContentType("text/plain;charset=utf-8");
		Map result = new HashMap();
		try {
			List<MultipartFile> files = multiRequest.getFiles("uploadMp4");
			if (files.size() == 1) {
				MultipartFile file = files.get(0);
				result = upload(file, "upload.file", request,
						new String[] { ".mp4" });
			} else {
				result = Utls.tipMap(result, Utls.Status_Erro, "上传文件解析出错!");
			}

		} catch (Exception e) {
			result = Utls.tipMap(result, Utls.Status_Erro, "文件上传出错，请重试!");
		}
		Utls.writeAndClose(response, result);
	}

	/*** 文本框上传 **/
	@SuppressWarnings("unchecked")
	@RequestMapping("/editorUpload")
	public void editorUpload(MultipartHttpServletRequest multiRequest,
			HttpServletResponse response, HttpServletRequest request) {
		response.setContentType("text/plain;charset=utf-8");

		Map result = new HashMap();
		try {
			List<MultipartFile> files = multiRequest.getFiles("imgFile");
			if (files.size() == 1) {
				MultipartFile file = files.get(0);
				result = upload(file, "upload.file", request, new String[] {
						".jpg", ".jpeg", ".png", ".gif" });
				if (result != null) {
					if (result.get("status") != null
							&& Integer
									.parseInt(result.get("status").toString()) == 1) {
						result.put("error", 0);
						result.put("url", result.get("msg").toString());
					} else {
						result.put("error", 1);
						result.put("message", result.get("msg").toString());
					}
				}

			} else {
				result.put("error", 1);
				result.put("message", "上传文件解析出错!");
			}

		} catch (Exception e) {
			result.put("error", 1);
			result.put("message", "文件上传出错，请重试!");
		}
		Utls.writeAndClose(response, result);
	}

	private Map upload(MultipartFile file, String pz,
			HttpServletRequest request, String[] array) {
		Map result = new HashMap();
		try {
			if (file == null) {
				return Utls.tipMap(result, Utls.Status_Erro, "上传失败：文件为空!");
			}
			if (file.getSize() > 10000000) {
				return Utls.tipMap(result, Utls.Status_Erro,
						"上传失败：文件大小不能超过10M!");
			}
			// 得到文件名
			String filename = file.getOriginalFilename();
			if (file.getSize() < 0) {
				return Utls.tipMap(result, Utls.Status_Erro, "上传失败：上传文件不能为空!");
			}
			try {
				boolean bool = true;
				String suffix = StringUtil.getSuffix(filename);
				for (int i = 0; i < array.length; i++) {
					if (suffix.endsWith(array[i])) {
						bool = false;
						break;
					}
				}
				if (bool) {
					return Utls.tipMap(result, Utls.Status_Erro,
							"上传失败：不支持的文件格式！");
				}

				Date nowDate = new Date();
				String path = Json4Upload.getSaveFolder(nowDate);
				File savefile = new File(path);
				if (!savefile.exists() && !savefile.isDirectory()) {
					savefile.mkdirs();
				}
				String name = DateUtil.fromatShortNoSignSSS(nowDate) + suffix;
				saveFileFromInputStream(file.getInputStream(), path, name);
				String imgsUrl = Json4Upload.getUrlFolder(nowDate) + name;
				// http://121.40.49.234/upload/index.jsp
				return Utls.tipMap(result, Utls.Status_Success, imgsUrl);
			} catch (IOException e) {
				return Utls.tipMap(result, Utls.Status_Erro, "文件上传出错，请重试!");
			}
		} catch (Exception ex) {
			return Utls.tipMap(result, Utls.Status_Erro, "文件上传出错，请重试!");
		}
	}

	/**
	 * 保存文件
	 * 
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public static void saveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}
}
