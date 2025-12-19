package com.example.quiz_1141013.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.quiz_1141013.request.QuizCreateReq;
import com.example.quiz_1141013.request.QuizUpdataReq;
import com.example.quiz_1141013.response.BasicRes;
import com.example.quiz_1141013.response.GetListRes;
import com.example.quiz_1141013.response.GetQuestionRes;
import com.example.quiz_1141013.service.QuizService;

import jakarta.validation.Valid;

@CrossOrigin
@RestController
public class QuizController {

	@Autowired
	private QuizService quizService;

	@PostMapping("quiz/create")
	public BasicRes create(@RequestBody @Valid QuizCreateReq req) throws Exception {
		return quizService.create(req);
	}

	@PostMapping("quiz/update")
	public BasicRes update(@RequestBody @Valid QuizUpdataReq req) throws Exception {
		return quizService.update(req);
	}

	@GetMapping("quiz/getAll")
	public GetListRes getAll() {
		return quizService.getAll();
	}

	@GetMapping("quiz/get_fillter_data")
	public GetListRes getAll(//
			@RequestParam("keyword") String keyword, //
			@RequestParam("startDate") LocalDate startDate, //
			@RequestParam("endDate") LocalDate endDate) {
		return quizService.getAll(keyword, startDate, endDate);
	}

	/* http://localhost:8080/quiz/get_questions */
	@GetMapping("quiz/get_questions")
	public GetQuestionRes getQuestionByQuizId(@RequestParam("quizId") int quizId) throws Exception {
		return quizService.getQuestionByQuizId(quizId);
	}
}
