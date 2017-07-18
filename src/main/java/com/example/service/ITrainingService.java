package com.example.service;

import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.domain.Training;
import com.example.web.TrainingRecordForm;

@Service
public interface ITrainingService {
	
	public List<Training> findAll();	
	public Training findById(Integer id);
	
	public Integer insertTrainingRecord(Object[] formInput);
	
	public void updateRecordForm(TrainingRecordForm form);
	
	public void deleteById(Integer id);
	
	public Integer deleteByDate(Date date);
}
