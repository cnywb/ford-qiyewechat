package com.ford.qiye.common;

import java.util.List;

/**
 * 分页实现类
 * Created by wanglijun on 16/8/7.
 */
public class Pagination<T> {

    private List<T> data;  // 分页数据

    private Long total;    // 总记录数

    private Integer pageIndex;         // 当前页码,第几页

    private Integer pageSize;       // 每页显示的记录数,每页显示多少条数据

    private Integer totalPage;      // 总页数

    private Integer startIndex;     // 开始索引

    private Integer endIndex;       // 结束索引

    private Integer indexCount = 10;// 显示的索引数目,如:10的话， 则显示1-10， 2-11


    public Pagination(List<T> data, Long total,Integer pageIndex, Integer pageSize) {
        this.data = data;
        this.total = total;
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;

        // 根据总记录数和每页显示数计算总页数(totalRecord+pageSize->totalPage)
        totalPage =Integer.parseInt (String.valueOf(this.total / this.pageSize));
        totalPage = (this.total % pageSize == 0) ? totalPage : (totalPage + 1);
        // 根据索引数目，当前页，总页数计算开始索引和结束索引(indexCount+pageNo+totalPage->startIndex+endIndex)
        startIndex = indexCount/2;
        startIndex = pageSize - (indexCount%2 == 0 ? (startIndex - 1) : startIndex);
        endIndex = pageSize + indexCount/2;
        // 1 <= startIndex < pageNo < endIndex <= totalPage
        // startIndex = pageNo - indexCount/2
        // endIndex = pageNo + indexCount/2
        if(startIndex < 1) {
            startIndex = 1;
            if(totalPage >= indexCount) {
                endIndex = indexCount;
            }  else {
                endIndex =totalPage;
            }
        }
        if(endIndex > totalPage) {
            endIndex = totalPage;
            if(endIndex > indexCount) {
                startIndex = endIndex - indexCount + 1;
            } else {
                startIndex = 1;
            }
        }

    }




    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }


    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getIndexCount() {
        return indexCount;
    }

    public void setIndexCount(Integer indexCount) {
        this.indexCount = indexCount;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }
}
