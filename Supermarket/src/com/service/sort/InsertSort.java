package com.service.sort;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.DBO;

/**
 * Servlet implementation class InsertSort
 */
@WebServlet("/InsertSort")
public class InsertSort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertSort() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		//String sortno = "2";//request.getParameter("sortno");
		String sortname = "生活类";//request.getParameter("sortname");
		
		String params[] = new String[]{sortname};
		
		DBO db = new DBO();
		int n = 0;
		String sql = null;
		
		JSONObject json = new JSONObject();
		JSONObject js = new JSONObject();
		Boolean status = false;
		String detail = null;
		
		try {
			if(db.getConn()!=null){
				System.out.println("连接成功！");
			}
			
		sql = new String("INSERT INTO sort(sortname) VALUES(?)");
		n = db.executeUpdate(sql, params);
		if(n!=0){
			status = true;
			detail = new String("插入类别成功！");
			
		}else{
			detail = new String("插入类别失败！");
		}
		json.put("status", status);
		json.put("detail", detail);
		json.put("message", js);
		out.println(json.toString());
		db.closeAll();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
