package com.stardy.util;

import com.stardy.entity.Category;

public class CategoryConvert {
	
    public static String convert(int category) {
        if(category == 1) {
            return "IT";
        }
        else if(category == 2) {
            return "어학";
        }
        else if(category == 3) {
            return "자격증";
        }
        else if(category == 4) {
            return "고시/공무원";
        }else if(category == 5) {
            return "취미/교양";
        }else {
            return "기타";
        }
    }
 
    public static String getCategory(int idx) {
    	
		Category[] categories = Category.values();
		
		for(Category a : categories) 
			if(a.ordinal() == idx)
				return a.toString();
		
		return null;
    }
}