package com.web.blog.service.portfolio;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import com.web.blog.dao.portfolio.FileDAO;
import com.web.blog.dao.portfolio.PTagDao;
import com.web.blog.dao.portfolio.PortfolioDao;
import com.web.blog.dao.tag.TagDao;
import com.web.blog.model.BasicResponse;
import com.web.blog.model.join.PortfolioTag;
import com.web.blog.model.portfolio.PTag;
import com.web.blog.model.portfolio.PTagCreateRequest;
import com.web.blog.model.portfolio.Portfolio;
import com.web.blog.model.portfolio.PortfolioRequest;
import com.web.blog.model.portfolio.PortfolioUpdateRequest;
import com.web.blog.model.portfolio.UploadFile;
import com.web.blog.model.portfolio.PortfolioTags;
import com.web.blog.model.portfolio.PortfolioTagsFiles;
import com.web.blog.model.tag.Tag;
import com.web.blog.model.tag.TagPortfolioTagResponse;
import com.web.blog.property.FileUploadProperties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service("portfolioService")
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    PortfolioDao portfolioDao;

    @Autowired
    PTagDao ptagDao;

    @Autowired
    TagDao tagDao;

    // @Autowired
    // private PortfolioTagDao portfolioTagDao;
    @Autowired
    FileDAO fileDAO;

    private Path fileLocation;

    public PortfolioServiceImpl(FileUploadProperties prop) {
        this.fileLocation = Paths.get(prop.getUploadDir()).toAbsolutePath().normalize();
    }

    // 요청과 응답
    final BasicResponse result = new BasicResponse();
    ResponseEntity<BasicResponse> response = null;

    @Override
    public ResponseEntity<BasicResponse> readAll(final String uid) {

        List<Portfolio> list = portfolioDao.findPortfolioByUid(uid);
        List<PortfolioTags> ptsList = new ArrayList<>();

        for (Portfolio p : list) {
            List<PortfolioTag> ptList = p.getPortfolioTags();
            List<Tag> tagList = new ArrayList<>();
            for (PortfolioTag pt : ptList) {
                Tag tag = pt.getTag();
                tagList.add(tag);
            }
            ptsList.add(PortfolioTags.builder(p.getPid(), p.getUid(), p.getTitle(), p.getStartDate(), p.getStartDate(),
                    p.getContents(), tagList).build());
        }

        if (!list.isEmpty()) {
            result.status = true;
            result.data = "포트폴리오 리스트 성공";
            result.object = ptsList;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "포트폴리오 리스트 실패(비어있거나 실패)";
            result.object = null;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
            return response;
        }
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> getTagAll(String uid) {

        System.out.println(uid);
        List<Portfolio> list = portfolioDao.findPortfolioByUid(uid);
        HashSet<TagPortfolioTagResponse> hs = new HashSet<>();

        // 해시셋에 넣고 중복제거.
        for (Portfolio i : list) {
            List<PortfolioTag> portfolioTags = i.getPortfolioTags();
            for (PortfolioTag j : portfolioTags) {
                hs.add(TagPortfolioTagResponse.builder().tid(j.getTag().getTid()).tag_Name(j.getTag().getTagName())
                        .state(false).build());

                System.out.println(j.getTag());
            }
            System.out.println();
        }

        /*
         * Iterator<Tag> It = hs.iterator(); while(It.hasNext()){
         * System.out.println(It.next()); }
         */

        if (!list.isEmpty()) {
            result.status = true;
            result.data = "태그 리스트 성공";
            result.object = hs;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "태그 리스트 실패";
            result.object = null;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
            return response;
        }

        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> createPortfolio(PortfolioRequest request) {
        try {
            final Portfolio opt = portfolioDao.save(request.toEntity());
            result.status = true;
            result.data = "포트폴리오 생성 성공";
            result.object = opt;
        } catch (final Exception e) {
            result.status = false;
            result.data = "포트폴리오 생성 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> updatePortfolio(final PortfolioUpdateRequest updaterequest) {
        try {
            final Portfolio opt = portfolioDao.save(updaterequest.toEntity());
            result.status = true;
            result.data = "포트폴리오 수정 성공";
            result.object = opt;
        } catch (final Exception e) {
            result.status = false;
            result.data = "포트폴리오 수정 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> deletePortfolio(int pid) {
        try {
            Portfolio portfolio = portfolioDao.findPortfolioByPid(pid);

            // System.out.println(fileLocation);
            // 프로젝트에 포함되어있는 파일 삭제
            // - pid가 pid인 파일리스트를 불러와서 for문으로 filename으로 파일스토리지에서 삭제하고 db에서 삭제
            List<UploadFile> fileList = portfolio.getFiles();
            for (UploadFile uploadFile : fileList) {
                String fileName = uploadFile.getFileName();
                int fileId = uploadFile.getId();
                File file = new File(fileLocation + "\\" + fileName);
                System.out.println(file.toString());
                if (file.exists()) {
                    if (file.delete()) {
                        // 지워졌으면 db에서 파일의 정보를 지워야해
                        fileDAO.deleteById(fileId);
                    }
                }
            }
            // 프로젝트와 연결되어있는 태그연결 삭제 - cascading

            // 삭제할 프로젝트만 연결되어있던(연결된 프로젝트가 없는) 태그 삭제 - 아직 유지
            // 프로젝트 삭제
            portfolioDao.deleteById(pid);
            result.status = true;
            result.data = "포트폴리오 삭제 성공";
            result.object = null;
        } catch (final Exception e) {
            result.status = false;
            result.data = "포트폴리오 삭제 실패";
            result.object = null;
        }
        response = new ResponseEntity<>(result, HttpStatus.OK);
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> readOne(int pid) {

        Portfolio p = portfolioDao.findPortfolioByPid(pid);

        List<Tag> tagList = new ArrayList<>();
        List<PortfolioTag> ptList = p.getPortfolioTags();
        for (PortfolioTag pt : ptList) {
            Tag tag = pt.getTag();
            tagList.add(tag);
        }

        PortfolioTagsFiles ptf = PortfolioTagsFiles.builder().pid(p.getPid()).uid(p.getUid()).title(p.getTitle())
                .start_date(p.getStartDate()).end_date(p.getEndDate()).contents(p.getContents()).tag(tagList)
                .files(p.getFiles()).build();
        System.out.println(p.getFiles());

        if (ptf != null) {
            result.status = true;
            result.data = "pid가 " + pid + "인 포트폴리오의 정보입니다.";
            result.object = ptf;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
        } else {
            result.status = false;
            result.data = "포트폴리오 실패(비어있거나 실패)";
            result.object = null;
            response = new ResponseEntity<BasicResponse>(result, HttpStatus.OK);
            return response;
        }
        return response;
    }

    @Override
    public ResponseEntity<BasicResponse> createPTag(PTagCreateRequest request) throws Exception {

        // p_tag 테이블에서 pid, uid 가 같으면 중복
        final Optional<PTag> ptagOpt = ptagDao.getTagByPidAndTid(request.getPid(), request.getTid());

        if (ptagOpt.isPresent()) {
            result.status = false;
            result.data = "포트폴리오 태그 생성 실패(중복, 실패)";
            result.object = ptagOpt.get().toResEntity();// 중복되면 그놈 쓰세요! -> 중복되면 추가할 수 없음
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            PTag resultOpt = ptagDao.save(request.toEntity());

            result.status = true;
            result.data = "포트폴리오 태그 생성 성공";
            result.object = resultOpt.toResEntity(); // 중복안되서 새로 만들어진 애

            response = new ResponseEntity<>(result, HttpStatus.OK);
            return response;
        }

        return response;
    }
}
