package com.learnhall.logic.controll.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.ui.ModelMap;

import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.db.bean.Exam;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.bean.Optquestion;
import com.learnhall.db.entity.ExamcatalogEntity;
import com.learnhall.db.entity.OptquestionEntity;

/**
 * 一个目录 + 该结构下面的 list question
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class ExamCatalogAndListQuestion {
	// 是不是案例分析的根目录
	static boolean isAnalysisCatalog(Examcatalog catalog) {
		return (catalog.getCatalogType() == 7 && catalog.getGid() == 0);
	}

	// 是不是案例分析的子目录
	static boolean isAnalysisChildCatalog(Examcatalog catalog) {
		return (catalog.getCatalogType() == 7 && catalog.getGid() != 0);
	}

	// 目录 + list question
	static Map ToMapCatalogAndListQuestion(Examcatalog catalog) {
		Map map = catalog.toBasicMap();

		String elps = StrEx.ellipsis(catalog.getTitle(), 10);
		int index = elps.indexOf("<img");
		if (index != -1) {
			elps = elps.substring(0, index);
			elps += "...";
		}

		index = elps.indexOf("<p");
		if (index != -1) {
			elps = elps.substring(0, index);
			elps += "...";
		}
		map.put("titleEllipsis", elps);

		boolean isAnalysisCatalog = isAnalysisCatalog(catalog);
		if (!isAnalysisCatalog) {
			List<Optquestion> listChild = OptquestionEntity
					.getByExamcatalogid(catalog.getId());
			int lens = 0;
			List<Map> listChildMap = null;
			if (!ListEx.isEmpty(listChild)) {
				lens = listChild.size();
				listChildMap = new ArrayList<Map>();
			}

			Map tmp = null;
			for (int i = 0; i < lens; i++) {
				tmp = listChild.get(i).toBasicMap();
				tmp.put("isOldContent",
						listChild.get(i).getContent().indexOf("<p") == -1);
				listChildMap.add(tmp);
			}

			if (listChildMap != null)
				map.put("listChild", listChildMap);
		}
		return map;
	}

	/**
	 * 取得试卷详情(目录+试题)
	 * 
	 * @param examid
	 * @return
	 */
	static public List<Map> getExamDetailsByExamid(int examid) {
		List<Examcatalog> examcatalogs = ExamcatalogEntity.getByExamid(examid);

		List<Map> lmResult = new ArrayList<Map>();
		if (!ListEx.isEmpty(examcatalogs)) {
			int lens = 0;

			// 一级目录
			List<Examcatalog> tmpList = new ArrayList<Examcatalog>();
			lens = examcatalogs.size();
			for (int i = 0; i < lens; i++) {
				Examcatalog item = examcatalogs.get(i);
				if (item.getStatus() != 0) {
					continue;
				}

				if (isAnalysisChildCatalog(item)) {
					continue;
				}
				tmpList.add(item);
			}
			Collections.sort(tmpList, new ComparatorExamcatalog());

			lens = tmpList.size();
			List<Integer> tmp2List = new ArrayList<Integer>();
			for (int i = 0; i < lens; i++) {
				Examcatalog item = tmpList.get(i);
				if (isAnalysisCatalog(item)) {
					tmp2List.add(item.getId());
				}

				Map map = ToMapCatalogAndListQuestion(item);
				lmResult.add(map);
				examcatalogs.remove(item);
			}

			// 二级目录
			lens = tmp2List.size();
			int id = -1;
			int id2 = -1;
			int index = -1;
			Map map = null;
			int parentid = -1;
			int index2 = -1;

			A: for (int i = 0; i < lens; i++) {
				parentid = -1;
				index = -1;

				id = tmp2List.get(i);
				B: for (int j = 0; j < lmResult.size(); j++) {
					map = lmResult.get(j);
					id2 = MapEx.getInt(map, "id");
					if (id2 == id) {
						parentid = id2;
						index = j;
						break B;
					}
				}

				if (parentid == -1) {
					continue A;
				}

				index2 = 0;
				C: for (Examcatalog ec : examcatalogs) {
					if (ec.getStatus() != 0) {
						continue C;
					}

					if (ec.getParentid() == parentid) {
						index2++;
						map = ToMapCatalogAndListQuestion(ec);
						lmResult.add(index + index2, map);
					}
				}
			}
		}
		return lmResult;
	}

	private static List<Map> getLM4ExamCatalog(List<Examcatalog> list) {
		List<Map> result = new ArrayList<Map>();
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();

			for (int i = 0; i < lens; i++) {
				Examcatalog en = list.get(i);
				if (en == null)
					continue;
				Map map = en.toBasicMap();

				result.add(map);
			}
		}
		return result;
	}

	// 取得试卷目录结构
	static public int getExamCatalogs(Exam en, ModelMap modelMap) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("examid", "=" + en.getId());
		params.put("status", "!= 1");
		params.put("parentid", "= 0");

		List<Map> result;
		try {
			List<Examcatalog> list = ExamcatalogEntity.getListBy(params, 0, 20);
			result = getLM4ExamCatalog(list);
		} catch (Exception e) {
			result = new ArrayList<Map>();
		}
		modelMap.addAttribute("list", result);

		return result.size();
	}
}
