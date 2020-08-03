package com.web.blog.model.experience;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.web.blog.model.join.PortfolioTag;
import com.web.blog.model.tag.Tag;

import lombok.*;

@Getter
@NoArgsConstructor
public class ExperienceFindTagResponse{

  
    private int exid;

    private String uid;

    private String title;

    @Column(name ="start_date")
    private Date startdate;

    @Column(name ="end_date")
    private Date enddate;

    private String contents;
    private List<Tag> tags;

    @Builder
    public ExperienceFindTagResponse(int exid, String uid, String title, Date startdate, Date enddate, String contents, List<Tag> tags){
        this.exid = exid;
        this.uid = uid;
        this.title = title;
        this.startdate = startdate;
        this.enddate = enddate;
        this.contents = contents;
        this.tags = tags;
    }
}
