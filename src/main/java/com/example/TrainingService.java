package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TrainingService {

	@Autowired
	TrainingRepository repository = new TrainingRepository();
	
	public List<Training> findAll(){
		return repository.findAll();
	}
	
	public Training findById(Integer id){
		return repository.findById(id);
	}
}
