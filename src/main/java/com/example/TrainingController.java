package com.example;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.form.TrainingRecordForm;

@Controller
@RequestMapping("")
public class TrainingController {

	@Autowired
	ITrainingService iTrainingService;
	
	@Autowired
	TrainingRecordsDateService trainingRecordsDateService;
	
	@ModelAttribute
	TrainingRecordForm setUpForm() {
		return new TrainingRecordForm();
	}
	
	@RequestMapping("/")
	public String index(Model model){
		List<Training> trainingList = iTrainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return "index";
	}
	
	@RequestMapping("/toRecord")
	public String toRecord(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return "record";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(@RequestParam("date") Date date, Model model) {
		List<TrainingRecord> trainingRecords = trainingRecordsDateService.findByDate(date);
		model.addAttribute("date", date);
		model.addAttribute("trainingRecords", trainingRecords);
		List<Training> trainingList = iTrainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return "edit";
	}
		
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestParam("date") Date date,
			@RequestParam("trainingId") Integer trainingId, @RequestParam("weight") BigDecimal weight,
			@RequestParam("repetition") Integer repetition, @RequestParam("setCount") Integer setCount,
			Model model){
		Object[] formInput = {date, trainingId, weight, repetition, setCount};
		iTrainingService.insertTrainingRecord(formInput);
		return toRecord(model);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Validated TrainingRecordForm form, BindingResult result,Model model){
		iTrainingService.updateRecordForm(form);
		return toRecord(model);
	}
}
