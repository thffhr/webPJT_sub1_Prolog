package com.web.blog.service.temp;


import java.util.*;

import com.web.blog.dao.temp.TempDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.temp.Temp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("tempService")
public class TempServiceImpl implements TempService {

    @Autowired
    TempDao tempDao;

    // 요청과 응답
    BasicResponse result = new BasicResponse();
    ResponseEntity<BasicResponse> response = null;

    @Override
    public ResponseEntity<BasicResponse> findAll() {
        
        List<Temp> obj = tempDao.findAll();
        List<Temp> temp = obj;

        if (obj != null ) {
            for(Temp t : obj){
                System.out.println(t);
            }
            result.status = true;
            result.data = "기간 가져오기 성공";
            result.object = temp;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
        }  else {
            result.status = false;
            result.data = "기간 가져오기 실패";
            result.object = null;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
          
        }
        return response;
    }

 

}