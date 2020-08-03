package com.web.blog.service.portfolio;

import java.util.Optional;

import com.web.blog.model.portfolio.UploadFile;
import com.web.blog.property.FileUploadProperties;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface FileUploadDownloadServiceImpl {

    public void FileUploadDownloadService(FileUploadProperties prop);

    public UploadFile storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public Iterable<UploadFile> getFileList();

    public Optional<UploadFile> getUploadFile(int id);
}