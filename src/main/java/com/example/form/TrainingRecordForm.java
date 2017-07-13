package com.example.form;

import java.math.BigDecimal;
import java.sql.Date;

import lombok.Data;

@Data
public class TrainingRecordForm {

	private Date date;
	private Integer[] id;
	private Integer[] trainingId;
	private BigDecimal[] weight;
	private Integer[] repetition;
	private Integer[] setCount;
}
