package com.learnhall.logic.json;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.bowlong.io.FileRw;
import com.bowlong.json.MyJson;
import com.bowlong.lang.PStr;
import com.bowlong.util.DateEx;
import com.bowlong.util.MapEx;
import com.learnhall.content.Svc;

@SuppressWarnings("rawtypes")
public class Json4Upload {

	static Map map = null;
	static public boolean isInitMap = false;
	/*** 是否是windows保存路径 **/
	static public boolean isWindows = false;

	static public final Map getMap() {
		if (map == null)
			isInitMap = false;
		if (isInitMap) {
			return map;
		}
		isInitMap = true;
		try {
			URL url = Json4Upload.class.getResource("upload.json");
			InputStream stream = url.openStream();
			String v = FileRw.readText(stream, "UTF-8");
			map = MyJson.toMap(v);
		} catch (IOException e) {
			map = new HashMap();
		}
		return map;
	}

	/*** 取得保存的folder地址 **/
	static public final String getFolder4Save() {
		Map map = getMap();
		return MapEx.getString(map, isWindows ? "saveWindows" : "saveLinux");
	}

	/*** 取得访问的folder地址 **/
	static public final String getFolder4Url() {
		Map map = getMap();
		return MapEx.getString(map, isWindows ? "urlWindows" : "urlLinux");
	}

	static public String gmRoot = null;

	static public final String getGmRoot() {
		if (null == gmRoot) {
			gmRoot = Svc.getAppRoot().replace("bin", "webapps");
		}
		return gmRoot;
	}

	static public final void makeFolder(String path) {
		File folder = new File(path);
		if (!folder.exists()) {
			FileRw.createDire(folder);
		}
	}

	static public final String getSFolder(String diff) {
		PStr pStr = PStr.b("");
		pStr.a(getGmRoot());
		pStr.a(getFolder4Save());
		pStr.a(File.separator);
		pStr.a(diff);
		pStr.a(File.separator);
		String ret = pStr.e("");
		makeFolder(ret);
		return ret;
	}

	static public final String getSaveFolder(Date d) {
		String dStr = "";
		if (null != d) {
			dStr = DateEx.format(d, DateEx.fmt_yyyyMMdd);
		} else {
			dStr = DateEx.nowStr5();
		}
		return getSFolder(dStr);
	}

	static public final String getUrlFolder(String diff) {
		PStr pStr = PStr.b("");
		pStr.a(getFolder4Url());
		pStr.a(File.separator);
		pStr.a(diff);
		pStr.a(File.separator);
		String ret = pStr.e("");
		makeFolder(ret);
		return ret;
	}
	
	static public final String getUrlFolder(Date d) {
		String dStr = "";
		if (null != d) {
			dStr = DateEx.format(d, DateEx.fmt_yyyyMMdd);
		} else {
			dStr = DateEx.nowStr5();
		}
		return getUrlFolder(dStr);
	}
}
