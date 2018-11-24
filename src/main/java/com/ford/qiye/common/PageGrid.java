package com.ford.qiye.common;

public class PageGrid {

	/**当前页面大小*/
	private Integer pageSize=10;
	/**当前页面*/
	private Integer pageIndex=1;
	/**关键字*/
	private String keyWord;


	public PageGrid() {
	}

	public PageGrid(Integer pageSize, Integer pageIndex) {
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
	}

	/***
	 *
	 * @param keyWord
	 * @param pageIndex
	 * @param pageSize
     */

	public PageGrid(String keyWord, Integer pageIndex, Integer pageSize) {
		this.keyWord = keyWord;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
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


	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
}
