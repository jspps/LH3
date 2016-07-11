package com.learnhall.logic.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewCpWrList;
import com.bowlong.util.NewMap;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Exam;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.entity.ExamEntity;
import com.learnhall.db.entity.ExamcatalogEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.OptquestionEntity;

/**
 * 套餐结构(下面所包含的东西)
 * 
 * @author Canyon
 * 
 *         createtime:2015-5-6 下午10:09:07
 */
public class PageKExam extends APage<Exam> {

	private static final long serialVersionUID = 1L;

	/*** 章节练习 **/
	static public final int zjlx = 1;
	/*** 历年真题 **/
	static public final int lnzt = 2;
	/*** 全真模拟 **/
	static public final int qzmn = 3;
	/*** 绝胜押题 **/
	static public final int jsyt = 4;

	// 章节练习
	NewCpWrList<Exam> listZJLX = new NewCpWrList<Exam>();
	// 历年真题
	NewCpWrList<Exam> listLNZT = new NewCpWrList<Exam>();
	// 全真模拟
	NewCpWrList<Exam> listQZMN = new NewCpWrList<Exam>();
	// 绝胜押题
	NewCpWrList<Exam> listJSYT = new NewCpWrList<Exam>();

	NewMap<Integer, Exam> map = new NewMap<Integer, Exam>();

	// 试卷id所对应的题目数量
	public Map<Integer, Integer> map4Num = new HashMap<Integer, Integer>();

	// 考试套餐的所有试卷的目录
	public Map<Integer, Examcatalog> mapCatalog = new HashMap<Integer, Examcatalog>();

	public Kind kind = null;
	public boolean isBuyed = false;
	public int curKindId = 0;

	public boolean initList(Kind knd, boolean isReInit) {
		if (knd == null)
			return false;
		int kindId = knd.getId();
		if (kindId == curKindId) {
			if (isReInit) {
				knd = KindEntity.getByKey(kindId);
			} else {
				return true;
			}
		}
		
		curKindId = kindId;
		kind = knd;

		isBuyed = false;
		listZJLX.clear();
		listLNZT.clear();
		listQZMN.clear();
		listJSYT.clear();
		map.clear();
		mapCatalog.clear();
		map4Num.clear();

		List<Integer> examids = ListEx.toListInt(kind.getExamids());
		if (!ListEx.isEmpty(examids)) {
			int len = examids.size();
			for (int i = 0; i < len; i++) {
				int examid = examids.get(i);
				Exam exam = ExamEntity.getByKey(examid);
				if (exam == null)
					continue;

				// 判断是否已经删除
				if (exam.getStatus() == 1)
					continue;

				map.put(examid, exam);
				switch (exam.getExamtypeid()) {
				case zjlx:
					listZJLX.add(exam);
					break;
				case lnzt:
					listLNZT.add(exam);
					break;
				case qzmn:
					listQZMN.add(exam);
					break;
				case jsyt:
					listJSYT.add(exam);
					break;
				default:
					break;
				}

				int num = OptquestionEntity.getCountByExamid(examid);
				map4Num.put(examid, num);
			}
		}

		return true;
	}

	public Exam getExam(int examId) {
		return map.get(examId);
	}

	// 取得该套餐所有的试卷的试卷目录列表
	void initExamCatalogs4Kind() {
		if (!MapEx.isEmpty(mapCatalog))
			return;

		List<Integer> listExamIds = ListEx.keyToList(map);
		if (ListEx.isEmpty(listExamIds))
			return;
		int len = listExamIds.size();
		for (int i = 0; i < len; i++) {
			int examid = listExamIds.get(i);
			List<Examcatalog> catalog = ExamcatalogEntity.getByExamid(examid);
			for (Examcatalog item : catalog) {
				if (item.getStatus() == 1
						|| mapCatalog.containsKey(item.getId()))
					continue;
				mapCatalog.put(item.getId(), item);
			}
		}
	}

	// 取得该套餐所有的试卷的试卷目录列表
	public List<Examcatalog> getExamCatalogs4Kind() {
		initExamCatalogs4Kind();
		return ListEx.valueToList(mapCatalog);
	}

	@Override
	public int countAll(Map<String, Object> params) {
		List<Exam> list = null;
		int type = zjlx;
		if (params != null)
			type = MapEx.getInt(params, "type");
		switch (type) {
		case zjlx: // 章节练习
			list = listZJLX;
			break;
		case lnzt: // 历年真题
			list = listLNZT;
			break;
		case qzmn: // 全真模拟
			list = listQZMN;
			break;
		case jsyt: // 绝胜押题
			list = listJSYT;
			break;
		default:
			break;
		}

		if (list != null)
			return list.size();
		return 0;
	}

	@Override
	public List<Exam> getList(Map<String, Object> params, int page, int pageSize) {
		List<Exam> list = null;
		int type = zjlx;
		if (params != null)
			type = MapEx.getInt(params, "type");
		switch (type) {
		case zjlx: // 章节练习
			list = listZJLX;
			break;
		case lnzt: // 历年真题
			list = listLNZT;
			break;
		case qzmn: // 全真模拟
			list = listQZMN;
			break;
		case jsyt: // 绝胜押题
			list = listJSYT;
			break;
		default:
			break;
		}
		if (list == null)
			return null;
		return ListEx.getPageT(list, page, pageSize);
	}

}
