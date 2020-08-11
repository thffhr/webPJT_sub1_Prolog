package com.web.blog.dao.temp;


import com.web.blog.model.apply.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempDao extends JpaRepository<ApplyTerm, Integer> {
}