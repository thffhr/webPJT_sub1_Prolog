package com.web.blog.controller.portfolio;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import com.web.blog.model.BasicResponse;
import com.web.blog.model.portfolio.UploadFile;
import com.web.blog.service.portfolio.FileUploadDownloadService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

@RestController
public class FileUploadController {
    private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);

    @Autowired
    private FileUploadDownloadService service;

    @PostMapping("/uploadFile/{pid}")
    @ApiOperation(value = "폼태그의 파일 업로드 방식으로 파일을 업로드 할 수 있다.")
    public ResponseEntity<BasicResponse> uploadFile(@PathVariable int pid, @RequestParam("file") MultipartFile file) {

        return service.storeFile(pid, file);
    }

    // 프로젝트별로 파일리스트를 리턴 혹은 사용자별로 파일리스트를 리턴
    @GetMapping("/uploadFiles")
    @ApiOperation(value = "업로드 된 파일 리스트를 볼 수 있다.")
    public Iterable<UploadFile> getUploadFileList() {
        return service.getFileList();
    }

    @DeleteMapping("/deleteFile/{id}")
    @ApiOperation(value = "특정 파일을 db와 파일 스토리지에서 삭제한다.")
    public ResponseEntity<BasicResponse> deleteFile(@PathVariable int id) {

        return service.deleteFile(id);
    }

    // 파일이 없을 경우 없다는 리턴을 해줘야함
    @GetMapping("/uploadFile/{id}")
    @ApiOperation(value = "업로드 된 파일의 db내의 id(int)로 파일이 존재하는 지 확인 할 수 있다.")
    public Optional<UploadFile> getUploadFile(@PathVariable int id) {
        return service.getUploadFile(id);
    }

    // @PostMapping("/uploadMultipleFiles")
    // @ApiOperation(value = "파일들의 배열(list)로 여러 파일을 업로드 할 수 있다.")
    // public List<UploadFile> uploadMultipleFiles(@RequestParam("files")
    // MultipartFile[] files) {
    // return Arrays.asList(files).stream().map(file ->
    // uploadFile(file)).collect(Collectors.toList());
    // }

    // 파일 다운로드시 한글 깨지는 오류 수정
    @GetMapping("/downloadFile/{fileName}")
    @ApiOperation(value = "파일 이름으로 파일을 다운로드 할 수 있다.")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = service.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename())
                .body(resource);
    }
}