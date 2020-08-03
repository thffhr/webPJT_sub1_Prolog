package com.web.blog.service.portfolio;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import com.web.blog.dao.portfolio.FileDAO;
import com.web.blog.dao.portfolio.PortfolioDao;
import com.web.blog.exception.FileDownloadException;
import com.web.blog.exception.FileUploadException;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.portfolio.Portfolio;
import com.web.blog.model.portfolio.UploadFile;
import com.web.blog.property.FileUploadProperties;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadDownloadService {
    private Path fileLocation;

    @Autowired
    private FileDAO fileDAO;

    @Autowired
    private PortfolioDao portfolioDao;

    public FileUploadDownloadService(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileLocation);
        } catch (Exception e) {
            throw new FileUploadException("파일을 업로드할 디렉토리를 생성하지 못했습니다.", e);
        }
    }

    public ResponseEntity<BasicResponse> storeFile(int pid, MultipartFile file) {
        ResponseEntity<BasicResponse> response = null;
        BasicResponse result = new BasicResponse();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        Date nowdate = new Date();
        String now = formatter.format(nowdate);

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String fileName = FilenameUtils.getBaseName(file.getOriginalFilename()) + "_"
        + now + "." + extension;
        System.out.println(fileName);

        Portfolio portfolio = portfolioDao.findPortfolioByPid(pid);

        Optional<UploadFile> fileExist = fileDAO.findUploadFileByFileName(fileName);

        try {
        // 파일이 존재하는지 확인한다.
        if (fileExist.isEmpty() || portfolio == null) {
        // 파일명에 부적합 문자가 있는지 확인한다.
        if (fileName.contains(".."))
        throw new FileUploadException("파일명에 부적합 문자가 포함되어 있습니다. " + fileName);

        Path targetLocation = this.fileLocation.resolve(fileName);

        Files.copy(file.getInputStream(), targetLocation,
        StandardCopyOption.REPLACE_EXISTING);

        UploadFile uploadFile = new UploadFile(portfolio, fileName, file.getSize(),
        file.getContentType());
        fileDAO.save(uploadFile);

        result.data = "success";
        result.status = true;
        result.object = "저장이 완료 되었습니다.";
        response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
        } else {
        result.data = "fail";
        result.status = false;
        result.object = "이미 존재하는 파일명이거나 존재하지 않는 프로젝트";
        response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);

        }

        } catch (Exception e) {
        throw new FileUploadException("[" + fileName + "] 파일 업로드에 실패하였습니다. 다시시도하십시오.", e);
        }
        return response;
    }

    @Transactional
    public ResponseEntity<BasicResponse> deleteFile(int id) {
        ResponseEntity<BasicResponse> response = null;
        BasicResponse result = new BasicResponse();
        // id를 이용해 저장된 파일의 이름이 뭔지 알려줘야할거같은데
        String fileName = fileDAO.findUploadFileById(id).getFileName();
        // 파일스토리지에서 파일을 지워야해
        File file = new File(fileLocation + "\\" + fileName);
        System.out.println(file.toString());
        if (file.exists()) {
            if (file.delete()) {
                // 지워졌으면 db에서 파일의 정보를 지워야해
                fileDAO.deleteById(id);

                result.data = "true";
                result.object = "deleted";
                result.status = true;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                result.data = "false";
                result.object = "파일은 삭제, 디비엔 존재";
                result.status = false;
                response = new ResponseEntity<>(result, HttpStatus.OK);
            }
        } else {
            result.data = "false";
            result.object = "파일이 존재하지 않아 삭제할 수 없음";
            result.status = false;
            response = new ResponseEntity<>(result, HttpStatus.OK);
        }
        // 둘 다 지웠으면 ok, 둘중하나라도 지워지지 않으면......다시 살려?//흐으으으음

        return response;

    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.");
            }
        } catch (MalformedURLException e) {
            throw new FileDownloadException(fileName + " 파일을 찾을 수 없습니다.", e);
        }
    }

    public Iterable<UploadFile> getFileList() {
        Iterable<UploadFile> iterable = fileDAO.findAll();

        if (null == iterable) {
            throw new FileDownloadException("업로드 된 파일이 존재하지 않습니다.");
        }

        return iterable;
    }

    public Optional<UploadFile> getUploadFile(int id) {
        Optional<UploadFile> uploadFile = fileDAO.findById(id);

        if (null == uploadFile) {
            throw new FileDownloadException("해당 아이디[" + id + "]로 업로드 된 파일이 존재하지 않습니다.");
        }
        return uploadFile;
    }

}