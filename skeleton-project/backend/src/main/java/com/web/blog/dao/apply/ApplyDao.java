package com.web.blog.dao.apply;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.apply.Apply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplyDao extends JpaRepository<Apply, Integer> {

    List<Apply> findApplyByUid(String uid);

    Optional<Apply> findApplyByUidAndApid(String uid, int apid);

}