package com.example.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.TrainingRecord;
import com.example.domain.TrainingRecordsDate;
import com.example.repository.TrainingRecordRepository;

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
	
	/**
	 * @param limit ページあたりのレコード数
	 * @param page 表示するページ
	 */
	public List<TrainingRecordsDate> findByPage(Integer limit, Integer page){
		List<TrainingRecordsDate> trainingRecordsDateList = new ArrayList<TrainingRecordsDate>();
		Integer offset = limit * (page - 1); // SQLのOFFSETのインデックス指定のため
		List<Date> trainingDateList = repository.findTrainingDateByPage(limit, offset);
		trainingDateList.forEach(date -> {
			trainingRecordsDateList.add(
					new TrainingRecordsDate(date, repository.findRecordsByDate(date)));
		});
		return trainingRecordsDateList;
	}
}
