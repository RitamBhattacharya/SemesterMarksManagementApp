package com.ritam.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "semester_details_tbl")
@Data
public class SemesterDetailsEntity {
	@Id
	private Integer semNo;
	private Integer noOfTheoryPaper;
	private Integer noOfPracticalPaper;
	private Float sgpa;
}
