package com.web.blog.dao.test;

import com.web.blog.model.test.Phone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Integer> {}