package com.ritam.model;

import lombok.Data;

@Data
public class SemesterDetails {
    private Integer semNo;
    private Integer noOfTheoryPaper;
    private Integer noOfPracticalPaper;
    private Float sgpa;
}
