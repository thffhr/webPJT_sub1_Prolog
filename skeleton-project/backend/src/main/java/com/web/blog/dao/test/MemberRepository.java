package com.web.blog.dao.test;

import com.web.blog.model.test.Member;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer>{}
