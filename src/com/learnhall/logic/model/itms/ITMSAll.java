package com.learnhall.logic.model.itms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.NewCpWrList;
import com.learnhall.db.bean.Optquestion;

public class ITMSAll implements Serializable {

	private static final long serialVersionUID = 1L;

	// 所有题
	// NewMap<Integer, Optquestion> mapAlls = new NewMap<Integer,
	// Optquestion>();

	// 单选题
	NewCpWrList<Optquestion> listRadio = new NewCpWrList<Optquestion>();
	// 多选题
	NewCpWrList<Optquestion> listChBox = new NewCpWrList<Optquestion>();
	// 判断题
	NewCpWrList<Optquestion> listJudge = new NewCpWrList<Optquestion>();
	// 填空题
	NewCpWrList<Optquestion> listFill = new NewCpWrList<Optquestion>();
	// 简答题
	NewCpWrList<Optquestion> listJD = new NewCpWrList<Optquestion>();

	// ============= 知识点
	// 单选题
	NewCpWrList<Optquestion> listRadio4K = new NewCpWrList<Optquestion>();
	// 多选题
	NewCpWrList<Optquestion> listChBox4K = new NewCpWrList<Optquestion>();
	// 判断题
	NewCpWrList<Optquestion> listJudge4K = new NewCpWrList<Optquestion>();
	// 填空题
	NewCpWrList<Optquestion> listFill4K = new NewCpWrList<Optquestion>();
	// 简答题
	NewCpWrList<Optquestion> listJD4K = new NewCpWrList<Optquestion>();

	public void init() {
		// mapAlls.clear();
		listChBox.clear();
		listFill.clear();
		listJD.clear();
		listJudge.clear();
		listRadio.clear();

		init4Knowledge(null);
	}

	public void init4Knowledge(String strKnowlegde) {
		initChBox4Knowlege(strKnowlegde);
		initFill4Knowlege(strKnowlegde);
		initJianDa4Knowlege(strKnowlegde);
		initJudge4Knowlege(strKnowlegde);
		initRadio4Knowlege(strKnowlegde);
	}

	private void initChBox4Knowlege(String strKnowlegde) {
		listChBox4K.clear();
		if (!StrEx.isEmptyTrim(strKnowlegde)) {
			for (Optquestion item : listChBox) {
				if (item.getContent().indexOf(strKnowlegde) != -1) {
					listChBox4K.add(item);
				}
			}
		}
	}
	
	private void initFill4Knowlege(String strKnowlegde) {
		listFill4K.clear();
		if (!StrEx.isEmptyTrim(strKnowlegde)) {
			for (Optquestion item : listFill) {
				if (item.getContent().indexOf(strKnowlegde) != -1) {
					listFill4K.add(item);
				}
			}
		}
	}
	
	private void initJianDa4Knowlege(String strKnowlegde) {
		listJD4K.clear();
		if (!StrEx.isEmptyTrim(strKnowlegde)) {
			for (Optquestion item : listJD) {
				if (item.getContent().indexOf(strKnowlegde) != -1) {
					listJD4K.add(item);
				}
			}
		}
	}
	
	private void initJudge4Knowlege(String strKnowlegde) {
		listJudge4K.clear();
		if (!StrEx.isEmptyTrim(strKnowlegde)) {
			for (Optquestion item : listJudge) {
				if (item.getContent().indexOf(strKnowlegde) != -1) {
					listJudge4K.add(item);
				}
			}
		}
	}
	
	private void initRadio4Knowlege(String strKnowlegde) {
		listRadio4K.clear();
		if (!StrEx.isEmptyTrim(strKnowlegde)) {
			for (Optquestion item : listRadio) {
				if (item.getContent().indexOf(strKnowlegde) != -1) {
					listRadio4K.add(item);
				}
			}
		}
	}

	public void initQuesion(Optquestion item) {
		int type = item.getType();
		switch (type) {
		case 1:
			listRadio.add(item);
			break;
		case 2:
			listChBox.add(item);
			break;
		case 3:
			listJudge.add(item);
			break;
		case 4:
			listFill.add(item);
			break;
		case 5:
			listJD.add(item);
			break;
		default:
			break;
		}
	}

	public List<Map<String, Object>> getList4ExamCatalog() {
		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		for (int serial = 1; serial < 7; serial++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String bigtypes = ITMSTool.getExamCatalogIntByBigtypes(serial);
			map.put("serial", serial);
			map.put("bigtypes", bigtypes);
			switch (serial) {
			case 1:
				map.put("val", listRadio.size());
				map.put("inpId", "numRadio2Exam");
				break;
			case 2:
				map.put("val", listChBox.size());
				map.put("inpId", "numChbox2Exam");
				break;
			case 3:
				map.put("val", listJudge.size());
				map.put("inpId", "numJudge2Exam");
				break;
			case 4:
				map.put("val", listFill.size());
				map.put("inpId", "numFill2Exam");
				break;
			case 5:
				map.put("val", listJD.size());
				map.put("inpId", "numJDa2Exam");
				break;
			case 6:
				map.put("val", 0);
				map.put("inpId", "numLunsu2Exam");
				break;
			default:
				break;
			}
			res.add(map);
		}
		return res;
	}

	public List<Optquestion> getList2Exam(int numR, int numC, int numJuagde,
			int numFill, int numJDan, int numLunsu) {
		List<Optquestion> res = new ArrayList<Optquestion>();
		if (numR > 0) {
			res.addAll(ListEx.subRndListT(listRadio, numR));
		}
		if (numC > 0) {
			res.addAll(ListEx.subRndListT(listChBox, numC));
		}
		if (numJuagde > 0) {
			res.addAll(ListEx.subRndListT(listJudge, numJuagde));
		}
		if (numFill > 0) {
			res.addAll(ListEx.subRndListT(listFill, numFill));
		}
		if (numJDan > 0) {
			res.addAll(ListEx.subRndListT(listJD, numJDan));
		}
		if (numLunsu > 0) {
			res.addAll(ListEx.subRndListT(listFill, numLunsu));
		}
		return res;
	}

	// === 知识要点
	public List<Map<String, Object>> getList4Knowledge(String strKnowlegde) {

		init4Knowledge(strKnowlegde);

		List<Map<String, Object>> res = new ArrayList<Map<String, Object>>();
		for (int serial = 1; serial < 7; serial++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String bigtypes = ITMSTool.getExamCatalogIntByBigtypes(serial);
			map.put("serial", serial);
			map.put("bigtypes", bigtypes);
			switch (serial) {
			case 1:
				map.put("val", listRadio4K.size());
				map.put("inpId", "numRadio2Exam");
				break;
			case 2:
				map.put("val", listChBox4K.size());
				map.put("inpId", "numChbox2Exam");
				break;
			case 3:
				map.put("val", listJudge4K.size());
				map.put("inpId", "numJudge2Exam");
				break;
			case 4:
				map.put("val", listFill4K.size());
				map.put("inpId", "numFill2Exam");
				break;
			case 5:
				map.put("val", listJD4K.size());
				map.put("inpId", "numJDa2Exam");
				break;
			case 6:
				map.put("val", 0);
				map.put("inpId", "numLunsu2Exam");
				break;
			default:
				break;
			}
			res.add(map);
		}
		return res;
	}

	public List<Optquestion> getList2Exam4Knowledge(int numR, int numC,
			int numJuagde, int numFill, int numJDan, int numLunsu) {
		List<Optquestion> res = new ArrayList<Optquestion>();
		if (numR > 0) {
			res.addAll(ListEx.subRndListT(listRadio4K, numR));
		}
		if (numC > 0) {
			res.addAll(ListEx.subRndListT(listChBox4K, numC));
		}
		if (numJuagde > 0) {
			res.addAll(ListEx.subRndListT(listJudge4K, numJuagde));
		}
		if (numFill > 0) {
			res.addAll(ListEx.subRndListT(listFill4K, numFill));
		}
		if (numJDan > 0) {
			res.addAll(ListEx.subRndListT(listJD4K, numJDan));
		}
		if (numLunsu > 0) {
			res.addAll(ListEx.subRndListT(listFill4K, numLunsu));
		}
		return res;
	}
}
