package com.web.blog.controller.portfolio.fileTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Autowired
    ResourceLoader resourceLoader;

    @GetMapping("/testtest")
    @ApiOperation(value = "특정 포트폴리오의 값을 보여줌")
    public void test(HttpServletRequest request, HttpServletResponse res) {
        Resource resource = null;
        String fileLen = null;
        if (environment != null) {
            // 파일들이 저장되어있는 경로(properties)
            String path = environment.getProperty("file.upload-dir");
            // 압축파일관련 클래스
            ZipOutputStream zout = null;
            // 생성할 압축파일 이름
            String zipName = path + "jihye" + ".zip";
            // String zipName = path + {닉네임_} + {지원한 회사 이름} + ".zip";
            // System.out.println(zipName);

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

            /* 생성한 압축파일 다운로드 받을 수 있는 uri 전송 */
            resource = resourceLoader.getResource(path + zipName);
            File file;
            try {
                file = resource.getFile();
                fileLen = file.length() + "";

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=\"" + resource.getFilename() + "\"");
            res.setStatus(HttpServletResponse.SC_OK);
            ResponseEntity.ok().body(resource);

            // List<String> fileNames = service.getFileName();

            // System.out.println("############# file size ###########" + fileNames.size());

            // try (ZipOutputStream zippedOut = new
            // ZipOutputStream(response.getOutputStream())) {
            // for (String file : fileNames) {
            // FileSystemResource resource = new FileSystemResource(file);

            // ZipEntry e = new ZipEntry(resource.getFilename());
            // // Configure the Zip entry, the properties of the file
            // e.setSize(resource.contentLength());
            // e.setTime(System.currentTimeMillis());
            // // etc.
            // zippedOut.putNextEntry(e);
            // // And the content of the resource:
            // StreamUtils.copy(resource.getInputStream(), zippedOut);
            // zippedOut.closeEntry();
            // }
            // zippedOut.finish();
            // } catch (Exception e) {
            // // Exception handling goes here
            // }
        } else {
            System.out.println("프로퍼티 널널ㅇㄴ알너런ㅇ러넝러너런ㅇ런ㅇ");
        }
    }

    @GetMapping("/downloadZip")
    public void downloadFile(HttpServletResponse response) {

        if (environment == null) {
            return;
        }
        String path = environment.getProperty("file.upload-dir");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=download.zip");
        response.setStatus(HttpServletResponse.SC_OK);

        String[] fileName = { "C:\\" + path + "ryan_20200804165439.jpg" };
        System.out.println(fileName[0]);

        List<String> fileNames = new ArrayList<>(Arrays.asList(fileName));

        System.out.println("############# file size ###########" + fileNames.size());

        // try (ZipOutputStream zout = new ZipOutputStream(response.getOutputStream()))
        // {
        // for (String file : fileNames) {
        // FileSystemResource resource = new FileSystemResource(file);

        // ZipEntry e = new ZipEntry(resource.getFilename());
        // // Configure the Zip entry, the properties of the file
        // e.setSize(resource.contentLength());
        // e.setTime(System.currentTimeMillis());
        // // etc.
        // zippedOut.putNextEntry(e);
        // // And the content of the resource:
        // StreamUtils.copy(resource.getInputStream(), zippedOut);
        // zippedOut.closeEntry();
        // }
        // zippedOut.finish();
        // } catch (Exception e) {
        // // Exception handling goes here
        // }
    }

}