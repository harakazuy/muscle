package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.TrainingRecord;
import com.example.repository.TrainingRecordRepository;
import com.example.repository.TrainingRepository;

@Service
public class TrainingRecordService {

	@Autowired
	TrainingRepository repository;
	
	@Autowired
	TrainingRecordRepository recordRepository;
	
	public List<TrainingRecord> findByTrainingId(Integer trainingId) {
		return recordRepository.findByTrainingId(trainingId);
	}
}
