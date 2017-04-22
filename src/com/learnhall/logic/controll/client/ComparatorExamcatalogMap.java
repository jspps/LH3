package com.learnhall.logic.controll.client;

import java.util.Comparator;
import java.util.Map;

@SuppressWarnings("rawtypes")
public class ComparatorExamcatalogMap implements Comparator<Map> {
	@Override	
	public int compare(Map o1, Map o2) {
			if (null != o1.get("serial") && null != o2.get("serial")) {
				int s1 = ObjTOInt(o1.get("serial"));
				int s2 = ObjTOInt(o2.get("serial"));
				
				if (s1 > s2)
					return 1;
				else if (s1 < s2)
					return -1;
				else
					return 0;
			}
			return 0;
	}
	
	private int ObjTOInt(Object obj){
		return StringTOInt(obj.toString());
	}
	
	private int StringTOInt(String str){
		if(str!=null){
			if("一".equals(str)){
				return 1;
			}
			if("二".equals(str)){
				return 2;
			}
			if("三".equals(str)){
				return 3;
			}
			if("四".equals(str)){
				return 4;
			}
			if("五".equals(str)){
				return 5;
			}
			if("六".equals(str)){
				return 6;
			}
			if("七".equals(str)){
				return 7;
			}
			if("八".equals(str)){
				return 8;
			}
			if("九".equals(str)){
				return 9;
			}
			if("十一".equals(str)){
				return 11;
			}
			if("十二".equals(str)){
				return 12;
			}
			if("十三".equals(str)){
				return 13;
			}
		}
		return 0;
	}

}
