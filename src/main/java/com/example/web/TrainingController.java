package com.example.web;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.domain.Training;
import com.example.domain.TrainingRecord;
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
	public String index(Model model){
		List<Training> trainingList = trainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return packagePath + "index";
	}
	
	@RequestMapping("/toRecord")
	public String toRecord(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return packagePath + "record";
	}
	
	@RequestMapping("/toEdit")
	public String toEdit(@RequestParam("date") Date date, Model model) {
		List<TrainingRecord> trainingRecords = trainingRecordsDateService.findByDate(date);
		model.addAttribute("date", date);
		model.addAttribute("trainingRecords", trainingRecords);
		List<Training> trainingList = trainingService.findAll();
		model.addAttribute("trainingList", trainingList);
		return packagePath + "edit";
	}
	
	@RequestMapping("/chart")
	public String chart(Model model){
		List<TrainingRecordsDate> trainingRecordsDateList = trainingRecordsDateService.findAll();
		model.addAttribute("trainingRecordsDateList", trainingRecordsDateList);
		return packagePath + "chart";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@Validated TrainingRecordForm form, BindingResult result, Model model){
		trainingService.insertRecordForm(form);
		model.addAttribute("isSuccess", true); // 成功(仮)
		return toRecord(model);
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Validated TrainingRecordForm form, BindingResult result, Model model){
		trainingService.updateRecordForm(form);
		return toRecord(model);
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	@ResponseBody
	public Integer delete(@RequestParam("id") Integer id, Model model){
		return trainingService.deleteById(id);
	}
	
	@RequestMapping(value="/deleteByDate")
	public String deleteByDate(@RequestParam("date") Date date, Model model){
		trainingService.deleteByDate(date);
		return toRecord(model);
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
