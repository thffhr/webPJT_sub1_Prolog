package com.web.blog.service.apply;

import java.util.List;
import java.util.Optional;

import com.web.blog.dao.apply.ApplyDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.apply.Apply;
import com.web.blog.model.apply.ApplyCreateRequest;
import com.web.blog.model.apply.ApplyUpdateRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("applyService")
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    ApplyDao applyDao;

    BasicResponse result = new BasicResponse();
    ResponseEntity<BasicResponse> response = null;

    @Override
    public ResponseEntity<BasicResponse> createApply(ApplyCreateRequest applyCreateRequest) {
        try {
            if (applyCreateRequest.getApCompany().equals(null)) {
                result.status = false;
                result.data = "회사명은 필수 입력값입니다.";
                result.object = null;
            } else {
                Apply apply = applyDao.save(applyCreateRequest.toReqEntity());
                result.status = true;
                result.data = "지원 추가 성공";
                result.object = apply;
            }
        } catch (final Exception e) {
            result.status = false;
            result.data = "지원 추가 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> readAllApply(String uid) {
        try {
            List<Apply> list = applyDao.findApplyByUid(uid);
            if (list.isEmpty()) {
                result.status = false;
                result.data = "지원 내역 없음";
                result.object = null;
            } else {
                result.status = false;
                result.data = "지원 내역 리스트";
                result.object = list;
            }

        } catch (Exception e) {
            result.status = false;
            result.data = "지원 내역 조회 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> readOneApply(String uid, int apid) {
        try {
            Optional<Apply> applyOpt = applyDao.findApplyByUidAndApid(uid, apid);
            if (applyOpt.isPresent()) {
                result.status = false;
                result.data = "지원 내역 조회 성공";
                result.object = applyOpt;
            } else {
                result.status = false;
                result.data = "지원 내역 없음";
                result.object = null;
            }
        } catch (Exception e) {
            result.status = false;
            result.data = "지원 내역 조회 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> updateApply(ApplyUpdateRequest applyUpdateRequest) {
        try {
            if (applyUpdateRequest.getApCompany().equals("")) {
                result.status = false;
                result.data = "회사명은 필수 입력값입니다.";
                result.object = null;
            } else {
                Apply apply = applyDao.save(applyUpdateRequest.toReqEntity());
                result.status = true;
                result.data = "지원 추가 성공";
                result.object = apply;
            }
        } catch (Exception e) {
            result.status = false;
            result.data = "지원 내역 수정 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> deleteApply(String uid, int apid) {
        try {
            Optional<Apply> applyOpt = applyDao.findApplyByUidAndApid(uid, apid);
            if (applyOpt.isPresent()) {
                applyDao.delete(applyOpt.get());
                result.status = false;
                result.data = "지원 내역 삭제 성공";
                result.object = applyOpt;
            } else {
                result.status = false;
                result.data = "지원 내역 없음";
                result.object = null;
            }
        } catch (Exception e) {
            // e.printStackTrace();
            result.status = false;
            result.data = "지원 내역 삭제 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }
}