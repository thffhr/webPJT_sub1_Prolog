package com.web.blog.service.temp;

import com.web.blog.model.BasicResponse;
import com.web.blog.model.portfolio.PTagCreateRequest;
import com.web.blog.model.portfolio.PortfolioRequest;
import com.web.blog.model.portfolio.PortfolioUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface TempService {

    // 기간 가져오기
    ResponseEntity<BasicResponse> findAll();

}
