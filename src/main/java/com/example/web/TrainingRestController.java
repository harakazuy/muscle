package com.example.web;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Training;
import com.example.domain.TrainingRecord;
import com.example.domain.TrainingRecordsDate;
import com.example.service.TrainingRecordService;
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
	TrainingRecordService trainingRecordService;
	
	@Autowired
	TrainingRecordsDateService trainingRecordsDateService;
	
// レコード追加・更新
	@RequestMapping(value="/upsert", method=RequestMethod.POST)
	public String upsert(@Validated TrainingRecordForm form, BindingResult result){
		return trainingService.upsertRecordForm(form);
	}
	
// レコード削除
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public Integer delete(@RequestParam("id") Integer id){
		return trainingService.deleteById(id);
	}
	
//　ページネーション
	// 総ページ数
	@RequestMapping(value="/totalPages", method=RequestMethod.POST)
	public Integer totalPages(@RequestParam("limit") Integer limit, Model model){
		return trainingService.countTotalPages(limit);
	}
	
	// ページ取得
	@RequestMapping(value="/pagination", method=RequestMethod.POST)
	public String pagination(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page){
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
	
// レコードリスト
	@RequestMapping(value="/records", method=RequestMethod.POST)
	public String record(@RequestParam("date") Date date){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		List<TrainingRecord> recordList = trainingRecordsDateService.findByDate(date);
		try{
			json = mapper.writeValueAsString(recordList);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
// チャート
	@RequestMapping(value="/chart", method=RequestMethod.POST)
	public String chart(@RequestParam("trainingId") Integer trainingId){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		List<TrainingRecord> list = trainingRecordService.findByTrainingId(trainingId);
		try{
			json = mapper.writeValueAsString(list);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
// トレーニング取得
	@RequestMapping(value="/trainings", method=RequestMethod.GET)
	public String trainings(){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		List<Training> list = trainingService.findAll();
		try{
			json = mapper.writeValueAsString(list);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
// 筋トレメニューのページネーション TODO:履歴と共通化できる？
	// 筋トレメニューの総ページ数
	@RequestMapping(value="/totalMenuPages", method=RequestMethod.POST)
	public Integer totalMenuPages(@RequestParam("limit") Integer limit, Model model){
		return trainingService.countTotalMenuPages(limit);
	}
	
	// 筋トレページ取得
	@RequestMapping(value="/menuPagination", method=RequestMethod.POST)
	public String menuPagination(@RequestParam("limit") Integer limit, @RequestParam("page") Integer page){
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		List<Training> list =  trainingService.findByPage(limit, page);
		try{
			json = mapper.writeValueAsString(list);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	// 筋トレ更新
	@RequestMapping(value="/updateTraining", method=RequestMethod.POST)
	public String updateTraining(@Validated TrainingForm form, BindingResult result){
		return trainingService.updateTraining(form);
	}
}
