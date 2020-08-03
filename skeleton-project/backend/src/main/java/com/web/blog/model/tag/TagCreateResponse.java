package com.web.blog.model.tag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class TagCreateResponse {

    @Id
    @Column(name = "tid")
    private int tid;

    @Column(name = "tag_name", nullable = false)
    private String tagName;

    @Builder
    public TagCreateResponse(int tid, String tagName) {
        this.tid = tid;
        this.tagName = tagName;
    }

}
