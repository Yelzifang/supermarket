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

import org.json.JSONException;
import org.json.JSONObject;

import com.dao.DBO;

/**
 * Servlet implementation class Staffself
 */
@WebServlet("/Staffself")
public class Staffself extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Staffself() {
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
		System.out.println("staffself");
		String username = (String)session.getAttribute("account");
		String password = (String)session.getAttribute("pwd");
		String params[] = new String[]{username,password};
		
		DBO db = new DBO();
		ResultSet rs = null;
		String sql = null;

		JSONObject json = new JSONObject();
		JSONObject js = new JSONObject();
		Boolean status = false;
		String detail = null;
		
		try {
			if(db.getConn()!=null){
				System.out.println("连接成功！");
			}
			
		sql = new String("SELECT * FROM staff WHERE account=? AND pwd=?");
		rs = db.executeQuery(sql, params);
		if(rs.next()){
			status = true;
			detail = new String("查新信息成功！");
		}else{
			detail = new String("账号或者密码错误！");
		}
		rs=db.executeQuery(sql, params);
		while(rs.next()){
			js.put("stano", rs.getInt(1));
			js.put("account", rs.getString(2));
			js.put("pwd", rs.getString(3));
			js.put("staname", rs.getString(4));
			js.put("sex", rs.getInt(5));
			js.put("tele", rs.getString(6));
			js.put("birthday", rs.getDate(8).toString());
		}
		json.put("status", status);
		json.put("detail", detail);
		json.put("message", js);
		System.out.println(json.toString());
		out.println(json.toString());
		db.closeAll();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
