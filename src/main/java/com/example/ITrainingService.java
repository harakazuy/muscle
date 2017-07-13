package com.example;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.form.TrainingRecordForm;

@Service
public interface ITrainingService {
	
	public List<Training> findAll();	
	public Training findById(Integer id);
	
	public Integer insertTrainingRecord(Object[] formInput);
	
	public void updateRecordForm(TrainingRecordForm form);
}
