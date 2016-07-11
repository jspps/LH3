package com.learnhall.logic.model;

import java.util.List;
import java.util.Map;

import com.bowlong.util.ListEx;
import com.bowlong.util.NewCpWrList;
import com.bowlong.util.page.APage;
import com.learnhall.db.bean.Answer;
import com.learnhall.db.bean.Ask;
import com.learnhall.db.entity.AnswerEntity;
import com.learnhall.db.entity.AskEntity;

public class PageQuestionAnswer extends APage<Answer> {

	private static final long serialVersionUID = 1L;

	NewCpWrList<Answer> listData = new NewCpWrList<Answer>();

	int quesId = 0;

	public void init(int questionid) {
		if (quesId == questionid){
			// return;
		}

		listData.clear();
		List<Answer> list = AnswerEntity.getByAskid(questionid);
		if (ListEx.isEmpty(list))
			return;
		int answerid = -1;
		Ask quesEn = AskEntity.getByKey(questionid);
		if (quesEn != null) {
			answerid = quesEn.getAnswerid();
		}
		if (answerid != -1) {
			Answer answer = AnswerEntity.getByKey(answerid);
			if (answer != null) {
				listData.add(answer);
			}
		}
		int len = list.size();
		for (int i = 0; i < len; i++) {
			Answer en = list.get(i);
			if (en.getId() != answerid) {
				listData.add(en);
			}
		}
	};

	@Override
	public int countAll(Map<String, Object> params) {
		return listData.size();
	}

	@Override
	public List<Answer> getList(Map<String, Object> params, int page, int pageSize) {
		return ListEx.getPageT(listData, page, pageSize);
	}

}
