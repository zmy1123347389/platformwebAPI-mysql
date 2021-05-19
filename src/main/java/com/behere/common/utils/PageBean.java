package com.behere.common.utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PageBean {
	private boolean flag = true;
	private List<?> list;
	private int recordCnt;
	public int pageSize = 10;
	private int pageNumber;
	private int firstPage = 1;
	private int prePage;
	private int nextPage;
	private int lastPage;
	private int curPage = 1;

	private Map<String, Object> paramsMap = new ConcurrentHashMap();

	public boolean isFlag() {
		return this.flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void pageResult(List<?> list, int recordCnt, int pageSize, int curPage) {
		this.list = list;
		this.recordCnt = recordCnt;
		this.pageSize = pageSize;
		this.curPage = curPage;

		this.pageNumber = (this.recordCnt / this.pageSize + (this.recordCnt % this.pageSize == 0 ? 0 : 1));

		this.firstPage = 1;

		this.lastPage = this.pageNumber;
		update(curPage);
	}

	public void update(int curPage) {
		if (curPage <= 1) {
			this.prePage = curPage;
		} else {
			this.prePage = (curPage - 1);
		}
		if (curPage >= this.pageNumber) {
			this.nextPage = this.curPage;
		} else
			this.nextPage = (curPage + 1);
	}

	public List<?> getList() {
		return this.list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getRecordCnt() {
		return this.recordCnt;
	}

	public void setRecordCnt(int recordCnt) {
		this.recordCnt = recordCnt;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public void setPageSize(int pageSize) {
		this.flag = false;
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return this.pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getFirstPage() {
		return this.firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrePage() {
		return this.prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return this.nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return this.lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public int getCurPage() {
		return this.curPage;
	}

	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}

	public Map<String, Object> getParamsMap() {
		return this.paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}
}
