package com.board.basic.board.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int page;
    private int startIdx;
    private int rowCnt;

    public void setPage(int page){
        this.page = page;
    }
}
