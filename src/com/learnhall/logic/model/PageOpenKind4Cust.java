package com.learnhall.logic.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Openkind4customer;
import com.learnhall.db.entity.Openkind4customerEntity;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageOpenKind4Cust extends APage<Map> {

	private static final long serialVersionUID = 1L;

	@Override
	public int countAll(Map<String, Object> params) {
		try {
			return Openkind4customerEntity.getCountAllBy(params);
		} catch (Exception e) {
		}
		return 0;
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		try {
			int begin = (page - 1) * pageSize;
			int limit = pageSize;
			List<Openkind4customer> list = Openkind4customerEntity.getListBy(
					params, begin, limit);
			if (!ListEx.isEmpty(list)) {
				List<Map> result = new ArrayList<Map>();
				int lens = list.size();
				for (int i = 0; i < lens; i++) {
					Openkind4customer en = list.get(i);
					if (en == null)
						continue;

					Map map = en.toBasicMap();
					Kind enKind = en.getKindFkKindid();
					if (enKind != null) {
						map.put("kindEn", enKind);
						// Adcourses course = enKind.getAdcoursesFkCoursid();
						// if (course != null) {
						// map.put("course", course);
						// map.put("nmDepart", course
						// .getAdqdepartmentFkDepartid().getName());
						// }
					}
					// Customer enCust = en.getCustomerFkCustomerid();
					// if (enCust != null) {
					// Account enAcc = enCust.getAccountFkAccountid();
					// map.put("accEn", enAcc);
					// }
					result.add(map);
				}
				return result;
			}

		} catch (Exception e) {
		}
		return null;
	}

}
