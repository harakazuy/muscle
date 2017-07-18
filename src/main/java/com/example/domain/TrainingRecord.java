package com.example.domain;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRecord {
	private Integer id;
	private Date date;
	private Integer trainingId;
	private String trainingName;
	private BigDecimal weight;
	private Integer repetition;
	private Integer setCount;
}
