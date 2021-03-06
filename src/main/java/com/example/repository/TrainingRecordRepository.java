package com.example.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.TrainingRecord;

@Repository
public class TrainingRecordRepository {
	// テーブル
	private String recordsTable = "training_records";
	private String trainingsTable = "trainings";
	// クエリ
	private String insertSql = "insert into " + recordsTable + " (training_date, training_id, weight, repetition, set_count) "
								+ "values (:date, :trainingId, :weight, :repetition, :setCount)";
	private String updateSql = "update " + recordsTable + " set "
								+ "training_date = :date, training_id = :trainingId, weight = :weight, repetition = :repetition, set_count = :setCount "
								+ "where id = :id";
	
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<TrainingRecord> rowMapper = (rs, i) -> {
		TrainingRecord trainingRecord = new TrainingRecord();
		trainingRecord.setId(rs.getInt("id"));
		trainingRecord.setTrainingId(rs.getInt("training_id"));
		trainingRecord.setTrainingName(rs.getString("training_name"));
		trainingRecord.setWeight(rs.getBigDecimal("weight"));
		trainingRecord.setRepetition(rs.getInt("repetition"));
		trainingRecord.setSetCount(rs.getInt("set_count"));
		trainingRecord.setDate(rs.getDate("training_date"));
		return trainingRecord;
	};
	
	private static final RowMapper<Date> dateRowMapper = (rs, i) -> {
		Date date = rs.getDate("training_date");
		return date;
	};
	
	public List<TrainingRecord> findRecordsByDate(Date date) {
		String sql = "select a.id, training_id, training_name, weight, repetition, set_count, training_date from ";
		sql += recordsTable + " as a join " + trainingsTable + " as b ";
		sql += "on a.training_id = b.id where training_date = :date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		List<TrainingRecord> trainingRecords = template.query(sql, param, rowMapper);
		return trainingRecords;
	};
	
	public List<TrainingRecord> findByTrainingId(Integer trainingId) {
		String sql = "select a.id, training_id, training_name, weight, repetition, set_count, training_date from ";
		sql += recordsTable + " as a join " + trainingsTable + " as b ";
		sql += "on a.training_id = b.id where training_id = :trainingId order by training_date asc";
		SqlParameterSource param = new MapSqlParameterSource().addValue("trainingId", trainingId);
		return template.query(sql, param, rowMapper);
	};
	
	public List<Date> findTrainingDateAll() {
		String sql = "select training_date from " + recordsTable;
		sql += " group by training_date order by training_date desc";
		List<Date> trainingDateList = template.query(sql, dateRowMapper);
		return trainingDateList;
	};
	
	public List<Date> findTrainingDateByPage(Integer limit, Integer offset) {
		String sql = "select training_date from " + recordsTable;
		sql += " group by training_date order by training_date desc ";
		sql += "limit :limit offset :offset";
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("limit", limit)
				.addValue("offset", offset);
		List<Date> trainingDateList = template.query(sql, param, dateRowMapper);
		return trainingDateList;
	};
	
	public Integer upsertRecord(TrainingRecord record) {
		String sql = record.getId() == null ? insertSql : updateSql;
		return template.update(sql, generateRecordParam(record));
	}
	
	public SqlParameterSource generateRecordParam(TrainingRecord record) {
		SqlParameterSource param = new MapSqlParameterSource()
				.addValue("date", record.getDate())
				.addValue("trainingId", record.getTrainingId())
				.addValue("weight", record.getWeight())
				.addValue("repetition", record.getRepetition())
				.addValue("setCount", record.getSetCount())
				.addValue("id", record.getId());
		return param;
	}
	
	public Integer deleteById(Integer id) {
		String sql = "delete from " + recordsTable + " where id = :id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.update(sql, param);
	}
	
	public Integer deleteByDate(Date date) {
		String sql = "delete from " + recordsTable + " where training_date = :date";
		SqlParameterSource param = new MapSqlParameterSource().addValue("date", date);
		return template.update(sql, param);
	}
	
	public Integer countDates() {
		String sql = "select count(distinct training_date) from " + recordsTable;
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
