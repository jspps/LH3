package com.learnhall.logic.model.itms;

public class ITMSTool {
	static public String getExamCatalogIntByBigtypes(final int type) {
		switch (type) {
		case 1:
			return "单选题";
		case 2:
			return "多选题";
		case 3:
			return "判断题";
		case 4:
			return "填空题";
		case 5:
			return "简答题";
		case 6:
			return "论述题";
		case 7:
			return "案例分析题";
		default:
			return "";
		}
	}
}
