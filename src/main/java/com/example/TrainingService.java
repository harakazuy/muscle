package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

	@Autowired
	TrainingRepository repository = new TrainingRepository();
	
	@Autowired
	TrainingRecordRepository recordRepository = new TrainingRecordRepository();
	
	public List<Training> findAll(){
		return repository.findAll();
	}
	
	public Training findById(Integer id){
		return repository.findById(id);
	}
	
	public Integer insertTrainingRecord(Object[] formInput){
		return recordRepository.insertTrainingRecord(formInput);
	}
}
