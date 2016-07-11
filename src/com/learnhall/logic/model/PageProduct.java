package com.learnhall.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Learnhub;
import com.learnhall.db.bean.Product;
import com.learnhall.db.entity.AdcoursesEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.LearnhubEntity;
import com.learnhall.db.entity.ProductEntity;

/**
 * 分页对象-产品Product的Map对象
 * 
 * @author Canyon
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageProduct extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return ProductEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Product> list = ProductEntity.getListBy(params, begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Product en = list.get(i);
					Map map = en.toBasicMap();
					result.add(map);
				}
				return result;
			}
		} catch (Exception e) {
		}
		return null;
	}

	/*** 给产品添加更多的属性[coures:该产品的课程,Kinds:该产品的套餐] **/
	public void resetList(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int productid = MapEx.getInt(map, "id");
				if (productid <= 0) {
					continue;
				}

				if (!map.containsKey("coures")) {

					int coursesid = MapEx.getInt(map, "coursesid");

					int lhubid = MapEx.getInt(map, "lhubid");

					// 课程
					Adcourses coures = AdcoursesEntity.getByKey(coursesid);
					map.put("coures", coures);

					// 学习中心
					Learnhub lhub = LearnhubEntity.getByKey(lhubid);
					map.put("lhub", lhub);

					// 套餐
					List<Kind> listKind = KindEntity.getByProductid(productid);
					map.put("kinds", listKind);
				}
			}
		}
	}

	/*** 给产品添加 课程 coures **/
	public void resetList4Coures(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int productid = MapEx.getInt(map, "id");
				if (productid <= 0) {
					continue;
				}

				if (!map.containsKey("coures")) {

					int coursesid = MapEx.getInt(map, "coursesid");

					// 课程
					Adcourses coures = AdcoursesEntity.getByKey(coursesid);
					map.put("coures", coures);
				}
			}
		}
	}

	/*** 给产品添加 课程 coures 和  Kind **/
	public void resetList4CouresKinds(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int productid = MapEx.getInt(map, "id");
				if (productid <= 0) {
					continue;
				}

				if (!map.containsKey("coures")) {

					int coursesid = MapEx.getInt(map, "coursesid");

					// 课程
					Adcourses coures = AdcoursesEntity.getByKey(coursesid);
					map.put("coures", coures);

					// 套餐
					List<Kind> listKind = KindEntity.getByProductid(productid);
					map.put("kinds", listKind);
					if (!ListEx.isEmpty(listKind)) {
						map.put("kindid", listKind.get(0).getId());
					}
				}
			}
		}
	}
}
