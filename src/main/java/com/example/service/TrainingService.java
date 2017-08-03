package com.example.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Training;
import com.example.domain.TrainingRecord;
import com.example.repository.TrainingRecordRepository;
import com.example.repository.TrainingRepository;
import com.example.web.TrainingRecordForm;

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
		
	public void insertRecordForm(TrainingRecordForm form){
		List<TrainingRecord> recordList = this.formToRecordList(form);
		recordList.forEach(record -> this.insertRecord(record));
		return;
	}
	
	public void updateRecordForm(TrainingRecordForm form){
		List<TrainingRecord> recordList = this.formToRecordList(form);
		recordList.forEach(record -> {
			if(record.getId() != null){
				this.updateRecord(record);
			}else{
				this.insertRecord(record);
			}
		});
		return;
	}
	
	public List<TrainingRecord> formToRecordList(TrainingRecordForm form){
		List<TrainingRecord> recordList = new ArrayList<TrainingRecord>();
		for(int i = 0; i < form.getTrainingId().length; i++) {
			TrainingRecord record = new TrainingRecord();
			record.setDate(form.getDate());
			if(form.getId().length != 0){
				record.setId(form.getId()[i]);
			};
			record.setTrainingId(form.getTrainingId()[i]);
			record.setWeight(form.getWeight()[i]);
			record.setRepetition(form.getRepetition()[i]);
			record.setSetCount(form.getSetCount()[i]);
			recordList.add(record);
		};
		return recordList;
	}
	
	public Integer insertRecord(TrainingRecord record){
		return recordRepository.insertRecord(record);
	}
	
	public Integer updateRecord(TrainingRecord record){
		return recordRepository.updateRecord(record);
	}
	
	public void deleteById(Integer id) {
		recordRepository.deleteById(id);
	}
	
	public Integer deleteByDate(Date date) {
		return recordRepository.deleteByDate(date);
	}
}
