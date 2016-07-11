package com.learnhall.logic.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bowlong.lang.StrEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.bowlong.util.NewCpWrList;
import com.bowlong.util.NewMap;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Adqdepartment;
import com.learnhall.db.bean.Kind;
import com.learnhall.db.bean.Product;
import com.learnhall.db.entity.AdcoursesEntity;
import com.learnhall.db.entity.AdqdepartmentEntity;
import com.learnhall.db.entity.KindEntity;
import com.learnhall.db.entity.ProductEntity;

/*** 套餐Kind列表(1默认,2专业id,3课程名,4学习中心id) **/
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PageKinds extends APage<Map> {

	private static final long serialVersionUID = 1L;

	NewCpWrList<Kind> list = new NewCpWrList<Kind>();
	NewMap<Integer, Kind> map = new NewMap<Integer, Kind>();
	/*** 当前list集合 **/
	public List<Kind> curList = new ArrayList<Kind>();

	/*** 当前kind集合所对应的产品课程对象 **/
	public Map<Integer, Product> mapProduct4Kind = new HashMap<Integer, Product>();

	/*** 当前kind集合所对应的课程对象 **/
	public Map<Integer, Adcourses> mapCourse4Kind = new HashMap<Integer, Adcourses>();

	// 考试范围[key: areaid, value: areaName]
	public NewMap<String, String> mapArea = new NewMap<String, String>();
	// 学习中心[key: lhubid, value: lhubName]
	public NewMap<Integer, String> mapLhub = new NewMap<Integer, String>();
	// 学习科目[key: Subid, value: subName]
	public NewMap<String, String> mapSub = new NewMap<String, String>();
	// 层次[key: levid, value: levName]
	public NewMap<String, String> mapLev = new NewMap<String, String>();
	// 大分类名字
	public String nmDepart = "";
	// 专业名字
	public String nmMajor = "";
	// productId_lhubId ---> 多套餐ids
	NewMap<String, List<Integer>> mapRelation = new NewMap<String, List<Integer>>();

	public int departid = 0;
	public String subName = "";
	public int curLhubid = 0;
	public int curProductid = 0;
	public int curKindid = 0;

	// 1 默认,2初始化by majoy,3初始化 by subname,4初始化by lhubid 5初始化by curProductid
	// 6kindid
	int status = 1;

	public boolean isInit() {
		return status > 1;
	}

	public void clearAll() {
		list.clear();
		curList.clear();
		mapCourse4Kind.clear();
		mapProduct4Kind.clear();
		map.clear();
		mapLhub.clear();
		mapLev.clear();
		mapSub.clear();
		mapArea.clear();
		mapRelation.clear();

		nmDepart = "";
		nmMajor = "";
		subName = "";

		departid = 0;
		curLhubid = 0;
		curProductid = 0;
		curKindid = 0;
	}

	public void initList(int departid, String nmMajor) {
		if (departid <= 0 || StrEx.isEmptyTrim(nmMajor)) {
			status = 1;
			this.departid = 0;
			return;
		}

		if (status == 2 && this.departid == departid
				&& nmMajor.equals(this.nmMajor)) {
			return;
		}

		clearAll();
		status = 2;
		this.departid = departid;
		this.nmMajor = nmMajor;

		Adqdepartment depart = AdqdepartmentEntity.getByKey(departid);
		if (depart == null)
			return;
		nmDepart = depart.getName();

		List<Kind> listKind = KindEntity.getListByDepardidNmmajor(departid,
				nmMajor);
		initKindLists(listKind);
	}

	public void initList(String name) {
		if (StrEx.isEmptyTrim(name)) {
			status = 2;
			subName = "";
			return;
		}

		if (status == 3 && subName.equals(name)) {
			return;
		}
		clearAll();
		status = 3;
		subName = name;

		List<Kind> listKind = KindEntity.getListByName(name);
		initKindLists(listKind);
	}

	public void initListByLhubid(int lhubid) {
		if (lhubid <= 0) {
			status = 1;
			curLhubid = 0;
			return;
		}

		if (status == 4 && curLhubid == lhubid) {
			return;
		}
		clearAll();
		status = 4;
		curLhubid = lhubid;

		List<Kind> listKind = KindEntity.getByLhubid(lhubid);
		initKindLists(listKind);
	}

	public void initListByProductid(int productid) {
		if (productid <= 0) {
			status = 1;
			curProductid = 0;
			return;
		}

		if (status == 5 && curProductid == productid) {
			return;
		}

		clearAll();
		status = 5;
		curProductid = productid;
		List<Kind> listKind = KindEntity.getByProductid(curProductid);
		initKindLists(listKind);
	}

	public void initListByKindid(int kindid) {
		if (kindid <= 0) {
			status = 1;
			curKindid = 0;
			return;
		}

		if (status == 6 && curKindid == kindid) {
			return;
		}

		clearAll();
		status = 6;
		curKindid = kindid;
		Kind kind = KindEntity.getByKey(kindid);
		List<Kind> listKind = new ArrayList<Kind>();
		listKind.add(kind);
		initKindLists(listKind);
	}

	void initKindLists(List<Kind> listKind) {
		if (!ListEx.isEmpty(listKind)) {
			int len = listKind.size();
			for (int i = 0; i < len; i++) {

				Kind kind = listKind.get(i);
				if (kind == null || kind.getStatus() != 0)
					continue;

				Product product = kind.getProductFkProductid();
				if (product == null || product.getStatus() != 0
						|| product.getExamineStatus() != 3)
					continue;

				int kindid = kind.getId();

				mapProduct4Kind.put(kindid, product);

				Adcourses courses = mapCourse4Kind.get(kind.getCoursid());
				if (courses == null) {
					courses = kind.getAdcoursesFkCoursid();
					if (courses != null) {
						mapCourse4Kind.put(kind.getCoursid(), courses);
					}
				}

				if (courses == null)
					continue;

				int lhubid = kind.getLhubid();
				if (!mapLhub.containsKey(lhubid)) {
					mapLhub.put(lhubid, kind.getNmLhub());
				}

				mapSub.put(courses.getNmSub(), courses.getNmSub());
				mapLev.put(courses.getNmLevel(), courses.getNmLevel());
				mapArea.put(courses.getNmArea(), courses.getNmArea());

				int productId = kind.getProductid();
				String key = productId + "_" + lhubid;
				List<Integer> rlist = null;
				if (mapRelation.containsKey(key)) {
					rlist = mapRelation.get(key);
				} else {
					rlist = new ArrayList<Integer>();
				}
				rlist.add(kindid);
				mapRelation.put(key, rlist);

				list.add(kind);
				map.putE(kindid, kind);
			}
		}

		filter(null);
	}

	// 取得对象
	public Kind getKindById(int kindId) {
		return map.get(kindId);
	}

	public Adcourses getCourseByKindid(int kindId) {
		Kind kind = getKindById(kindId);
		if (kind == null)
			return null;
		return mapCourse4Kind.get(kind.getCoursid());
	}

	public Product getProductByKindid(int kindId) {
		return mapProduct4Kind.get(kindId);
	}

	public List<Kind> getKindsBy(int kindId) {
		List<Kind> result = new ArrayList<Kind>();
		Kind en = getKindById(kindId);
		if (en == null)
			return result;
		String key = en.getProductid() + "_" + en.getLhubid();
		List<Integer> list = mapRelation.get(key);
		for (Integer kid : list) {
			Kind tmp = getKindById(kid);
			if (tmp == null)
				continue;
			result.add(tmp);
		}
		return result;
	}

	/*** 过滤对象 **/
	public void filter(Map<String, Object> params) {
		curList.clear();
		int lens = list.size();
		for (int i = 0; i < lens; i++) {
			Kind en = list.get(i);
			if (!MapEx.isEmpty(params)) {
				int lhub = MapEx.getInt(params, "lhubid");
				if (lhub > 0) {
					if (lhub != en.getLhubid())
						continue;
				}
				Adcourses course = mapCourse4Kind.get(en.getCoursid());
				if (course == null) {
					continue;
				}

				int departid = MapEx.getInt(params, "departid");
				if (departid > 0) {
					if (departid != course.getDepartid())
						continue;
				}

				String nmMajor = MapEx.getString(params, "nmMajor");
				if (!StrEx.isEmptyTrim(nmMajor)) {
					if (!nmMajor.equals(course.getNmMajor()))
						continue;
				}

				String nmLevel = MapEx.getString(params, "nmLevel");
				if (!StrEx.isEmpty(nmLevel) && !"-1".equals(nmLevel)) {
					if (!nmLevel.equals(course.getNmLevel()))
						continue;
				}

				String nmSub = MapEx.getString(params, "nmSub");
				if (!StrEx.isEmpty(nmSub) && !"-1".equals(nmSub)) {
					if (!nmSub.equals(course.getNmSub()))
						continue;
				}

				String nmArea = MapEx.getString(params, "nmArea");
				if (!StrEx.isEmpty(nmArea) && !"-1".equals(nmArea)) {
					if (!nmArea.equals(course.getNmArea()))
						continue;
				}
			}
			curList.add(en);
		}
	}

	// 销量
	void sortByBuyCount(List<Kind> orign, boolean isDesc) {
		Comparator c = new Comparator<Kind>() {
			@Override
			public int compare(Kind o1, Kind o2) {
				int tmp1 = o1.getBuycount();
				int tmp2 = o2.getBuycount();
				if (tmp1 < tmp2)
					return -1;
				if (tmp1 > tmp2)
					return 1;
				return 0;
			}
		};

		Collections.sort(orign, c);
		if (!isDesc) {
			ListEx.reverse(orign);
		}
	}

	// 人气
	void sortByVisit(List<Kind> orign, boolean isDesc) {
		Comparator c = new Comparator<Kind>() {
			@Override
			public int compare(Kind o1, Kind o2) {
				int tmp1 = o1.getVisit();
				int tmp2 = o2.getVisit();
				if (tmp1 < tmp2)
					return -1;
				if (tmp1 > tmp2)
					return 1;
				return 0;
			}
		};

		Collections.sort(orign, c);
		if (!isDesc) {
			ListEx.reverse(orign);
		}
	}

	// 价格
	void sortByPrice(List<Kind> orign, boolean isDesc) {
		Comparator c = new Comparator<Kind>() {
			@Override
			public int compare(Kind o1, Kind o2) {
				double tmp1 = o1.getPrice();
				double tmp2 = o2.getPrice();
				if (tmp1 < tmp2)
					return -1;
				if (tmp1 > tmp2)
					return 1;
				return 0;
			}
		};

		Collections.sort(orign, c);
		if (!isDesc) {
			ListEx.reverse(orign);
		}
	}

	// 口碑
	void sortByPraise(List<Kind> orign, boolean isDesc) {
		Comparator c = new Comparator<Kind>() {
			@Override
			public int compare(Kind o1, Kind o2) {
				int tmp1 = o1.getPraise();
				int tmp2 = o2.getPraise();
				if (tmp1 < tmp2)
					return -1;
				if (tmp1 > tmp2)
					return 1;
				return 0;
			}
		};

		Collections.sort(orign, c);
		if (!isDesc) {
			ListEx.reverse(orign);
		}
	}

	@Override
	public int countAll(Map<String, Object> params) {
		return curList.size();
	}

	@Override
	public List<Map> getList(Map<String, Object> params, int page, int pageSize) {
		if (!MapEx.isEmpty(params)) {
			// 销量[1升序，2降序]
			int buycount = MapEx.getInt(params, "buycount");
			if (buycount > 0) {
				sortByBuyCount(curList, buycount == 2);
			}
			// 人气[1升序，2降序]
			int visit = MapEx.getInt(params, "visit");
			if (visit > 0) {
				sortByVisit(curList, visit == 2);
			}
			// 价格[1升序，2降序]
			int price = MapEx.getInt(params, "price");
			if (price > 0) {
				sortByPrice(curList, price == 2);
			}
			// 口碑[1升序，2降序]
			int praise = MapEx.getInt(params, "praise");
			if (praise > 0) {
				sortByPraise(curList, praise == 2);
			}
		}

		List<Kind> listTmp = ListEx.getPageT(curList, page, pageSize);
		if (!ListEx.isEmpty(listTmp)) {
			int lens = listTmp.size();
			List<Map> result = new ArrayList<Map>();
			for (int i = 0; i < lens; i++) {
				Kind en = listTmp.get(i);
				Map map = en.toBasicMap();
				result.add(map);
			}
			return result;
		}
		return null;
	}

	/*** 给Kind添加更多的属性[course:该课程,product:该产品] **/
	public void resetList(List<Map> list) {
		if (!ListEx.isEmpty(list)) {
			int lens = list.size();
			for (int i = 0; i < lens; i++) {
				Map map = list.get(i);
				if (map == null) {
					continue;
				}

				int coursid = MapEx.getInt(map, "coursid");
				if (coursid > 0) {
					Adcourses enTmp = AdcoursesEntity.getByKey(coursid);
					if (enTmp != null) {
						map.put("course", enTmp);
					}
				}
				int productid = MapEx.getInt(map, "productid");
				if (productid > 0) {
					Product enTmp = ProductEntity.getByKey(productid);
					if (enTmp != null) {
						map.put("product", enTmp);
					}
				}
			}
		}
	}
}
