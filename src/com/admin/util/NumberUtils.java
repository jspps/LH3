package com.admin.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class NumberUtils {

	/** 四舍五入界定值 */
	private static double roundLimitValue = 0.95D;

	/** 范围系列变量 */
	private static double roundRangeValue = 0.45D;
	/** money format 格式化整数显示 */
	private static final String NumberFormat_INTEGER = "###,###";
	/** money format 格式化保留两位小数显示，0 = 0.00 */
	private static final String NumberFormat_TWO_SCALE = "###,##0.00";
	/** money format 格式化保留两位小数显示，0 = 0.00 */
	private static final String Number_TWO_SCALE = "0.00";

	private static final String DECIMAL_EIGHT = "00000000";

	public NumberUtils() {

	}

	/**
	 * 
	 * 提供精确的小数位四舍五入处理。
	 * 
	 * @author hxw
	 * @param v
	 *            需要四舍五入的数字
	 * 
	 * @param scale
	 *            小数点后保留几位
	 * 
	 * @return 四舍五入后的结果
	 */

	public static double round(double v, int scale) {

		if (scale < 0) {

			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");

		}

		BigDecimal b = new BigDecimal(Double.toString(v));

		BigDecimal one = new BigDecimal("1");

		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

	}

	/**
	 * 
	 * @Description: 计算后返回double值，并保留${scale} 位小数位数
	 * @author hxw
	 * @param dividend
	 *            被除数
	 * @param divisor
	 *            除数
	 * @param scale
	 *            所需小数位数
	 * @param isRound
	 *            是否四舍五入
	 * @return 格式化后
	 */
	public static double formatWithFractionDigits(double dividend,
			double divisor, int scale, boolean isRound) {

		if (divisor == 0D || dividend == 0D) {
			return 0D;
		}

		double temp = dividend / divisor;
		BigDecimal bigDecimal = new BigDecimal(temp);
		if (isRound) {
			if (temp < 1D) {
				if (scale == 1 && temp >= roundLimitValue) {
					return bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN)
							.doubleValue();
				}
				if (scale > 1) {
					for (int i = 0; i < scale - 1; i++) {
						roundRangeValue = roundRangeValue * 0.1D;
						roundLimitValue = new BigDecimal(roundLimitValue
								+ roundRangeValue).setScale(scale + 1,
								BigDecimal.ROUND_HALF_UP).doubleValue();
					}
					if (temp >= roundLimitValue) {
						return bigDecimal
								.setScale(scale, BigDecimal.ROUND_DOWN)
								.doubleValue();
					}
				}
			}
			return round(temp, scale);
		}
		return bigDecimal.setScale(scale, BigDecimal.ROUND_DOWN).doubleValue();
	}

	/**
	 * 按照###,###格式输出如233,243,216
	 * 
	 * @param doubleValue
	 * @return 格式化输出
	 */
	public static String format4Integer(long doubleValue) {
		DecimalFormat df = new DecimalFormat(NumberFormat_INTEGER);
		return df.format(doubleValue);
	}

	/**
	 * 按照0.00格式输出233,243,216,86
	 * 
	 * @param doubleValue
	 * @return 格式化输出
	 */
	public static String format4TwoScale(double doubleValue) {
		DecimalFormat df = new DecimalFormat(Number_TWO_SCALE);
		return df.format(doubleValue);
	}

	/**
	 * 按照 ###,##0.00格式输出233,243,216,86 money格式输出
	 * 
	 * @param doubleValue
	 * @return 格式化输出
	 */
	public static String format4OnlyTwoScale(double doubleValue) {
		DecimalFormat df = new DecimalFormat(NumberFormat_TWO_SCALE);
		return df.format(doubleValue);
	}

	/**
	 * 按照00000000的格式输出8位数，如数字不足位数自动补零
	 * 
	 * @param longValue
	 * @return 格式化输出
	 */

	public static String format4DecimalWithEight(long longValue) {
		DecimalFormat df = new DecimalFormat(DECIMAL_EIGHT);
		return df.format(longValue);
	}
}
