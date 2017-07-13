package com.example;

import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRepository {
	private String trainingsTable = "trainings";

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Training> rowMapper = (rs, i) -> {
		Training training = new Training();
		training.setId(rs.getInt("id"));
		training.setTrainingName(rs.getString("training_name"));
		return training;
	};
	
	public List<Training> findAll() {
		String sql = "";
		sql += "select * from " + trainingsTable;
		List<Training> trainingList = template.query(sql, rowMapper);
		return trainingList;
	}
	
	public Training findById(Integer id){
		String sql = "";
		sql += "select * from " + trainingsTable + " where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Training training = template.queryForObject(sql, param, rowMapper);
		return training;
	}
}
