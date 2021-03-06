package com.assessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.demo.model.Task;

@Repository
public interface ITaskRepository extends JpaRepository<Task, Long>{

}
