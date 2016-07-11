package com.learnhall.logic;

/**
 * 所有的HttpSession的属性attr的key值
 * 
 * @author Administrator
 * 
 */
public class SessionKeys {

	/*** 头部状态1首页，2考试榜，3全部课程 **/
	static public final String CurTop = "CurTop";

	/*** 头部状态1学习，2问答，3帐号，4消息，5设置 **/
	static public final String CurSigleTop = "CurSigleTop";

	/*** 学员用户 **/
	static public final String Customer = "Customer";

	/*** 分页我的考币对象 **/
	static public final String PageMyKbi = "PageMyKbi";

	/*** 分页我的资金rmb对象 **/
	static public final String PageMyRMB = "PageMyRMB";

	/*** 分页种类Kind对象 **/
	static public final String PageSieve = "PageSieve";

	/*** 课程筛选的分类的参数对象map **/
	static public final String Filter4MarjorKind = "Filter4MarjorKind";

	/*** 分页套餐kind下面的Exam对象 **/
	static public final String PageKExam = "PageKExam";

	/*** 分页套餐kind下面的Exam对象下面的ExamITMS **/
	static public final String ExamITMS = "ExamITMS";

	/*** 购物车列表Map对象 **/
	static public final String ShopCutMap = "ShopCutMap";

	/*** 一个订单的session(防止刷新) **/
	static public final String ShopCartOneOrderSession = "ShopCartOneOrderSession";

	/*** 购物车订单编码Code **/
	static public final String ShopCartCode = "ShopCartCode";

	/*** 当前试卷 **/
	static public final String CurExam = "CurExam";

	/*** 当前试卷的记录实体对象 **/
	static public final String CurExamRecord = "CurExamRecord";

	/*** 试卷问题Map对象 **/
	static public final String MapQuestions = "MapQuestions";

	/*** 试卷答案Map **/
	static public final String MapAnswerDate = "MapAnswerDate";

	/*** 试卷每题得分Map **/
	static public final String MapScore4Exam = "MapScore4Exam";

	/*** 试卷目录Map **/
	static public final String MapExamCatalog = "MapExamCatalog";

	/*** 前一个访问的地址 **/
	static public final String UrlPre = "UrlPre";

	/*** 当前访问的地址 **/
	static public final String UrlCur = "UrlCur";

	/*** 要去的地址 **/
	static public final String UrlGo2 = "UrlGo2";

	/*** 考试状态 [1.已经扣除考币] **/
	static public final String Status4Examing = "Status4Examing";

	/*** 考试所要消耗的考币值 **/
	static public final String NdKbi4Examing = "NdKbi4Examing";

	/*** 购买课程套餐对象 **/
	static public final String PageRecordAnswer = "PageRecordAnswer";

	/*** 购买课程套餐对象 **/
	static public final String PageBuyKindCourses = "PageBuyKindCourses";

	/*** 是否体验考试 **/
	static public final String IsTestExam = "IsTestExam";
}
