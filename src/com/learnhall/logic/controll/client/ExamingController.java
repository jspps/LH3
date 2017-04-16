package com.learnhall.logic.controll.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bowlong.json.MyJson;
import com.bowlong.lang.PStr;
import com.bowlong.lang.StrEx;
import com.bowlong.util.DateEx;
import com.bowlong.util.ListEx;
import com.bowlong.util.MapEx;
import com.learnhall.content.Svc;
import com.learnhall.db.bean.Adcourses;
import com.learnhall.db.bean.Customer;
import com.learnhall.db.bean.Errorfeedback;
import com.learnhall.db.bean.Exam;
import com.learnhall.db.bean.Examcatalog;
import com.learnhall.db.bean.Itms4day;
import com.learnhall.db.bean.Optquestion;
import com.learnhall.db.bean.Product;
import com.learnhall.db.bean.Recordanswer;
import com.learnhall.db.bean.Recordques4exam;
import com.learnhall.db.entity.AdcoursesEntity;
import com.learnhall.db.entity.ExamEntity;
import com.learnhall.db.entity.OptquestionEntity;
import com.learnhall.db.entity.ProductEntity;
import com.learnhall.db.entity.RecordanswerEntity;
import com.learnhall.db.entity.Recordques4examEntity;
import com.learnhall.logic.SessionKeys;
import com.learnhall.logic.Utls;
import com.learnhall.logic.model.PageKExam;

/**
 * 前台界面-控制器-在线考试
 * 
 * @author zhangwen
 * 
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
@Controller
@RequestMapping("/doClient")
public class ExamingController {
	static void sortList(List<Optquestion> listQues) {
		Collections.sort(listQues, new Comparator<Optquestion>() {
			@Override
			public int compare(Optquestion o1, Optquestion o2) {
				int tp1 = o1.getType();
				int tp2 = o2.getType();
				if (tp1 < tp2)
					return -1;
				if (tp1 > tp2)
					return 1;
				int c1 = o1.getExamcatalogid();
				int c2 = o2.getExamcatalogid();
				if (c1 < c2)
					return -1;
				if (c1 > c2)
					return 1;
				int id1 = o1.getOptid();
				int id2 = o2.getOptid();
				if (id1 < id2)
					return -1;
				if (id1 > id2)
					return 1;
				return 0;
			}
		});
	}

	static public void removeSesesion4Exam(HttpSession session) {
		// 移除回答
		session.removeAttribute(SessionKeys.MapAnswerDate);
		// 移除得分
		session.removeAttribute(SessionKeys.MapScore4Exam);
		// 移除问题
		session.removeAttribute(SessionKeys.MapQuestions);
		// 试卷目录
		session.removeAttribute(SessionKeys.MapExamCatalog);
		// 当前考试状态:1.已经扣除考币
		session.removeAttribute(SessionKeys.Status4Examing);
		// 当前考试所需考币值
		session.removeAttribute(SessionKeys.NdKbi4Examing);
		// 移除考试答案记录
		session.removeAttribute(SessionKeys.CurExamRecord);
	}

	static private Map<Integer, Optquestion> getMapQuestions(HttpSession session) {
		return (Map<Integer, Optquestion>) session
				.getAttribute(SessionKeys.MapQuestions);
	}

	static private Map<Integer, String> getMapAnswers(HttpSession session) {
		return (Map<Integer, String>) session
				.getAttribute(SessionKeys.MapAnswerDate);
	}

	static private Map<Integer, Integer> getMapScore4Exam(HttpSession session) {
		return (Map<Integer, Integer>) session
				.getAttribute(SessionKeys.MapScore4Exam);
	}

	static private Map<Integer, Examcatalog> getMapExamCatalogs(
			HttpSession session) {
		return (Map<Integer, Examcatalog>) session
				.getAttribute(SessionKeys.MapExamCatalog);
	}

	static private String getStatus4Examing(HttpSession session) {
		Object status = session.getAttribute(SessionKeys.Status4Examing);
		Utls.Msg4Empty = "";
		if (status == null) {
			Utls.Msg4Empty = "还未支付考币，不能考好试！";
			return "redirect:/msgEmpty";
		}
		return "";
	}

	static private int getNdKbi4Examing(HttpSession session) {
		Object ndkbi = session.getAttribute(SessionKeys.NdKbi4Examing);
		if (ndkbi == null)
			return 0;
		return (int) ndkbi;
	}

	static private Exam getExam(HttpSession session) {
		return (Exam) session.getAttribute(SessionKeys.CurExam);
	}

	static private Recordanswer getExamRecord(HttpSession session) {
		return (Recordanswer) session.getAttribute(SessionKeys.CurExamRecord);
	}

	/*** [examType:1考试,2查看答卷] **/
	static public boolean initExam(HttpServletRequest request,
			HttpSession session, ModelMap modelMap, int examType) {
		Map map = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(map, "examid");
		int preExamId = 0;
		Exam exam = getExam(session);
		if (exam != null) {
			preExamId = exam.getId();
		}

		if (examid == 0) {
			examid = preExamId;
			if (examid == 0)
				return false;
		}

		if (preExamId != examid) {
			exam = ExamEntity.getByKey(examid);
		}

		if (exam == null)
			return false;

		session.setAttribute(SessionKeys.CurExam, exam);
		// 考试时间
		int examTime = exam.getExamtime();
		modelMap.addAttribute("name", exam.getName());
		return initQues(session, modelMap, examType, examid, examTime);
	}

	/*** 初始化试卷[examType:1考试,2查看答卷,3ITMS,4重做exam的错题] **/
	static public boolean initQues(HttpSession session, ModelMap modelMap,
			int examType, int examid, int examTime) {

		if (examType == 1) {
			removeSesesion4Exam(session);
		}

		Map<Integer, Optquestion> mapQues = getMapQuestions(session);
		Map<Integer, Examcatalog> mapExamCatalogs = getMapExamCatalogs(session);
		List<Optquestion> listQues = null;

		// 是否初始化了MapQuestion,MapExamCatalog
		boolean isInitMapQuesAndCatalog = true;

		if (MapEx.isEmpty(mapQues) || MapEx.isEmpty(mapExamCatalogs)) {
			if (examid > 0) {
				listQues = OptquestionEntity.getByExamid(examid);
			}

			isInitMapQuesAndCatalog = false;
			mapExamCatalogs = MapEx.clear4MapKV(mapExamCatalogs);
			mapQues = MapEx.clear4MapKV(mapQues);
		} else {
			if (examType == 4) {
				listQues = new ArrayList<Optquestion>();
				Map<Integer, Integer> mapScore = getMapScore4Exam(session);
				for (Entry<Integer, Integer> entry : mapScore.entrySet()) {
					int opt_id = entry.getKey();
					int opt_scroce = entry.getValue();
					if (opt_scroce <= 0) {
						Optquestion opt = mapQues.get(opt_id);
						if (opt == null)
							continue;
						listQues.add(opt);
					}
				}
			} else {
				listQues = ListEx.valueToList(mapQues);
			}
		}

		if (ListEx.isEmpty(listQues))
			return false;

		sortList(listQues);

		int lens = listQues.size();

		// 考币消耗值
		int allScore = 0;
		session.removeAttribute(SessionKeys.NdKbi4Examing);

		// 考题左边数据
		List<Map<String, Object>> listLeft = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < lens; i++) {
			Optquestion en = listQues.get(i);
			int examcatalogid = en.getExamcatalogid();
			Map<String, Object> mapLeft = new HashMap<String, Object>();
			mapLeft.put("type", en.getType());
			mapLeft.put("examcatalogid", examcatalogid);
			mapLeft.put("optid", en.getOptid());
			Examcatalog ecatalog = null;
			if (isInitMapQuesAndCatalog) {
				ecatalog = mapExamCatalogs.get(examcatalogid);
			} else {
				// 初始化Map对象
				mapQues.put(en.getOptid(), en);
				ecatalog = en.getExamcatalogFkExamcatalogid();
				if (ecatalog != null) {
					mapExamCatalogs.put(examcatalogid, ecatalog);
				}
			}

			if (ecatalog != null) {
				allScore += ecatalog.getEveryScore();
				mapLeft.put("serial", ecatalog.getSerial());
				mapLeft.put("bigtypes", ecatalog.getBigtypes());
				
				String elps = StrEx.ellipsis(ecatalog.getTitle(), 10);
				int index = elps.indexOf("<img");
				if(index != -1){
					elps = elps.substring(0, index);
					elps += "...";
				}
				
				index = elps.indexOf("<p");
				if(index != -1){
					elps = elps.substring(0, index);
					elps += "...";
				}
				mapLeft.put("titleEllipsis",elps);
			}

			listLeft.add(mapLeft);
		}

		session.setAttribute(SessionKeys.NdKbi4Examing, allScore);
		session.setAttribute(SessionKeys.MapQuestions, mapQues);
		session.setAttribute(SessionKeys.MapExamCatalog, mapExamCatalogs);

		modelMap.put("listLeft", listLeft);
		modelMap.put("curExamLen", lens);

		int curOptId = lens > 0 ? listQues.get(0).getOptid() : 0;
		int nxtOptId = lens > 1 ? listQues.get(1).getOptid() : 0;
		optQuestion(true, 1, curOptId, nxtOptId, lens, session, modelMap);

		modelMap.addAttribute("examTime", examTime);
		String v = "{}";
		String score4exam = "{}";
		try {
			if (examType == 2) {
				Map<Integer, String> mapdate = getMapAnswers(session);
				Map<Integer, Integer> mapscore = getMapScore4Exam(session);
				int custid = Utls.getCustomerId(session);
				if (custid > 0) {
					Recordanswer r = getExamRecord(session);
					if (r == null && examid > 0) {
						r = RecordanswerEntity.getByExamidCustomerid(examid,
								custid);
					}

					if (r != null) {
						if (mapdate == null) {
							mapdate = Utls.bytes2Map(r.getAnwers());
						}
						if (mapscore == null) {
							mapscore = Utls.bytes2Map(r.getScore4ques());
						}
					}
				}

				if (mapdate != null) {
					session.setAttribute(SessionKeys.MapAnswerDate, mapdate);
					v = MyJson.formatString(mapdate);
				}

				if (mapscore != null) {
					session.setAttribute(SessionKeys.MapScore4Exam, mapscore);
					score4exam = MyJson.formatString(mapscore);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("json", v);
		modelMap.addAttribute("json4score", score4exam);
		return true;
	}

	static void optQuestion(boolean isFirst, int titNum, int curOptId,
			int nxtOptId, int maxLen, HttpSession session, ModelMap modelMap) {
		boolean isMax = false;
		if (isFirst) {
			if (titNum >= maxLen) {
				isMax = true;
			}
		} else {
			isMax = (titNum + 1 > maxLen);
		}

		if (isMax) {
			titNum = maxLen;
		}

		Map<Integer, Optquestion> mapQues = getMapQuestions(session);

		modelMap.addAttribute("num", titNum);
		Optquestion viewCurOpt = null;
		if (isFirst) {
			viewCurOpt = mapQues.get(curOptId);
		} else {
			if (nxtOptId > 0) {
				viewCurOpt = mapQues.get(nxtOptId);
			} else {
				viewCurOpt = mapQues.get(curOptId);
			}
		}
		
		Map oneQuest = viewCurOpt.toBasicMap();
		oneQuest.put("isOldContent",viewCurOpt.getContent().indexOf("<p>") == -1);
		
		modelMap.addAttribute("curOpt", oneQuest);
		modelMap.addAttribute("isOver", isMax ? 0 : 1);
		modelMap.addAttribute("curExamLen", maxLen);

		// === 试卷题的目录
		int examcatalogid = viewCurOpt.getExamcatalogid();
		Map<Integer, Examcatalog> mapExamCatalogs = getMapExamCatalogs(session);
		Examcatalog catalog = mapExamCatalogs.get(examcatalogid);
		modelMap.addAttribute("examcatalog", catalog);
	}

	/*** 在线模考体验 **/
	@RequestMapping("/examing")
	public String examing(HttpServletRequest request, HttpSession session,
			ModelMap modelMap) {
		boolean isInitExam = initExam(request, session, modelMap, 1);
		if (!isInitExam)
			return "redirect:home";

		return "client/examing/examing";
	}

	/*** 处理试题(提交答案，取得下题内容) **/
	@RequestMapping("/optquestionDes")
	public String dealWithQuestion(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		return handleQuestion(request, response, modelMap, session);
	}

	/*** 处理考试(读取试题) **/
	static public String handleQuestion(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {

		String status = getStatus4Examing(session);
		if (!StrEx.isEmptyTrim(status)) {
			return status;
		}

		Map map = Svc.getMapAllParams(request);
		int curOptId = MapEx.getInt(map, "curOptId");
		int titNum = MapEx.getInt(map, "titNum");
		int nxtOptId = MapEx.getInt(map, "nxtOptId");
		String datedaan = MapEx.getString(map, "datedaan");
		boolean isAnswer = MapEx.getBoolean(map, "isAnswer");
		int maxLen = MapEx.getInt(map, "curExamLen");

		if (Utls.getIsTestExam(session)) {
			// 体验试卷
			if (titNum > 5) {
				Utls.Msg4Empty = "体验试卷，只能体验前5题！";
				return "redirect:/msgEmpty";
			}
		}

		Map<Integer, String> mapdate = getMapAnswers(session);
		if (isAnswer) {
			if (mapdate == null) {
				mapdate = new HashMap<Integer, String>();
			}
			mapdate.put(curOptId, datedaan);
			session.setAttribute(SessionKeys.MapAnswerDate, mapdate);
			if (nxtOptId == 0) {
				titNum--;
			}
		}

		boolean isFirst = !isAnswer;
		if (modelMap.containsKey("see_dj")) {
			// 查看答卷的时候，那边isAnswer是false,不加判断就一直取第一条数据
			boolean isLeft = MapEx.getBoolean(map, "isLeft");
			isFirst = isLeft;
			String see_dj = (String) modelMap.get("see_dj");
			if (StrEx.isEmptyTrim(see_dj) || "0".equals(see_dj)) {
				int reqType = MapEx.getInt(map, "reqType");
				if (reqType == 0) {
					isFirst = false;
					int curType = MapEx.getInt(map, "curType");
					if (curType > 3) {
						int selfScore = MapEx.getInt(map, "selfScore");
						if (selfScore > 0) {
							handleSubject(session, curOptId, curType, selfScore);
						}
					}
				}
			}
		}

		optQuestion(isFirst, titNum, curOptId, nxtOptId, maxLen, session,
				modelMap);

		String v = "{}";
		String score4exam = "{}";
		try {
			if (mapdate != null) {
				v = MyJson.formatString(mapdate);
			}
			Map<Integer, Integer> mapscore = getMapScore4Exam(session);
			if (mapscore != null) {
				score4exam = MyJson.formatString(mapscore);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		modelMap.addAttribute("json", v);
		modelMap.addAttribute("json4score", score4exam);
		return "client/examing/right";
	}

	/*** jsop判断打完题没有 **/
	@RequestMapping("/judgeAnswers")
	public void judgeAnswers(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map result = new HashMap();
		Map pars = Svc.getMapAllParams(request);
		int maxLen = MapEx.getInt(pars, "curExamLen");
		int curLen = 0;
		if (maxLen == 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "出现了异常！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map<Integer, String> mapdate = getMapAnswers(session);
		if (!MapEx.isEmpty(mapdate)) {
			for (Entry<Integer, String> entry : mapdate.entrySet()) {
				if (StrEx.isEmpty(entry.getValue()))
					continue;
				curLen++;
			}
		}

		int timediff = MapEx.getInt(pars, "timediff");
		if (timediff > 0) {
			if (curLen != maxLen) {
				result = Utls.tipMap(result, Utls.Status_Erro, "还有"
						+ (maxLen - curLen) + "道题没有选择答案！");
				Utls.writeAndClose(response, result);
				return;
			}
		}

		result = Utls.tipMap(result, Utls.Status_Success, "答题完毕！");
		Utls.writeAndClose(response, result);
	}

	/*** 判断答题有没有完成 **/
	@RequestMapping("/judgePage4Examing")
	public String judgePage4Examing(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map pars = Svc.getMapAllParams(request);
		int maxLen = MapEx.getInt(pars, "curExamLen");
		int curLen = 0;
		Map<Integer, String> mapdate = getMapAnswers(session);
		if (!MapEx.isEmpty(mapdate)) {
			for (Entry<Integer, String> entry : mapdate.entrySet()) {
				if (StrEx.isEmpty(entry.getValue()))
					continue;
				curLen++;
			}
		}

		int num = maxLen >= curLen ? maxLen - curLen : 0;
		int timers = MapEx.getInt(pars, "timers");
		String url = MapEx.getString(pars, "url");
		if (StrEx.isEmptyTrim(url))
			url = "client/saveAnswers";

		String status = getStatus4Examing(session);
		if (!StrEx.isEmptyTrim(status)) {
			url = "client/home";
		}

		modelMap.put("num", num);
		modelMap.put("curExamLen", maxLen);
		modelMap.put("timers", timers);
		modelMap.put("url", url);
		return "client/examing/jugde4examing";
	}

	/*** 记录考试题 **/
	static void recordQues4Cust(Customer customer, int questionid, int catalog,
			boolean isRight) {
		if (customer == null)
			return;

		int customerid = customer.getId();

		Recordques4exam en = Recordques4examEntity.getByCustomidQuestionid(
				customerid, questionid);
		if (en == null) {
			en = Recordques4exam.newRecordques4exam(0, customerid, questionid,
					catalog, isRight ? 0 : 1, DateEx.nowDate());
			en.insert();
		} else {
			if (isRight) {
				en.setNumError(0);
			} else {
				int nm = en.getNumError();
				en.setNumError(nm + 1);
			}
			en.update();
		}
	}

	/*** 统计计算考试 **/
	private Map reckon4Exam(Customer customer, int costTimeMs,
			Map<Integer, String> mapAnswer, Map<Integer, Optquestion> mapQues,
			Map<Integer, Examcatalog> mapExamCatalog) {
		Map result = new HashMap();
		// 计算分数(得分)
		int scoreAll = 0;
		int avecorrectrate = 0;

		// 用于记录试卷目录结构
		Map<Integer, Map<String, Object>> tmpMap = new HashMap<Integer, Map<String, Object>>();

		// 计算正确率的
		int lensAll = 0;
		int lensAll4Right = 0;

		// 记录试卷所有题的得分
		Map<Integer, Integer> mapScores = new HashMap<Integer, Integer>();

		for (Entry<Integer, Optquestion> entry : mapQues.entrySet()) {
			int curOptId = entry.getKey();
			Optquestion optques = entry.getValue();
			int catalogType = optques.getType();

			// 该次考试的题总数
			lensAll++;

			// 首次只去看单选，多选，判断
			if (catalogType > 3)
				continue;

			String answer = "";
			int curScore = 0;
			if (!MapEx.isEmpty(mapAnswer)) {
				if (mapAnswer.containsKey(curOptId))
					answer = mapAnswer.get(curOptId);
			}
			String rightStr = optques.getRight_2();
			boolean isRight = rightStr.equals(answer);
			int score = 0;

			String serial = "一";
			String bigtypes = "单项选择题";

			Examcatalog examCatalog = null;
			if (!MapEx.isEmpty(mapExamCatalog)) {
				int catalogId = optques.getExamcatalogid();
				examCatalog = mapExamCatalog.get(catalogId);
			}

			if (examCatalog != null) {
				score = examCatalog.getEveryScore();
				serial = examCatalog.getSerial();
				bigtypes = examCatalog.getBigtypes();
			}

			// 记录考题
			recordQues4Cust(customer, curOptId, catalogType, isRight);

			Map<String, Object> mapCata = tmpMap.get(catalogType);
			if (mapCata == null) {
				mapCata = new HashMap<String, Object>();
			}
			int lensAll2 = MapEx.getInt(mapCata, "lensAll");
			int lensRight = MapEx.getInt(mapCata, "lensRight");
			int scoreAll2 = MapEx.getInt(mapCata, "scoreAll");
			int scoreRight = MapEx.getInt(mapCata, "scoreRight");

			lensAll2++;
			scoreAll2 += score;
			if (isRight) {
				lensRight++;
				scoreRight += score;
				lensAll4Right++;
				scoreAll += score;
				curScore = score;
			}

			mapCata.put("everScore", score);
			mapCata.put("lensAll", lensAll2);
			mapCata.put("lensRight", lensRight);
			mapCata.put("scoreAll", scoreAll2);
			mapCata.put("scoreRight", scoreRight);
			mapCata.put("type", catalogType);
			mapCata.put("serial", serial);
			mapCata.put("bigtypes", bigtypes);

			tmpMap.put(catalogType, mapCata);
			mapScores.put(curOptId, curScore);
		}

		// 计算-统计-单\多正确率
		List<Map<String, Object>> reckon = ListEx.valueToList(tmpMap);

		avecorrectrate = 0;
		if (lensAll > 0) {
			avecorrectrate = lensAll4Right * 100 / lensAll;
		}
		result.put("scoreAll", scoreAll);
		result.put("avg", avecorrectrate);
		result.put("reckon", reckon);
		result.put("reckonMap", tmpMap);
		result.put("mapScores", mapScores);
		result.put("lens4exam", lensAll);
		result.put("lens4right", lensAll4Right);
		return result;
	}

	@RequestMapping("/saveAnswers")
	public String saveAnswers(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map mapPars = Svc.getMapAllParams(request);
		int examid = MapEx.getInt(mapPars, "examid");
		int preExamId = 0;
		Exam exam = getExam(session);
		if (exam != null) {
			preExamId = exam.getId();
		}

		if (examid == 0) {
			examid = preExamId;
			if (examid == 0)
				return "redirect:home";
		}

		if (preExamId != examid) {
			exam = ExamEntity.getByKey(examid);
		}

		if (exam == null)
			return "redirect:home";

		Map<Integer, Optquestion> mapQues = getMapQuestions(session);

		if (MapEx.isEmpty(mapQues))
			return "redirect:home";

		Customer customer = Utls.getCustomer(session);
		int custid = Utls.getCustomerId(session);
		Date nowDate = DateEx.nowDate();
		// 单位是毫秒
		int costTimeMs = MapEx.getInt(mapPars, "timers") * 1000;

		Map<Integer, String> mapAnswer = getMapAnswers(session);
		Map<Integer, Examcatalog> mapECatalog = getMapExamCatalogs(session);

		Map map4Reckon = reckon4Exam(customer, costTimeMs, mapAnswer, mapQues,
				mapECatalog);
		List reckon = MapEx.getList(map4Reckon, "reckon");
		Map reckonMap = MapEx.getMap(map4Reckon, "reckonMap");
		int scoreAll = MapEx.getInt(map4Reckon, "scoreAll");
		int avecorrectrate = MapEx.getInt(map4Reckon, "avg");
		int lens4exam = MapEx.getInt(map4Reckon, "lens4exam");
		int lens4right = MapEx.getInt(map4Reckon, "lens4right");

		Map mapScoreExam = MapEx.getMap(map4Reckon, "mapScores");
		session.setAttribute(SessionKeys.MapScore4Exam, mapScoreExam);

		PageKExam pageExam = KExamController.getKExam(session);
		boolean isHasPageKExam = (pageExam != null && pageExam.kind != null);
		int kindid = 0;
		int courseid = 0;
		int lhubid = 0;
		if (isHasPageKExam) {
			kindid = pageExam.kind.getId();
			courseid = pageExam.kind.getCoursid();
			lhubid = pageExam.kind.getLhubid();
		}

		Map map2UI = new HashMap();
		if (customer != null) {
			Recordanswer recordanswer = getExamRecord(session);
			if (recordanswer == null) {
				recordanswer = RecordanswerEntity.getByExamidCustomerid(examid,
						custid);
			}

			byte[] anwers = Utls.map2Bytes(mapAnswer);
			byte[] catalog = Utls.map2Bytes(reckonMap);
			byte[] scores4ques = Utls.map2Bytes(mapScoreExam);

			if (recordanswer == null) {
				recordanswer = Recordanswer.newRecordanswer(0, examid,
						exam.getName(), custid, customer.getName(), scoreAll,
						avecorrectrate, nowDate, 1, 0, anwers, nowDate,
						costTimeMs, catalog, exam.getExamtypeid(), scores4ques,
						kindid, lens4exam, lens4right, courseid, lhubid);
				recordanswer.insert();
			} else {
				recordanswer.setAnwers(anwers);
				recordanswer.setScore(scoreAll);
				recordanswer.setAvecorrectrate(avecorrectrate);
				recordanswer.setLasttime(nowDate);
				recordanswer.setCostTimes(costTimeMs);
				recordanswer.setCatalog(catalog);
				recordanswer.changeNum(1);
				recordanswer.setScore4ques(scores4ques);
				recordanswer.setLens4exam(lens4exam);
				recordanswer.setLens4right(lens4right);
				recordanswer.update();
			}

			if (isHasPageKExam) {
				Itms4day itms4day = Itms4day.newItms4day(0, custid, kindid,
						avecorrectrate, nowDate);
				itms4day.insert();
			}

			map2UI.putAll(recordanswer.toBasicMap());

			session.setAttribute(SessionKeys.CurExamRecord, recordanswer);
		} else {
			map2UI.put("nmExam", exam.getName());
			map2UI.put("costTimes", costTimeMs);
			map2UI.put("score", scoreAll);
			map2UI.put("kindid", kindid);
			map2UI.put("avecorrectrate", avecorrectrate);
		}

		/*** 考完成绩分数 **/
		modelMap.addAttribute("examed", map2UI);
		modelMap.addAttribute("reckon", reckon);
		if (custid > 0) {
			return "client/exam4subject/examedScore";
		} else {
			return "client/exam4subject/examedScore4Test";
		}
	}

	@RequestMapping("/saveAnswers4ITMS")
	public String saveAnswers4ITMS(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map<Integer, Optquestion> mapQues = getMapQuestions(session);
		if (MapEx.isEmpty(mapQues))
			return "redirect:home";

		Customer customer = Utls.getCustomer(session);

		Map mapPars = Svc.getMapAllParams(request);
		// 单位是毫秒
		int costTimeMs = MapEx.getInt(mapPars, "timers") * 1000;

		Map<Integer, String> mapAnswer = getMapAnswers(session);
		Map<Integer, Examcatalog> mapECatalog = getMapExamCatalogs(session);

		Map map4Reckon = reckon4Exam(customer, costTimeMs, mapAnswer, mapQues,
				mapECatalog);
		List reckon = MapEx.getList(map4Reckon, "reckon");
		int scoreAll = MapEx.getInt(map4Reckon, "scoreAll");
		int avecorrectrate = MapEx.getInt(map4Reckon, "avg");
		Map mapScoreExam = MapEx.getMap(map4Reckon, "mapScores");
		session.setAttribute(SessionKeys.MapScore4Exam, mapScoreExam);

		// 计算分数(得分)
		Map<String, Object> examMap = new HashMap<String, Object>();
		examMap.put("nmExam", "ITMS 考试");
		examMap.put("costTimes", costTimeMs);
		examMap.put("score", scoreAll);
		examMap.put("avecorrectrate", avecorrectrate);

		/*** 考完成绩分数 **/
		modelMap.addAttribute("reckon", reckon);
		modelMap.addAttribute("examed", examMap);
		return "client/examed/examedScore4ITMS";
	}

	/*** 取得纠错界面用着弹出界面 **/
	@RequestMapping("/correct4Alert")
	public String correct4Alert(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Utls.getMapAllParams(request);
		int questid = MapEx.getInt(mapPars, "questid");
		modelMap.addAttribute("questid", questid);
		return "client/correct4alert";
	}

	/*** 题出错反馈 **/
	@RequestMapping("/correct4Feedback")
	public void correct4Feedback(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map result = new HashMap();
		Map pars = Svc.getMapAllParams(request);
		int questid = MapEx.getInt(pars, "questid");
		if (questid <= 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "出现了异常！");
			Utls.writeAndClose(response, result);
			return;
		}

		String descr = MapEx.getString(pars, "descr");
		if (StrEx.isEmptyTrim(descr)) {
			result = Utls.tipMap(result, Utls.Status_Erro, "没有反馈内容！");
			Utls.writeAndClose(response, result);
			return;
		}

		Map<Integer, Optquestion> mapQues = getMapQuestions(session);

		Optquestion question = null;
		if (mapQues != null) {
			question = mapQues.get(questid);
		}

		if (question == null) {
			question = OptquestionEntity.getByKey(questid);
		}

		if (question == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "该题不存在,Question标识:"
					+ questid);
			Utls.writeAndClose(response, result);
			return;
		}

		Customer enCust = Utls.getCustomer(session);
		int customerid = 0;
		String custname = "";
		if (enCust != null) {
			custname = enCust.getName();
			customerid = enCust.getId();
		}

		int lhubid = 0;
		int examid = question.getExamid();
		Exam exam = ExamEntity.getByKey(examid);
		if (exam != null) {
			lhubid = exam.getLhubid();
		}

		Errorfeedback newEn = Errorfeedback.newErrorfeedback(0, examid,
				exam.getName(), questid, question.getType(), question.getGid(),
				customerid, custname, descr, lhubid, 0, DateEx.nowDate());
		newEn.insert();

		result = Utls.tipMap(result, Utls.Status_Success, "成功");
		Utls.writeAndClose(response, result);
	}

	/*** 取得考试花费考币界面弹出界面 **/
	@RequestMapping("/costKbi4Alert")
	public String costKbi2Examing4Alert(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Utls.getMapAllParams(request);
		String name = MapEx.getString(mapPars, "name");
		modelMap.addAttribute("name", name);
		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			modelMap.addAttribute("hasKbi", 0);
		} else {
			modelMap.addAttribute("hasKbi", customer.getKbiUse());
		}
		int ndKbi = getNdKbi4Examing(session);
		modelMap.addAttribute("ndKbi", ndKbi);
		return "client/examing/tip4examing";
	}

	/*** jsop判断考试花费考币 **/
	@RequestMapping("/judgeCostKbi4Examing")
	public void judgeCostKbi4Examing(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Map result = new HashMap();
		// 体验用户,必须加，用着判断是否付款了的
		if (Utls.getIsTestExam(session)) {
			session.setAttribute(SessionKeys.Status4Examing, 1);
			result = Utls.tipMap(result, Utls.Status_Success, "Okey");
			Utls.writeAndClose(response, result);
			return;
		}

		Customer customer = Utls.getCustomer(session);
		if (customer == null) {
			result = Utls.tipMap(result, Utls.Status_Erro, "Session过时！");
			Utls.writeAndClose(response, result);
			return;
		}

		int ndKbi = getNdKbi4Examing(session);
		if (ndKbi == 0) {
			result = Utls.tipMap(result, Utls.Status_Erro, "出现异常，考币消耗为零！");
			Utls.writeAndClose(response, result);
			return;
		}

		if (ndKbi > customer.getKbiUse()) {
			result = Utls.tipMap(result, Utls.Status_Erro, "考币不足！");
			Utls.writeAndClose(response, result);
			return;
		}

		customer.changeKbiUse(-ndKbi);
		customer.update();

		Map mapPars = Utls.getMapAllParams(request);
		String name = MapEx.getString(mapPars, "name");
		String cont = "花费" + ndKbi + "考币做" + name;
		Utls.recordKbi(customer, 2, ndKbi, cont);

		// 必须加，用着判断是否付款了的
		session.setAttribute(SessionKeys.Status4Examing, 1);

		result = Utls.tipMap(result, Utls.Status_Success, "Okey");
		Utls.writeAndClose(response, result);
	}

	/*** 处理主观评分 **/
	static void handleSubject(HttpSession session, int optid, int catalogType,
			int selfScore) {
		Recordanswer recordanswer = getExamRecord(session);
		if (recordanswer == null)
			return;
		Customer customer = Utls.getCustomer(session);

		// 计算分数(得分)
		int scoreAll = recordanswer.getScore();
		int avecorrectrate = 0;

		// 计算正确率的
		int lensAll = recordanswer.getLens4exam();
		int lensAll4Right = recordanswer.getLens4right();

		Map<Integer, Optquestion> mapQues = getMapQuestions(session);
		Optquestion enQust = mapQues.get(optid);
		Map<Integer, Examcatalog> mapECatalog = getMapExamCatalogs(session);
		Examcatalog enEC = mapECatalog.get(enQust.getExamcatalogid());
		int score = enEC.getEveryScore();
		String serial = enEC.getSerial();
		String bigtypes = enEC.getBigtypes();

		boolean isRight = selfScore > 0;

		byte[] catalog = recordanswer.getCatalog();
		Map map = Utls.bytes2Map(catalog);
		Map mapCata = MapEx.getMap(map, catalogType);
		if (mapCata == null) {
			mapCata = new HashMap();
		}
		int lensAll2 = MapEx.getInt(mapCata, "lensAll");
		int lensRight = MapEx.getInt(mapCata, "lensRight");
		int scoreAll2 = MapEx.getInt(mapCata, "scoreAll");
		int scoreRight = MapEx.getInt(mapCata, "scoreRight");

		lensAll2++;
		scoreAll2 += score;
		if (isRight) {
			lensRight++;
			scoreRight += selfScore;
			lensAll4Right++;
			scoreAll += selfScore;
		}

		mapCata.put("everScore", score);
		mapCata.put("lensAll", lensAll2);
		mapCata.put("lensRight", lensRight);
		mapCata.put("scoreAll", scoreAll2);
		mapCata.put("scoreRight", scoreRight);
		mapCata.put("type", catalogType);
		mapCata.put("serial", serial);
		mapCata.put("bigtypes", bigtypes);

		map.put(catalogType, mapCata);
		catalog = Utls.map2Bytes(map);

		if (lensAll > 0) {
			avecorrectrate = lensAll4Right * 100 / lensAll;
		}

		Map<Integer, Integer> mapScore = getMapScore4Exam(session);
		mapScore.put(optid, selfScore);
		byte[] btScore = Utls.map2Bytes(mapScore);

		recordanswer.setScore4ques(btScore);
		recordanswer.setScore(scoreAll);
		recordanswer.setCatalog(catalog);
		recordanswer.setLens4right(lensAll4Right);
		recordanswer.setAvecorrectrate(avecorrectrate);

		recordanswer.update();
		session.setAttribute(SessionKeys.CurExamRecord, recordanswer);

		// 记录考题
		recordQues4Cust(customer, optid, catalogType, isRight);
	}

	/*** 考试数据统计(包含了主观、客观数据) **/
	@RequestMapping("/reckonExam")
	public String reckonExam(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Recordanswer recordanswer = getExamRecord(session);
		if (recordanswer == null)
			return "redirect:home";
		byte[] catalog = recordanswer.getCatalog();
		Map map = Utls.bytes2Map(catalog);
		List reckon = ListEx.valueToList(map);
		/*** 考完成绩分数 **/
		modelMap.addAttribute("examed", recordanswer.toBasicMap());
		modelMap.addAttribute("reckon", reckon);
		return "client/examed/examedScore";
	}

	/*** 试卷考试错题重做 **/
	@RequestMapping("/redoErrors")
	public String redoErrors(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		Exam exam = getExam(session);
		if (exam == null) {
			return "redirect:home";
		}
		int examid = exam.getId();
		// 考试时间
		int examTime = exam.getExamtime();
		String name = exam.getName();
		name = PStr.str(name, ":错题重做");
		modelMap.addAttribute("name", name);
		initQues(session, modelMap, 4, examid, examTime);
		return "client/examing/examing";
	}

	/*** 重新做题-做其他的题 **/
	@RequestMapping("/reExaming")
	public String reExaming(HttpServletRequest request,
			HttpServletResponse response, ModelMap modelMap, HttpSession session) {
		return "redirect:historyTopics";
	}

	/*** 前台界面-取得弹出框-提交问题 **/
	@RequestMapping("/getPutQuesHtml")
	public String getPutQuesHtml(HttpServletRequest request,
			HttpServletResponse response, HttpSession session, ModelMap modelMap) {
		Map mapPars = Utls.getMapAllParams(request);
		int questid = MapEx.getInt(mapPars, "questid");
		Map<Integer, Optquestion> mapQues = getMapQuestions(session);
		Optquestion viewCurOpt = mapQues.get(questid);
		String tag = "";
		if (viewCurOpt != null) {
			int examid = viewCurOpt.getExamid();
			Exam exam = ExamEntity.getByKey(examid);
			if (exam != null) {
				int productid = exam.getProductid();
				Product product = ProductEntity.getByKey(productid);
				if (product != null) {
					int courseid = product.getCoursesid();
					Adcourses course = AdcoursesEntity.getByKey(courseid);
					if (course != null) {
						tag = PStr.str(course.getNmMajor(), "-",
								course.getNmLevel(), "-", course.getNmSub());
					}
				}
			}
		}
		modelMap.put("tag", tag);
		return "client/putques4alert";
	}
}
