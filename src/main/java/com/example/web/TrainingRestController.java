package com.example.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.domain.TrainingRecordsDate;
import com.example.service.TrainingRecordsDateService;
import com.example.service.TrainingService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/rest")
public class TrainingRestController {

	@Autowired
	TrainingService trainingService;
	
	@Autowired
	TrainingRecordsDateService trainingRecordsDateService;

//　ページネーション
	@RequestMapping(value="/totalPages", method=RequestMethod.POST)
	public Integer totalPages(@RequestParam("limit") Integer limit, Model model){
		return trainingService.countTotalPages(limit);
	}
	
	@RequestMapping(value="/pagination", method=RequestMethod.POST)
	public String pagination(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page, Model model){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		List<TrainingRecordsDate> list =  trainingRecordsDateService.findByPage(limit, page);
		try{
			json = mapper.writeValueAsString(list);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
// チャート
	@RequestMapping(value="/chart", method=RequestMethod.POST)
	public String chart(@RequestParam("id") Integer id, Model model){
		String json = null;
		return json;
	}
}
