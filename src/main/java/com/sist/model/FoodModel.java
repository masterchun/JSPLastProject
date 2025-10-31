package com.sist.model;

import com.sist.controller.Controller;
import com.sist.controller.RequestMapping;
import com.sist.dao.*;
import com.sist.vo.*;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.*;

@Controller
public class FoodModel {
	@RequestMapping("food/list.do")
	public String food_list(HttpServletRequest request, HttpServletResponse response) {
		String page = request.getParameter("page");
		if(page==null) page = "1";
		int curpage = Integer.parseInt(page);
		
		Map map = new HashMap();
		int rowSize = 12;
		int start = (rowSize * curpage) - (rowSize - 1);
		int end = rowSize * curpage;
		
		map.put("start", start);
		map.put("end", end);
		List<FoodVO> list = FoodDAO.foodListData(map);
		for(FoodVO vo:list) {
			String addr = vo.getAddress();
			addr = addr.substring(0, addr.indexOf(" "));
			vo.setAddress(addr.trim());
		}
		int totalpage = FoodDAO.foodTotalPage();
		
		final int BLOCK = 10;
		int startPage = ((curpage - 1) / BLOCK * BLOCK) + 1;
		int endPage = ((curpage - 1) / BLOCK * BLOCK) + BLOCK;
		if(endPage > totalpage) endPage = totalpage;
		
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("main_jsp", "../food/list.jsp");
		
		return "../main/main.jsp";
	}
}





























