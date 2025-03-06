package com.ritam.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ritam.entity.SemesterDetailsEntity;
import com.ritam.model.SemesterDetails;
import com.ritam.repository.SemesterRepository;

@Service
public class SemesterServiceImpl implements ISemesterService {
	
	@Autowired
	private SemesterRepository repo;
	
	@Override
	public void saveDetails(SemesterDetails details) {
			SemesterDetailsEntity entity=new SemesterDetailsEntity();
			BeanUtils.copyProperties(details, entity);
			repo.save(entity);
	}
	
	
	@Override
	public List<SemesterDetails> fetchAllDetails() {
	    List<SemesterDetailsEntity> entities = repo.findAll();
	    
	    if (entities == null || entities.isEmpty()) { 
	        return List.of();  
	    }

	    return entities.stream().map(entity -> {
	        SemesterDetails details = new SemesterDetails();
	        BeanUtils.copyProperties(entity, details);
	        return details;
	    }).toList();
	}

	
	
	@Override
	public Float calculateCGPA() {
	    List<SemesterDetailsEntity> entities = repo.findAll();
	    if (entities.isEmpty()) {
	        return 0.0f;
	    }
	    float totalSGPA = 0;
	    for (SemesterDetailsEntity entity : entities) {
	        totalSGPA += entity.getSgpa();
	    }

	    return totalSGPA / entities.size(); 
	}
	
	
	@Override
	public SemesterDetails getSemesterById(Integer id) {
	    SemesterDetailsEntity entity = repo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Semester not found"));
	    SemesterDetails details = new SemesterDetails();
	    BeanUtils.copyProperties(entity, details);
	    return details;
	}

	@Override
	public void updateSemester(Integer id, SemesterDetails updatedDetails) {
	    SemesterDetailsEntity entity = repo.findById(id)
	        .orElseThrow(() -> new RuntimeException("Semester not found"));
	    
	    entity.setSemNo(updatedDetails.getSemNo());
	    entity.setNoOfTheoryPaper(updatedDetails.getNoOfTheoryPaper());
	    entity.setNoOfPracticalPaper(updatedDetails.getNoOfPracticalPaper());
	    entity.setSgpa(updatedDetails.getSgpa());

	    repo.save(entity);
	}

	
	@Override
	public void deleteSemester(Integer semNo) {
	    repo.deleteById(semNo);
	}
}
