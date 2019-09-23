package com.assessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.demo.model.ParentTask;

@Repository
public interface IParentTaskRepository extends JpaRepository<ParentTask, Long>{

}
