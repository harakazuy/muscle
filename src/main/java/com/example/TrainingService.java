package com.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.form.TrainingRecordForm;

@Service
public class TrainingService implements ITrainingService {
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
	
	public Integer insertTrainingRecord(Object[] formInput){
		return recordRepository.insertTrainingRecord(formInput);
	}
	
	public void updateRecordForm(TrainingRecordForm form){
		List<TrainingRecord> recordList = this.formToRecordList(form);
		recordList.forEach(record -> this.updateRecord(record));
		return;
	}
	
	public List<TrainingRecord> formToRecordList(TrainingRecordForm form){
		List<TrainingRecord> recordList = new ArrayList<TrainingRecord>();
		List<Integer> idList = Arrays.asList(form.getId());
		idList.forEach(id -> {
			TrainingRecord record = new TrainingRecord();
			Integer index = idList.indexOf(id);
			record.setId(id);
			record.setTrainingId(form.getTrainingId()[index]);
			record.setWeight(form.getWeight()[index]);
			record.setRepetition(form.getRepetition()[index]);
			record.setSetCount(form.getSetCount()[index]);
			recordList.add(record);
		});
		return recordList;
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
