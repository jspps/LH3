package com.learnhall.enums;

/**
 * 考试的枚举
 * 
 * @author Canyon
 * 
 */
public enum ExamEnum {
	Exam("试卷考试", 1), 
	SeeAnswer("查看答案", 2),
	ExamITMS("ITMS考试", 3),
	ExamWrong("重做错题", 4);

	// 成员变量
	private String name;
	private int index;

	// 构造方法
	private ExamEnum(String name, int index) {
		this.name = name;
		this.index = index;
	}

	// 普通方法
	public static String getName(int index) {
		for (ExamEnum c : ExamEnum.values()) {
			if (c.getIndex() == index) {
				return c.name;
			}
		}
		return null;
	}

	// get set 方法
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	// 覆盖方法
	@Override
	public String toString() {
		return this.index + "_" + this.name;
	}
}
