package com.learnhall.logic.model.itms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bowlong.util.ListEx;
import com.bowlong.util.NewCpWrList;
import com.learnhall.db.bean.Exam;
import com.learnhall.db.bean.Itms4auto;
import com.learnhall.db.bean.Optquestion;
import com.learnhall.db.entity.ExamEntity;
import com.learnhall.db.entity.Itms4autoEntity;

/**
 * 智能组题就是把该科目所有的题按真题的题型和题量随机组合成一套题
 * 
 * @author Canyon
 * 
 *         createtime:2015-5-10 下午9:50:02
 */
public class ITMS4Auto implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public void init() {
		listChBox.clear();
		listFill.clear();
		listJD.clear();
		listJudge.clear();
		listRadio.clear();
	}

	public void initQuesion(Optquestion item) {
		int examid = item.getExamid();
		Exam exam = ExamEntity.getByKey(examid);
		if (exam == null || exam.getExamtypeid() != 2) {
			return;
		}
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

	public Itms4auto getAutoEntity(int kindid) {
		Itms4auto en = Itms4autoEntity.getByKindid(kindid);
		if (en == null) {
			int num4chbox = 15;
			int num4fill = 10;
			int num4jd = 10;
			int num4judge = 10;
			int num4luns = 10;
			int num4radio = 25;
			en = Itms4auto.newItms4auto(0, kindid, num4radio, num4chbox,
					num4judge, num4fill, num4jd, num4luns);
		}
		return en;
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
}
