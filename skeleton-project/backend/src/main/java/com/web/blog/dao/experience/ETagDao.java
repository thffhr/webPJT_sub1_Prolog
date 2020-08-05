package com.web.blog.dao.experience;

import com.web.blog.model.experience.ETag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ETagDao extends JpaRepository<ETag, Integer> {
    
    //ex_tag 테이블에서 중복된 태그 있는지 확인
    Optional<ETag> getTagByExidAndTid(int exid, int tid);

    //ex_tag 삭제
    void deleteByExidAndTid(@Param("exid") int exid, @Param("tid") int tid);


}