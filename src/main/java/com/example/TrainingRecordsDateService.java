package com.example;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingRecordsDateService {

	@Autowired
	TrainingRecordRepository repository = new TrainingRecordRepository();
	
	public List<TrainingRecordsDate> findAll(){
		List<TrainingRecordsDate> trainingRecordsDateList = new ArrayList<TrainingRecordsDate>();
		List<Date> trainingDateList = repository.findTrainingDateAll();
		trainingDateList.forEach(date -> {
			trainingRecordsDateList.add(
					new TrainingRecordsDate(date, repository.findRecordsByDate(date)));
		});
		return trainingRecordsDateList;
	}
	
	public List<TrainingRecord> findByDate(Date date) {
		return repository.findRecordsByDate(date);
	}
}
