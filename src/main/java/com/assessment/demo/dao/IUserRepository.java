package com.assessment.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assessment.demo.model.User;

@Repository
public interface IUserRepository extends JpaRepository<User, Long>{

}
