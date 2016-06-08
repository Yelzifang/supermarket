package com.service.commodity;

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
 * Servlet implementation class SearchComed
 */
@WebServlet("/SearchComed")
public class SearchComed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchComed() {
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
		String stano = "1";//(String)session.getAttribute("stano");
		String dateTime = null;//request.getParameter("saletime");
		String saleamount = null;//request.getParameter("saleamount");
		String params[] = null;
		
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
			if(dateTime==null&&saleamount==null){
				params = new String[]{stano};
				sql = new String("SELECT comname,saledate,state,saleamount FROM sale,commodity"+
				" WHERE commodity.comno=sale.comno AND stano=?");
			}else if(saleamount!=null){
				params = new String[]{stano,saleamount};
				sql = new String("SELECT comname,saledate,state,saleamount FROM sale,commodity"+
				" WHERE commodity.comno=sale.comno AND stano=? AND saleamount>?");
			}else{
				params = new String[]{stano,dateTime};
				sql = new String("");
			}
			rs = db.executeQuery(sql, params);
			if(rs.next()){
				status = true;
				detail = new String("查询成功！");
			}else{
				detail = new String("查询失败！");
			}
			rs = db.executeQuery(sql, params);
			while(rs.next()){
				JSONObject temp = new JSONObject();
				temp.put("comname", rs.getString(1));
				temp.put("dateTime", rs.getDate(2).toString());
				temp.put("state", rs.getInt(3));
				temp.put("saleamount", rs.getInt(4));
				js.put(temp);
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
