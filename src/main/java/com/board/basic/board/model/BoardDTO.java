package com.board.basic.board.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDTO {
    private int iboard;
    private int iuser;
    private int page;
    private int startIdx;
    private int rowCnt;
    private int searchType;
    private String searchText;


    public void setPage(int page){
        this.startIdx = (page-1)*rowCnt;
        this.page = page;
    }
}
