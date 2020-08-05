package com.web.blog.service.portfolio;

import java.util.Optional;

import com.web.blog.model.BasicResponse;
import com.web.blog.model.portfolio.UploadFile;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadDownloadService {

    public ResponseEntity<BasicResponse> storeFile(int pid, MultipartFile file);

    public ResponseEntity<BasicResponse> deleteFile(int id);

    public Resource loadFileAsResource(String fileName);

    public Iterable<UploadFile> getFileList();

    public Optional<UploadFile> getUploadFile(int id);
}