package com.example.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Training;
import com.example.domain.TrainingRecord;
import com.example.repository.TrainingRecordRepository;
import com.example.repository.TrainingRepository;
import com.example.web.TrainingRecordForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TrainingService {
	@Autowired
	TrainingRepository repository;
	
	@Autowired
	TrainingRecordRepository recordRepository;
	
	public List<Training> findAll(){
		return repository.findAll();
	}
	
	public Training findById(Integer id){
		return repository.findById(id);
	}
	
	// TODO:TrainingRecordServiceに移動する
	// TODO:{レコード:返り値}でエラー判定してるのを綺麗にする
	public String upsertRecordForm(TrainingRecordForm form){
		List<TrainingRecord> recordList = this.formToRecordList(form);
		return this.upsertRecordList(recordList);
	}
	
	public List<TrainingRecord> formToRecordList(TrainingRecordForm form){
		List<TrainingRecord> recordList = new ArrayList<>();
		for(int i = 0; i < form.getTrainingId().length; i++) {
			TrainingRecord record = new TrainingRecord();
			record.setDate(form.getDate());
			record.setId(form.getId().length != 0 ? form.getId()[i] : null);
			record.setTrainingId(form.getTrainingId()[i]);
			record.setWeight(form.getWeight()[i]);
			record.setRepetition(form.getRepetition()[i]);
			record.setSetCount(form.getSetCount()[i]);
			recordList.add(record);
		};
		return recordList;
	}
	
	public String upsertRecordList(List<TrainingRecord> recordList) {
		List<Map<TrainingRecord, Integer>> resultMapList = new ArrayList<>();
		recordList.forEach(record -> {
			Map<TrainingRecord, Integer> resultMap = new LinkedHashMap<>();
			Integer result = this.upsertRecord(record);
			resultMap.put(record, result);
			resultMapList.add(resultMap);
		});
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try{
			json = mapper.writeValueAsString(resultMapList);
		}catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public Integer upsertRecord(TrainingRecord record) {
		return recordRepository.upsertRecord(record);
	}

	public Integer deleteById(Integer id) {
		return recordRepository.deleteById(id);
	}
	
	public Integer deleteByDate(Date date) {
		return recordRepository.deleteByDate(date);
	}
	
	public Integer countTotalPages(Integer limit){
		Integer dates = recordRepository.countDates();
		return (dates + limit - 1) / limit;
	}
}
