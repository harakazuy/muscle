package com.example.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Training;
import com.example.web.TrainingForm;

@Repository
public class TrainingRepository {
	private String trainingsTable = "trainings";

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
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
	
	public List<Training> findByPage(Integer limit, Integer offset) {
		String sql = "select id, training_name from " + trainingsTable;
		sql += " order by id";
		sql += "limit :limit offset :offset";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("limit", limit)
				.addValue("offset", offset);
		return template.query(sql, param, rowMapper);
	}
	
	public Training findById(Integer id){
		String sql = "";
		sql += "select * from " + trainingsTable + " where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Training training = template.queryForObject(sql, param, rowMapper);
		return training;
	}
	
	public Integer countAll() {
		String sql = "select count(*) from " + trainingsTable;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
	
	public Integer update(TrainingForm training) {
		String sql = "update " + trainingsTable;
		sql += " set training_name = :trainingName ";
		sql += "where id = :id";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("trainingName", training.getTrainingName())
				.addValue("id", training.getId());
		return template.update(sql, param);
	}
}
