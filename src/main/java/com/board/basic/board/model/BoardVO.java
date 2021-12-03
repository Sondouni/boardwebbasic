package com.board.basic.board.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardVO {
    private int iboard;
    private String title;
    private String ctnt;
    private int writer;
    private String writerNm;
    private int hit;
    private String rdt;
    private String mdt;
}
