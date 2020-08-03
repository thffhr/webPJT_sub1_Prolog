package com.web.blog.dao.test;

import java.util.Optional;

import com.web.blog.model.test.Member;
import com.web.blog.model.test.MemberPhone;
import com.web.blog.model.test.Phone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPhoneRepository extends JpaRepository<MemberPhone, Integer>{

    Optional<MemberPhone> getMemberPhoneByMemberAndPhone(Member member, Phone phone);
}
