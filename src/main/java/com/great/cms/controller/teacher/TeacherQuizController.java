package com.great.cms.controller.teacher;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.great.cms.entity.Quiz;
import com.great.cms.entity.QuizRegistration;
import com.great.cms.entity.Teacher;
import com.great.cms.entity.Teaches;
import com.great.cms.security.utils.UserUtil;
import com.great.cms.service.QuizRegistrationService;
import com.great.cms.service.QuizService;
import com.great.cms.service.TeachesService;

@Controller
@RequestMapping("/teacher/quiz")
public class TeacherQuizController {

	@Autowired
	TeachesService teachesService;
	@Autowired
	QuizService quizService;
	@Autowired
	QuizRegistrationService quizRegService;

	@RequestMapping("/question/create")
	public String showQuizQuestions(Model model) {
		System.out.println("teacher/quiz/question/create");
		return "teacher/quiz/quiz_question";
	}

	@RequestMapping("/dashboard")
	public String showStdQuizDashboard(Principal principal, Model uiModel) {
		System.out.println("/teacher/quiz/dashboard");
		Teacher teacher = UserUtil.getInstance().getTeacher(principal);
		List<Quiz> quizList = new ArrayList<Quiz>();
		List<Teaches> teachesList = teachesService.findByInstructorId(teacher);
		// System.out.println("TeachesList Found!");
		for (Teaches teaches : teachesList) {
			List<Quiz> quizes = quizService.getQuizes(teaches);
			// System.out.println("Quiz List Found!");
			// System.out.println("Teaches " +
			// teaches.getTeachesId()+": Quiz-Count: " + quizes.size());
			if (!quizes.isEmpty())
				quizList.addAll(quizes);
		}
		uiModel.addAttribute("quizList", quizList);
		return "teacher/quiz/teach_quiz_dashboard";
	}

	@RequestMapping("/students/{quizId}")
	public String showStdQuizRegPage(Principal principal,
			@PathVariable Long quizId, Model uiModel) {
		System.out.println("/teacher/quiz/student/" + quizId);
		Quiz quiz = new Quiz(quizId);
		List<QuizRegistration> quizRegList = quizRegService
				.getQuizRegistrationsByQuiz(quiz);
		uiModel.addAttribute("quizRegList", quizRegList);
		uiModel.addAttribute("quizRegistration", new QuizRegistration());
		return "teacher/quiz/teach_quiz_students";
	}

	@RequestMapping("/students/approve")
	public String stdQuizRegApprove(Principal principal,
			QuizRegistration quizRegistration, Model uiModel,
			RedirectAttributes redirectAttr) {
		System.out.println("/teacher/quiz/students/approve" + quizRegistration);
		QuizRegistration savedQuizReg = quizRegService
				.getQuizRegistrationById(quizRegistration
						.getQuizRegistrationId());
		quizRegistration.setAttendTime(savedQuizReg.getAttendTime());
		quizRegistration.setSubmitTime(savedQuizReg.getSubmitTime());
		quizRegService.saveOrUpdate(quizRegistration);
		redirectAttr.addAttribute("quizId", quizRegistration.getQuizId()
				.getQuizId());
		return "redirect:/teacher/quiz/students/{quizId}";
	}
}
