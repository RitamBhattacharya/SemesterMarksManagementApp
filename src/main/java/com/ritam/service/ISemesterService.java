package com.ritam.service;

import java.util.List;

import com.ritam.model.SemesterDetails;

public interface ISemesterService {
	
	public void saveDetails(SemesterDetails details);

	public List<SemesterDetails> fetchAllDetails();

	public Float calculateCGPA();
	
	public SemesterDetails getSemesterById(Integer id);
	
	public void updateSemester(Integer id, SemesterDetails updatedDetails);

}
