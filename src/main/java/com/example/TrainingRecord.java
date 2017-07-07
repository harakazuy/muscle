package com.example;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingRecord {
	private Integer id;
	private String trainingName;
	private BigDecimal weight;
	private Integer repetition;
	private Integer setCount;
}
