package com.web.blog.controller.temp;

import com.web.blog.model.BasicResponse;
import com.web.blog.service.temp.TempService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
public class TempController {

    @Autowired
    TempService tempService;

    @GetMapping("/temp")
    @ApiOperation(value = "유저의 전체 경험 목록")
    public ResponseEntity<BasicResponse> findAll() {
        return tempService.findAll();
    }


    

}
