package com.service.sort;

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
 * Servlet implementation class ShowSort
 */
@WebServlet("/ShowSort")
public class ShowSort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowSort() {
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
		String username = "1";//(String)session.getAttribute("account");
		String password = "123";//(String)session.getAttribute("pwd");
		
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
			sql = new String("SELECT * FROM sort");
			rs = db.executeQuery(sql, params);
			if(rs.next()){
				status = true;
				detail = new String("查询类别成功！");
			}else{
				detail = new String("查询类别失败！");
			}
			rs = db.executeQuery(sql, params);
			while(rs.next()){
				JSONObject temp = new JSONObject();
				temp.put("sortno", rs.getInt(1));
				temp.put("sortname", rs.getString(2));
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
