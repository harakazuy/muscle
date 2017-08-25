package com.example.web;

import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.domain.Training;
import com.example.domain.TrainingRecordsDate;
import com.example.service.TrainingService;
import com.example.service.TrainingRecordsDateService;

@Controller
@RequestMapping("")
public class TrainingController {

	@Autowired
	TrainingService trainingService;
	
	@Autowired
	TrainingRecordsDateService trainingRecordsDateService;
	
	@ModelAttribute
	TrainingRecordForm setUpForm() {
		return new TrainingRecordForm();
	}
	
	private String packagePath = "trainings/";
	
	@RequestMapping("/")
	public String common(){
		return packagePath + "common";
	}
	
	@RequestMapping("/index")
	public String index(Model model){
		return packagePath + "index";
	}
	
	@RequestMapping("/record")
	public String record(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return packagePath + "record";
	}
	
	@RequestMapping("/edit")
	public String edit(Model model) {
		return packagePath + "edit";
	}
	
	@RequestMapping("/chart")
	public String chart(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return packagePath + "chart";
	}
	
	// TODO:非同期実装にしてRestControllerに移す
	@RequestMapping(value="/deleteByDate")
	public String deleteByDate(@RequestParam("date") Date date, Model model){
		trainingService.deleteByDate(date);
		return record(model);
	}
	
// ページの部品	
	@RequestMapping(value="/header")
	public String header(Model model){
		return packagePath + "header";
	}
	
	@RequestMapping(value="/navbar")
	public String navbar(Model model){
		return packagePath + "navbar";
	}
	
	@RequestMapping(value="/footer")
	public String footer(Model model){
		return packagePath + "footer";
	}
	
	@RequestMapping(value="/form")
	public String form(Model model){
		List<Training> trainingList = trainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return packagePath + "form";
	}
}
