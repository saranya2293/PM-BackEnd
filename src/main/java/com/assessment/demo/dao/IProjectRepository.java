package com.assessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assessment.demo.model.Project;

@Repository
public interface IProjectRepository extends JpaRepository<Project, Long> {

	public final static String project_for_task = "Select p.* From Project_DB_TBL p join Task_DB_TBL t on p.project_id = t.project_id where t.task_id = :taskId";

	@Query(value = project_for_task, nativeQuery = true)
	public Project findProjectForTask(@Param("taskId") Long taskId);

}
