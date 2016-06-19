package com.service.staff;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.DBO;

/**
 * Servlet implementation class StaffShow
 */
@WebServlet("/StaffShow")
public class StaffShow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StaffShow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = "fzb";//session.getAttribute("account");
		String password = "123";//session.getAttribute("pwd");
		String all = "true";//request.getParameter("all");
		String stano = "1";//request.getParameter("stano");
		String account = "fzb";//request.getParameter("account");
		String name = "%冯志宝%";//request.getParameter("name");
		
		String params[] = new String[]{};
		
		DBO db = new DBO();
		ResultSet rs = null;
		String sql = null;

		JSONObject json = new JSONObject();
		JSONArray js = new JSONArray();
		Boolean status = false;
		String detail = null;
		
		try {
			if(db.getConn()!=null){
				System.out.println("连接成功！");
			}
		if(username!=null&&password!=null){
			if(all.equals("true")){
				sql = new String("SELECT * FROM staff WHERE identity=2");
			}else if(!stano.equals("")){
				sql = new String("SELECT * FROM staff WHERE identity=2 AND stano="+stano);
			}else if(!account.equals("")){
				sql = new String("SELECT * FROM staff WHERE identity=2 AND account='"+account+"'");
			}else if(!name.equals("%%")){
				sql = new String("SELECT * FROM staff WHERE identity=2 AND name LIKE '"+name+"'");
			}
			rs = db.executeQuery(sql, params);
			if(rs.next()){
				status = true;
				detail = new String("查询员工信息成功！");
			}else{
				detail = new String("员工信息查询失败！");
			}
			rs = db.executeQuery(sql, params);
			while(rs.next()){
				JSONObject temp = new JSONObject();
				temp.put("stano", rs.getInt(1));
				temp.put("account", rs.getString(2));
				temp.put("pwd", rs.getString(3));
				temp.put("name", rs.getString(4));
				temp.put("sex", rs.getInt(5));
				temp.put("tele", rs.getString(6));
				temp.put("birthday", rs.getString(8));
				js.put(temp);
			}
		}else{
			detail = new String("请登录！");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doGet(request, response);
	}

}
