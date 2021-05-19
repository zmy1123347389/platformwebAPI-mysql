package com.behere.common.utils;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

/**
 * @Author behere 1992lcg@163.com
 */
public class  PageUtils implements Serializable {
	private static final long serialVersionUID = 1L;
	//总数
	private int total;
	//总页数
	private int pages;
	//当前页
	private int pageNum;
	//分页数
	private int pageSize;
	//数据
	private List<?> rows;

	public PageUtils(List<?> list, int total) {
		this.rows = list;
		this.total = total;
	}
	
	public PageUtils(List<?> list, Page<Object> startPage) {
		this.rows = list;
		long total = startPage.getTotal();
		int intValue = new Long(total).intValue();
		this.total = intValue;
		long pages = startPage.getPages();
		int intPages = new Long(pages).intValue();
		this.pages = intPages;
		long pageNum = startPage.getPageNum();
		int intPageNum = new Long(pageNum).intValue();
		this.pageNum = intPageNum;
		long pageSize = startPage.getPageSize();
		int intPageSize = new Long(pageSize).intValue();
		this.pageSize = intPageSize;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}

}
