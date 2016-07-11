package com.learnhall.timer;

import java.util.concurrent.ScheduledExecutorService;

import org.apache.commons.logging.Log;

import com.bowlong.util.ExceptionEx;
import com.learnhall.content.Svc;
import com.learnhall.db.entity.Rnk4profitEntity;

/**
 * 每天定时执行
 * 
 * @author Administrator
 * 
 */
public class TimerNight extends Svc implements Runnable {

	static Log log = getLog(TimerNight.class);

	static public boolean isClearLog = true;

	static public boolean isStart = false;

	static ScheduledExecutorService SES = newScheduledThreadPool(
			"TimerNight.Gbo", 2);

	static TimerNight _self;

	private TimerNight() {
	}

	public static TimerNight getInstance() {
		if (_self == null) {
			_self = new TimerNight();
		}
		return _self;
	}

	static public void startTimer() {
		if (isStart) {
			return;
		}
		isStart = true;

		TimerNight t = getInstance();
		int hour = 3;
		int minute = 20;
		int sec = 0;
		scheduledEveryDay(SES, t, hour, minute, sec);
	}

	@Override
	public void run() {
		try {
			exceFun();
		} catch (Exception e) {
			log.info(ExceptionEx.e2s(e));
		}
	}

	// ============ 执行函数 ========

	private void exceFun() throws Exception {
		exce(isClearLog, 0);
	}

	public void exce(boolean isClear, int type) throws Exception {

		isClearLog = isClear;

		if (isClearLog) {
			Rnk4profitEntity.clearLog4Call();
		}
		type = type > 1 ? 1 : 0;
		Rnk4profitEntity.exceProcess(type);
	}
}
