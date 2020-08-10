package com.web.blog.dao.temp;


import com.web.blog.model.temp.Temp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TempDao extends JpaRepository<Temp, Integer> {
}