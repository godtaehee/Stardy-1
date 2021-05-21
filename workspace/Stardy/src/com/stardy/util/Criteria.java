package com.stardy.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Criteria {

	private int page;
	private int count;
	
	private int sum;
	private int startNum;
	private int tempEndNum;
	private int endNum;
	private int realEndNum;
	
	private boolean next;
	private boolean prev;
	
	public Criteria(int sum) {
		this(1, 10, sum);
	}
	
	public Criteria(int page, int sum) {
		this(page, 10, sum);
	}
	
	public Criteria(int page, int count, int sum) {
		this.page = page;
		this.count = count;
		this.sum = sum;
		
		startNum = ((page) / 5) * 5;
		tempEndNum = startNum + 5;
		endNum = sum / 10 + (int) Math.ceil(sum % 10.0 / 10.0);
		realEndNum = tempEndNum < endNum? tempEndNum : endNum;
		
		next = realEndNum < endNum;
		prev = startNum > 1;
	}
}