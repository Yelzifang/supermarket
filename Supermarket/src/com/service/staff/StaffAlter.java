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
		String stano = "5";//request.getParameter("stano");
		String pwd = "";//request.getParameter("pwd");
		String name = "huazhka";//request.getParameter("name");
		String sex = "2";//request.getParameter("sex");
		String tele = "";//request.getParameter("tele");
		String birthday = "1993-06-14";//request.getParameter("birthday");
		String params[] = new String[]{sex,stano};
		
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
			String sql1="",sql2="",sql3="",sql4="";
			Boolean j = false;
			if(!pwd.equals("")){	
				sql1 = " pwd='"+pwd+"'";
				j = true;
			}
			if(!name.equals("")){
				if(j){
					sql2 = " ,staname='"+name+"'";
				}else{
					sql2 = " staname='"+name+"'";
					j = true;
				}
			}
			if(!tele.equals("")){
				if(j){
					sql3 = " ,tele='"+tele+"'";
				}else{
					sql3 = " tele='"+tele+"'";
					j = true;
				}
			}
			if(!birthday.equals("")){
				if(j){
					sql4 = " ,birthday='"+birthday+"'";
				}else{
					sql4 = " birthday='"+birthday+"'";
					j = true;
				}
			}
		sql = new String("UPDATE staff SET"+sql1+sql2+sql3+sql4+" ,sex=? WHERE stano=?");
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
