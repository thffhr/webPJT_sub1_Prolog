package com.web.blog.dao.apply;

import java.util.List;

import com.web.blog.model.apply.ApplyTerm;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyTermDao extends JpaRepository<ApplyTerm, Integer> {

    List<ApplyTerm> findAllByOrderByApTermDesc();

}