package com.example;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseballTeamService {

	@Autowired
	BaseballTeamRepository repository = new BaseballTeamRepository();

	public List<Team> findAll(){
		return repository.findAll();
	}

	public Team findById(Integer id){
		return repository.findById(id);
	}
}
