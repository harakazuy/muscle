package com.example.domain;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRecordsDate {
	private Date date;
	private List<TrainingRecord> trainingRecords;
}
