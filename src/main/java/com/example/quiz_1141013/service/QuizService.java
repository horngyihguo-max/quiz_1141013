package com.example.quiz_1141013.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.example.quiz_1141013.constants.ResMessage;
import com.example.quiz_1141013.constants.Type;
import com.example.quiz_1141013.dao.QuestionDAo;
import com.example.quiz_1141013.dao.QuizDao;
import com.example.quiz_1141013.entity.Question;
import com.example.quiz_1141013.request.QuizCreateReq;
import com.example.quiz_1141013.request.QuizUpdataReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.GetListRes;
import com.example.quiz_1141013.response.GetQuestionRes;
import com.example.quiz_1141013.vo.Options;
import com.example.quiz_1141013.vo.QuestionVo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class QuizService {

	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private QuizDao quizDao;

	@Autowired
	private QuestionDAo questionDao;

	/* rollbackFor = Exception.class: 表示只要此方法發生了 Exception ，寫一半的資料都會回溯 */
	@Transactional(rollbackFor = Exception.class)
	public BasicRes create(QuizCreateReq req) throws Exception {
		BasicRes checkRes = check(req);
		/* 方法 check 的結果只會有2種結果，null 和 非 null(BasicRes)，非 null 的結果表示檢查有錯 */
		if (checkRes != null) {
			/* 把檢查有錯的結果直接return 出去 */
			return checkRes;
		}
		/* 新增問卷 */
		quizDao.addQuiz(req.getTitle(), req.getTitle(), req.getStartDate(), req.getEndDate(), req.isPublished());
		/* 取得最新的 quiz_id 編號 */
		int quizId = quizDao.getMaxId();
		/* 將quizId 寫進 DB */
		for (QuestionVo vo : req.getQuestionVoList()) {
			/* 要把 vo 中的 List<Options> 轉換成字串 */
			try {
				String OptionListStr = mapper.writeValueAsString(vo.getOptionList());
				questionDao.addQuestion(quizId, vo.getQuestionId(), vo.getQuestion(), vo.getType(), //
						vo.isRequired(), OptionListStr);

			} catch (Exception e) {
				throw e;
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	private BasicRes check(QuizCreateReq req) {
		/* 排除開始時間比結束時間晚 */
		if (req.getStartDate().isAfter(req.getEndDate()) || req.getStartDate().isBefore(LocalDate.now())) {
			return new BasicRes(ResMessage.DATE_ERROR.getCode(), ResMessage.DATE_ERROR.getMessage());
		}
		List<QuestionVo> voList = req.getQuestionVoList();
		for (QuestionVo vo : voList) {
			/* 排除非 3 種 type */
			if (!Type.checkType(vo.getType())) {
				return new BasicRes(ResMessage.TYPE_ERROR.getCode(), ResMessage.TYPE_ERROR.getMessage());
			}
			/* type是選擇題的時，選項至少要有一個 */
			if (Type.isChosenType(vo.getType())) {
				if (vo.getOptionList().size() < 1) {
					return new BasicRes(ResMessage.OPTIONS_SIZE_ERROR.getCode(), //
							ResMessage.OPTIONS_SIZE_ERROR.getMessage());
				}
			} else { // type 是簡答題，不能有選項
				if (!vo.getOptionList().isEmpty()) {
					return new BasicRes(ResMessage.OPTIONS_SIZE_ERROR.getCode(), //
							ResMessage.OPTIONS_SIZE_ERROR.getMessage());
				}
			}

		}
		return null;
	}

	@Transactional(rollbackFor = Exception.class)
	public BasicRes update(QuizUpdataReq req) throws Exception {
		/*
		 * 方法 check 中的參數資料型態是 QuizCreateReq， 對 QuizUpdateReq 來說是父類別， 若把子類別 QuizUpdateReq
		 * 當參數放到 check 中，其資料型態會自動轉型成父類別 QuizCreateReq， 即check((QuizCreateReq)req)，
		 * 這樣的結果差別只是在於子類別中的屬性 quizId 都會是預設值 0， 但不影響方法 check 的檢查，因為沒用到 quizId
		 */
		BasicRes checkRes = check(req);
		/* 方法 check 的結果只會有2種結果，null 和 非 null(BasicRes)，非 null 的結果表示檢查有錯 */
		if (checkRes != null) {
			/* 把檢查有錯的結果直接return 出去 */
			return checkRes;
		}
		/* 檢查 QuizId 和 QuestionVo 中的 quizId 是否一樣 */
		for (QuestionVo vo : req.getQuestionVoList()) {
			if (req.getQuizId() != vo.getQuizId()) {
				return new BasicRes(ResMessage.QUIZ_ID_MISMATCH.getCode(), //
						ResMessage.QUIZ_ID_MISMATCH.getMessage());
			}
		}
		/* 更新 quiz */
		int updateRes = quizDao.update(req.getQuizId(), req.getTitle(), req.getDescription(), //
				req.getStartDate(), req.getEndDate(), req.isPublished());
		System.out.println("updateRes = " + updateRes);
		/* 有找到 quizId 並更新成功(即使更新的資料與DB中的都一樣)，都會回傳 1(Where 條件帶的是PK) */
		if (updateRes != 1) {
			return new BasicRes(ResMessage.QUIZ_NOT_FOUND.getCode(), ResMessage.QUIZ_NOT_FOUND.getMessage());
		}
		/* 確定 quiz_id 有存在 --> 先刪問題，再新增問題 */
		questionDao.deleteByQuizId(req.getQuizId());
		/* 新增問題 */
		/* 將quizId 寫進 DB */
		System.out.println("QuizId = " + req.getQuizId());
		for (QuestionVo vo : req.getQuestionVoList()) {
			System.out.println("QuestionVo quizId = " + vo.getQuizId());
			/* 要把 vo 中的 List<Options> 轉換成字串 */
			try {
				String OptionListStr = mapper.writeValueAsString(vo.getOptionList());
				questionDao.addQuestion(vo.getQuizId(), vo.getQuestionId(), vo.getQuestion(), vo.getType(), //
						vo.isRequired(), OptionListStr);

			} catch (Exception e) {
				throw e;
			}
		}
		return new BasicRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage());
	}

	public GetListRes getAll() {
		return new GetListRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				quizDao.getAll());
	}

	public GetListRes getAll(String keyword, LocalDate startDate, LocalDate endDate) {
		/*
		 * 把 keyword 是 null(沒有輸入值) 或 空字串 或全空白字串 轉換成空字串 目的是後面在取資料時會使用 like %% ， %%
		 * 中間是空字串時，也是會撈全部
		 */
		if (!StringUtils.hasText(keyword)) {
			keyword = "";
		}
		if (startDate == null) {
			startDate = LocalDate.of(1970, 1, 1);
		}
		if (endDate == null) {
			endDate = LocalDate.of(3000, 12, 31);
		}
		return new GetListRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				quizDao.getAll(keyword, startDate, endDate));
	}

	public GetQuestionRes getQuestionByQuizId(int quizId) throws Exception {
		List<Question> list = questionDao.getByQuizId(quizId);
		List<QuestionVo> questionVoList = new ArrayList<>();
		/* 把 Question 中的每個字串 options 轉換成物件 Options */
		for (Question item : list) {
			/* 轉換 */
			try {
				List<Options> opList = mapper.readValue(item.getOptions(), new TypeReference<>() {
				});
				/* 把 Question 中的每個屬性值以及 opList ， set 到 QuestionVo 對應的屬性位置 */
				QuestionVo vo = new QuestionVo(quizId, item.getQuestionId(), item.getQuestion(), //
						item.getType(), item.isRequired(), opList);
				/* 把每個 vo 加到 questionVoList */
				questionVoList.add(vo);
			} catch (Exception e) {
				throw e;
			}
		}
		return new GetQuestionRes(ResMessage.SUCCESS.getCode(), ResMessage.SUCCESS.getMessage(), //
				questionVoList);
	}
}
