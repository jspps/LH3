package com.learnhall.content;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import redis.clients.jedis.Jedis;

import com.bowlong.sql.CacheModel;
import com.bowlong.sql.freemarker.JedisTookits;
import com.learnhall.db.entity.AdqdepartmentEntity;
import com.learnhall.timer.TimerNight;

@SuppressWarnings("all")
public class AppContext extends JSONContext {
	static Log log = LogFactory.getLog(AppContext.class);

	static boolean isLoadAll = false;

	public static void loadAll() throws Exception {
		// //////////////////////////////////////////
		// Apps Init begin
		if (isLoadAll)
			return;
		isLoadAll = true;

		dataSource();

		AdqdepartmentEntity.cacheModel = CacheModel.FULL_CACHE;
		AdqdepartmentEntity.loadAll();

		TimerNight.startTimer();

		// Apps Init end
		// ////////////////////////
	}

	// //////////////////// //////////////////// ////////////////////
	// 系统数据库
	public static DataSource dsData() {
		return dataSource().get("ds");
	}

	public static DataSource dsLog() {
		return dataSource().get("log");
	}

	public static DataSource dsDesign() {
		return dataSourceDesign().get("design");
	}

	public static DataSource dsCfg() {
		return dataSourceDesign().get("cfg");
	}

	static public Connection getConnection() {
		try {
			return dsData().getConnection();
		} catch (SQLException e) {
			log.error(e2s(e));
		}
		return null;
	}

	static public Connection getConnectionCfg() {
		try {
			return dsCfg().getConnection();
		} catch (SQLException e) {
			log.error(e2s(e));
		}
		return null;
	}

	public static String getName() {
		return getString(APP(), "NAME");
	}

	public static double getVer() {
		return getDouble(APP(), "VER");
	}

	/** 该游戏的唯一标识 */
	public static String getGameID() {
		return getString(APP(), "GAME_ID");
	}

	public static int getGamePortTcp() {
		return getInt(APP(), "TCP_PORT");
	}

	public static int getGamePortWeb() {
		return getInt(APP(), "WEB_PORT");
	}

	public static int getGamePortShutdown() {
		return getInt(APP(), "SHUTDOWNPORT");
	}

	public static String getNGINX() {
		return getString(APP(), "NGINX");
	}

	public static String getGateHost() {
		return getString(APP(), "GATE_HOST");
	}

	public static int getGatePortWeb() {
		return getInt(APP(), "GATE_PORT_WEB");
	}

	public static int getGatePortTcp() {
		return getInt(APP(), "GATE_PORT_TCP");
	}

	public static int getGatePortShutdown() {
		return getInt(APP(), "SHUTDOWNPORT_GATE");
	}

	public static String getGMGTHost() {
		return getString(APP(), "GMGT_HOST");
	}

	public static int getGMGTPortTcp() {
		return getInt(APP(), "GMGT_PORT_TCP");
	}

	public static int getJedisPort() {
		return getInt(RedisMap(), "port");
	}

	static private List GAME_WEB_HOSTS() {
		return getList(APP(), "GAME_WEB_HOSTS");
	}

	public static String getGameWebHostBySvcid(int svcid) {
		List list = GAME_WEB_HOSTS();
		if (list == null || list.isEmpty())
			return "";
		try {
			String host = "";
			for (Object obj : list) {
				Map map = (Map) obj;
				host = getString(map, "" + svcid);
				if (host != null && !"".equals(host.trim()))
					break;

			}
			return host;
		} catch (Exception e) {
			return "";
		}
	}

	public static String getPbUrlSave() {
		return getString(APP(), "pbUrlSave");
	}

	public static String getPbUrlGet() {
		return getString(APP(), "pbUrlGet");
	}

	public static Jedis getJedis() {
		return JedisTookits.getJedis();
	}

	public static void returnJedis(Jedis resource) {
		JedisTookits.returnJedis(resource);
	}
}