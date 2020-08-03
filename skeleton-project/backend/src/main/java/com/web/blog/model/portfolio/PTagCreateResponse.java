package com.web.blog.model.portfolio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.*;

@Entity
@Data
@NoArgsConstructor
public class PTagCreateResponse {

    @Id
    @Column(name = "ptid")
    private int ptid;

    @Column(name = "pid")
    private int pid;

    @Column(name = "tid")
    private int tid;

    @Builder
    public PTagCreateResponse(int ptid, int pid, int tid) {
        this.ptid = ptid;
        this.pid = pid;
        this.tid = tid;
    }

}
