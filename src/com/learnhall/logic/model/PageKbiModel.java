package com.learnhall.logic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Recordkbi4customer;
import com.learnhall.db.entity.Recordkbi4customerEntity;

public class PageKbiModel extends APage<Recordkbi4customer> {

	private static final long serialVersionUID = 1L;

	List<Recordkbi4customer> listIn = new ArrayList<Recordkbi4customer>();
	List<Recordkbi4customer> listOut = new ArrayList<Recordkbi4customer>();

	void sort(List<Recordkbi4customer> list) {
		Collections.sort(list, new Comparator<Recordkbi4customer>() {
			@Override
			public int compare(Recordkbi4customer o1, Recordkbi4customer o2) {
				if (DateEx.isBefore(o1.getCreatetime(), o2.getCreatetime()))
					return 1;
				return -1;
			}
		});
	}

	public void initDataByCid(int customerid) {
		List<Recordkbi4customer> all = Recordkbi4customerEntity
				.getByCustid(customerid);
		listIn.clear();
		listOut.clear();
		if (ListEx.isEmpty(all))
			return;
		int len = all.size();
		for (int i = 0; i < len; i++) {
			Recordkbi4customer en = all.get(i);
			int type = en.getType();
			switch (type) {
			case 1:
				listIn.add(en);
				break;
			default:
				listOut.add(en);
				break;
			}
		}
		sort(listIn);
		sort(listOut);
	}

	@Override
	public int countAll(Map<String, Object> params) {
		int type = MapEx.getInt(params, "type");
		type = type <= 1 ? 1 : 2;
		if (type == 1)
			return listIn.size();
		return listOut.size();
	}

	@Override
	public List<Recordkbi4customer> getList(Map<String, Object> params,
			int page, int pageSize) {
		int type = MapEx.getInt(params, "type");
		type = type <= 1 ? 1 : 2;
		if (type == 1)
			return ListEx.getPageT(listIn, page, pageSize);
		return ListEx.getPageT(listOut, page, pageSize);
	}

}
