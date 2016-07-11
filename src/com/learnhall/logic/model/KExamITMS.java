package com.learnhall.logic.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.bean.Optquestion;
import com.learnhall.db.bean.Recordques4exam;
import com.learnhall.db.entity.OptquestionEntity;
import com.learnhall.db.entity.Recordques4examEntity;
import com.learnhall.logic.model.itms.ITMS4Auto;
import com.learnhall.logic.model.itms.ITMS4Errors;
import com.learnhall.logic.model.itms.ITMS4News;
import com.learnhall.logic.model.itms.ITMSAll;

/**
 * 套餐结构中的特色结构ITMS
 * 
 * @author Canyon
 * 
 *         createtime:2015-5-6 下午10:11:27
 */
public class KExamITMS implements Serializable {

	// 备注:1 题型练习,2错误,3新题,4知识点,5自能组题

	private static final long serialVersionUID = 1L;
	// 套餐包含的试卷
	public PageKExam kindExams;
	// 所有
	public ITMSAll itmsAll = new ITMSAll();
	public ITMS4Errors itmsErrors = new ITMS4Errors();
	public ITMS4News itmsNews = new ITMS4News();
	public ITMS4Auto itmsAuto = new ITMS4Auto();

	public void init(PageKExam pageKExam, int customid) {
		itmsAll.init();
		itmsErrors.init();
		itmsNews.init();
		itmsAuto.init();

		if (pageKExam == null)
			return;
		if (!pageKExam.isBuyed)
			return;
		if (pageKExam.kind == null || !pageKExam.kind.getIsHasITMS())
			return;
		this.kindExams = pageKExam;

		List<Examcatalog> listECatalogs = pageKExam.getExamCatalogs4Kind();
		if (ListEx.isEmpty(listECatalogs))
			return;
		int len = listECatalogs.size();

		for (int i = 0; i < len; i++) {
			Examcatalog catalog = listECatalogs.get(i);
			if (catalog == null)
				continue;
			List<Optquestion> ques = OptquestionEntity
					.getByExamidExamcatalogid(catalog.getExamid(),
							catalog.getId());

			if (ListEx.isEmpty(ques))
				continue;

			for (Optquestion item : ques) {
				int optid = item.getOptid();
				Recordques4exam rquestion = Recordques4examEntity
						.getByCustomidQuestionid(customid, optid);
				itmsAll.initQuesion(item);
				itmsAuto.initQuesion(item);
				if (rquestion == null) {
					itmsNews.initQuesion(item);
				} else {
					if (rquestion.getNumError() > 1) {
						itmsErrors.initQuesion(item);
					}
				}
			}
		}
	}

	public List<Map<String, Object>> getList4Alls() {
		return itmsAll.getList4ExamCatalog();
	}

	public List<Map<String, Object>> getList4Errors() {
		return itmsErrors.getList4ExamCatalog();
	}

	public List<Map<String, Object>> getList4Newes() {
		return itmsNews.getList4ExamCatalog();
	}

	public List<Map<String, Object>> getList4Knowledge(String strKnowlegde) {
		return itmsAll.getList4Knowledge(strKnowlegde);
	}

	// type : 1 题型练习,2错误,3新题,4知识点,5自能组题
	public List<Optquestion> getList2Exam(int type, int numR, int numC,
			int numJuagde, int numFill, int numJDan, int numLunsu) {
		switch (type) {
		case 1:
			return itmsAll.getList2Exam(numR, numC, numJuagde, numFill,
					numJDan, numLunsu);
		case 2:
			return itmsErrors.getList2Exam(numR, numC, numJuagde, numFill,
					numJDan, numLunsu);
		case 3:
			return itmsNews.getList2Exam(numR, numC, numJuagde, numFill,
					numJDan, numLunsu);
		case 4:
			return itmsAll.getList2Exam4Knowledge(numR, numC, numJuagde,
					numFill, numJDan, numLunsu);
		case 5:
			return itmsAuto.getList2Exam(numR, numC, numJuagde, numFill,
					numJDan, numLunsu);
		default:
			return new ArrayList<Optquestion>();
		}
	}
}
