package com.sist.dao;

import com.sist.commons.*;
import com.sist.vo.*;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class FoodDAO {
	private static SqlSessionFactory ssf;
	
	static {
		ssf = CreateSqlSessionFactory.getSsf();
	}
	
	public static List<FoodVO> foodListData(Map map) {
		List<FoodVO> list = null;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			list = session.selectList("foodListData", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return list;
	}
	
	public static int foodTotalPage() {
		int total = 0;
		SqlSession session = null;
		
		try {
			session = ssf.openSession();
			total = session.selectOne("foodTotalPage");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return total;
	}
}

































