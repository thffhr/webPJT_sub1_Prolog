package com.web.blog.controller.Apply;

import javax.validation.Valid;

import com.web.blog.model.BasicResponse;
import com.web.blog.model.apply.ApplyCreateRequest;
import com.web.blog.model.apply.ApplyUpdateRequest;
import com.web.blog.service.apply.ApplyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class ApplyController {

    @Autowired
    ApplyService applyService;

    @PostMapping("/apply")
    @ApiOperation(value = "새로운 지원목록을 생성")
    public ResponseEntity<BasicResponse> create(@Valid @RequestBody ApplyCreateRequest applyCreateRequest) {
        return applyService.createApply(applyCreateRequest);
    }

    @GetMapping("/apply")
    @ApiOperation(value = "지원목록 리스트를 리턴")
    public ResponseEntity<BasicResponse> readAll(@RequestParam String uid) {
        return applyService.readAllApply(uid);
    }

    @GetMapping("/apply/{apid}")
    @ApiOperation(value = "지원목록 하나를 리턴")
    public ResponseEntity<BasicResponse> readOne(@RequestParam String uid, @PathVariable int apid) {
        return applyService.readOneApply(uid, apid);
    }

    @PutMapping("/apply")
    @ApiOperation(value = "지원목록 수정")
    public ResponseEntity<BasicResponse> update(@RequestBody ApplyUpdateRequest applyUpdateRequest) {
        return applyService.updateApply(applyUpdateRequest);
    }

    @DeleteMapping("/apply")
    @ApiOperation(value = "지원목록 삭제")
    public ResponseEntity<BasicResponse> delete(@RequestParam String uid, @RequestParam int apid) {
        return applyService.deleteApply(uid, apid);
    }
}