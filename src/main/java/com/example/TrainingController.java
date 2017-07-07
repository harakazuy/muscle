package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class TrainingController {

	@Autowired
	TrainingService trainingService = new TrainingService();
	
	@Autowired
	TrainingRecordsDateService trainingRecordsDateService = new TrainingRecordsDateService();
	
	@RequestMapping("/")
	public String index(Model model){
		List<Training> trainingList = trainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return "index";
	}
	
	@RequestMapping("/toRecord")
	public String toRecord(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return "record";
	}
}
