package com.learnhall.content;

import java.io.File;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.bowlong.json.MyJson;
import com.bowlong.third.redis.JedisEx;

@SuppressWarnings({ "unchecked", "rawtypes" })
public abstract class JSONContext extends Svc {
	static Log log = getLog(JSONContext.class);

	static Map<String, Object> ctx = null;

	public static Map<String, Object> ctx() {
		try {
			if (ctx == null) {
				String path = JSONContext.class.getClassLoader().getResource("").getPath();
				String pathDb = path + "json/app.json";
				File CONFIG = new File(pathDb);
				ctx = MyJson.parseGeneric(CONFIG);
			}
		} catch (Exception e) {
			log.error(e2s(e));
		}
		return ctx;
	}

	static Map<String, Object> APP = null;

	public static Map<String, Object> APP() {
		if (APP == null)
			APP = (Map<String, Object>) ctx().get("APP");
		return APP;
	}

	// ============= redis ==============

	static Map<String, Object> redisMap = null;

	public static Map<String, Object> RedisMap() {
		if (redisMap == null)
			redisMap = (Map<String, Object>) ctx().get("REDIS");
		return redisMap;
	}

	static JedisPool jedisSource = null;

	public static JedisPool getJedisPool() {
		if (jedisSource == null) {
			Map<String, Object> redisConfig = RedisMap();
			int maxActive = getInt(redisConfig, "maxActive");
			int maxIdle = getInt(redisConfig, "maxIdle");
			int minIdle = getInt(redisConfig, "minIdle");
			int maxWait = getInt(redisConfig, "maxWait");
			String host = getString(redisConfig, "host");
			int timeOut = getInt(redisConfig, "timeOut");
			int port = getInt(redisConfig, "port");

			JedisPoolConfig config = JedisEx.newJedisPoolConfig(maxActive,
					maxIdle, minIdle, maxWait);

			// testOnBorrow：在borrow一个jedis实例时，是否提前进行alidate操作；如果为true，则得到的jedis实例均是可用的；
			boolean testOnBorrow = getBool(redisConfig, "testOnBorrow");

			// testOnReturn：在return给pool时，是否提前进行validate操作；
			boolean testOnReturn = getBool(redisConfig, "testOnReturn");

			// testWhileIdle：如果为true，表示有一个idle object evitor线程对idle
			// object进行扫描，如果validate失败，此object会被从pool中drop掉；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
			boolean testWhileIdle = getBool(redisConfig, "testWhileIdle");

			// timeBetweenEvictionRunsMillis：表示idle object
			// evitor两次扫描之间要sleep的毫秒数；
			int timeBetweenEvictionRunsMillis = getInt(redisConfig,
					"timeBetweenEvictionRunsMillis");

			// numTestsPerEvictionRun：表示idle object evitor每次扫描的最多的对象数；
			int numTestsPerEvictionRun = getInt(redisConfig,
					"numTestsPerEvictionRun");

			// minEvictableIdleTimeMillis：表示一个对象至少停留在idle状态的最短时间，然后才能被idle
			// object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义；
			int minEvictableIdleTimeMillis = getInt(redisConfig,
					"minEvictableIdleTimeMillis");

			config.setTestOnBorrow(testOnBorrow);
			config.setTestOnReturn(testOnReturn);
			config.setTestWhileIdle(testWhileIdle);
			config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
			config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
			config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);

			jedisSource = new JedisPool(config, host, port, timeOut);
		}
		return jedisSource;
	}

	/** 关闭数据 **/
	static public void closeJedisPool() {
		if (jedisSource != null)
			jedisSource.destroy();
	}

	// ============= sql DataSource ==============
	static Map<String, Map<String, Object>> dsMap = null;

	static Map<String, Map<String, Object>> getDSMap() {
		if (dsMap == null) {
			dsMap = (Map<String, Map<String, Object>>) ctx().get("DATASOURCE");
		}
		return dsMap;
	}

	static Map<String, DataSource> dataSourceDesign = null;

	public static Map<String, DataSource> dataSourceDesign() {
		if (dataSourceDesign == null) {
			dataSourceDesign = newMap();
			Map<String, Map<String, Object>> dss = getDSMap();
			Set<Entry<String, Map<String, Object>>> entrys = dss.entrySet();
			for (Entry<String, Map<String, Object>> entry : entrys) {
				if (!(entry.getKey() instanceof String)
						|| !(entry.getValue() instanceof Map))
					continue;
				String key = entry.getKey();
				Map<String, Object> map = entry.getValue();
				if ("ds".equalsIgnoreCase(key) || "log".equalsIgnoreCase(key))
					continue;
				DruidDataSource ds = dataSource(map);
				dataSourceDesign.put(key, ds);
			}
		}
		return dataSourceDesign;
	}

	static Map<String, DataSource> dataSource = null;

	public static Map<String, DataSource> dataSource() {
		if (dataSource == null) {
			dataSource = newMap();
			Map<String, Map<String, Object>> dss = getDSMap();
			Set<Entry<String, Map<String, Object>>> entrys = dss.entrySet();
			for (Entry<String, Map<String, Object>> entry : entrys) {
				if (!(entry.getKey() instanceof String)
						|| !(entry.getValue() instanceof Map))
					continue;
				String key = entry.getKey();
				Map<String, Object> map = entry.getValue();
				if ("design".equalsIgnoreCase(key)
						|| "cfg".equalsIgnoreCase(key))
					continue;
				DruidDataSource ds = dataSource(map);
				dataSource.put(key, ds);
			}
		}
		return dataSource;
	}

	public static final DruidDataSource dataSource(Map map) {
		DruidDataSource ds = new DruidDataSource();
		String driverClassName = getString(map, "driverClassName");
		String url = getString(map, "url");
		String username = getString(map, "username");
		String password = getString(map, "password");
		int maxActive = getInt(map, "maxActive");
		int maxIdle = getInt(map, "maxIdle");
		int maxWait = getInt(map, "maxWait");
		boolean removeAbandoned = getBool(map, "removeAbandoned");
		int removeAbandonedTimeout = getInt(map, "removeAbandonedTimeout");

		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setMaxActive(maxActive);
		// ds.setMaxIdle(maxIdle);
		ds.setInitialSize(maxIdle);
		ds.setMaxWait(maxWait);
		ds.setRemoveAbandoned(removeAbandoned);
		ds.setRemoveAbandonedTimeout(removeAbandonedTimeout);
		ds.setInitialSize(30);
		try {
			ds.getConnection().getMetaData();
			// System.out.println(ds.getNumActive() + "-" + ds.getNumIdle());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}

	// ////////////////////////////////////////////////////////

}
