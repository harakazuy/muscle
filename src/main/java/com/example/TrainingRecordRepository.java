package com.example;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class TrainingRecordRepository {
	private String trainingRecordsTable = "training_records";
	private String trainingsTable = "trainings";
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<TrainingRecord> rowMapper = (rs, i) -> {
		TrainingRecord trainingRecord = new TrainingRecord();
		trainingRecord.setId(rs.getInt("id"));
		trainingRecord.setTrainingName(rs.getString("training_name"));
		trainingRecord.setWeight(rs.getBigDecimal("weight"));
		trainingRecord.setRepetition(rs.getInt("repetition"));
		trainingRecord.setSetCount(rs.getInt("set_count"));
		return trainingRecord;
	};
	
	public List<TrainingRecord> findRecordsByDate(Date date) {
		String sql = "select a.id, training_name, weight, repetition, set_count from ";
		sql += trainingRecordsTable + " as a join " + trainingsTable + " as b ";
		sql += "on a.training_id = b.id where training_date = :date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		List<TrainingRecord> trainingRecords = template.query(sql, param, rowMapper);
		return trainingRecords;
	};
	
	private static final RowMapper<Date> dateRowMapper = (rs, i) -> {
		Date date = rs.getDate("training_date");
		return date;
	};
	
	public List<Date> findTrainingDateAll() {
		String sql = "select training_date from " + trainingRecordsTable;
		sql += " group by training_date order by training_date desc";
		List<Date> trainingDateList = template.query(sql, dateRowMapper);
		return trainingDateList;
	};
	
	public Integer insertTrainingRecord(Object[] formInput) {
		String sql = "insert into " + trainingRecordsTable + " (training_date, training_id, weight, repetition, set_count) ";
		sql += "values (:date, :trainingId, :weight, :repetition, :setCount)";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("date", formInput[0])
				.addValue("trainingId", formInput[1])
				.addValue("weight", formInput[2])
				.addValue("repetition", formInput[3])
				.addValue("setCount", formInput[4]);
		return template.update(sql, param);
	};
}
