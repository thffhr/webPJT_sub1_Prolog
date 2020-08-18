package com.web.blog.controller.portfolio.fileTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resources;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.net.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class httpservletTestController {

    @Autowired
    Environment environment;

    @GetMapping("/testtest")
    @ApiOperation(value = " ")
    public void test(HttpServletRequest request, HttpServletResponse res) {
        String fileLen = null;
        if (environment != null) {
            // 파일들이 저장되어있는 경로(properties)
            String path = environment.getProperty("file.upload-dir");
            // 압축파일관련 클래스
            ZipOutputStream zout = null;
            // 생성할 압축파일 이름
            String zipName = path + "jihye" + ".zip";
            // String zipName = path + {닉네임_} + {지원한 회사 이름} + ".zip";
            System.out.println(zipName);

            /* 압축파일 생성 */
            try {
                // zipName으로 새 ZipOutputStream 클래스를 생성하고
                zout = new ZipOutputStream(new FileOutputStream(zipName));
                // ?
                byte[] buf = new byte[1024];
                // 압축할 파일을 경로포함해서 FileInputStream 클래스로 생성한다.
                FileInputStream in = new FileInputStream(path + "ryan_20200804165439.jpg");// 압축대상 파일
                // 압축파일에 포함시키는 메서드
                zout.putNextEntry(new ZipEntry("ryan_20200804165439.jpg"));
                //
                int len;
                while ((len = in.read(buf)) > 0) {
                    zout.write(buf, 0, len);
                }
                zout.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (null != zout) {
                    try {
                        zout.close();
                    } catch (Exception e) {
                    }
                    zout = null;
                }
            }
        } else {
            System.out.println("프로퍼티 널널ㅇㄴ알너런ㅇ러넝러너런ㅇ런ㅇ");
        }
    }
}