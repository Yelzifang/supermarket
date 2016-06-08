package com.service.staff;

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
 * Servlet implementation class StaffAlter
 */
@WebServlet("/StaffAlter")
public class StaffAlter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffAlter() {
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
		String username = "hzk";//request.getParameter("account");
		String password = "123";//request.getParameter("pwd");
		String name = "huazhka";//request.getParameter("name");
		String sex = "2";//request.getParameter("sex");
		String tele = "15016066772";//request.getParameter("tele");
		String birthday = "1993-06-14";//request.getParameter("birthday");
		String params[] = new String[]{password,name,sex,tele,birthday,username};
		
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
			
		sql = new String("UPDATE staff SET pwd=?,staname=?,sex=?,tele=?,birthday=? WHERE account=?");
		n = db.executeUpdate(sql, params);
		if(n!=0){
			status = true;
			detail = new String("修改成功！");
			
		}else{
			detail = new String("修改失败！");
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
