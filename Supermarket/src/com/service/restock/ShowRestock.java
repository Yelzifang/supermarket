package com.service.restock;

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
 * Servlet implementation class ShowRestock
 */
@WebServlet("/ShowRestock")
public class ShowRestock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowRestock() {
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
		String stano = "";//(String)session.getAttribute("stano");//个人进货查询
		String time = "";//(String)request.getParameter("time");//按时间查询
		String comno = "";//(String)request.getParameter("comno");//按商品查询
		String stoamount = "";//(String)request.getParameter("stoamount");//按进货数量查询
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
			sql = new String("SELECT commodity.comname,staff.staname,redate,reamount,reason FROM commodity,staff,restock WHERE commodity.comno=restock.comno AND staff.stano=restock.stano");
			rs = db.executeQuery(sql, params);
			if(rs.next()){
				status = true;
				detail = new String("查询退货信息成功！");
				rs = db.executeQuery(sql, params);
				while(rs.next()){
					JSONObject temp = new JSONObject();
					temp.put("comname", rs.getString(1));
					temp.put("staname", rs.getString(2));
					temp.put("redate", rs.getDate(3));
					temp.put("reamount", rs.getInt(4));
					temp.put("reason", rs.getString(5));
					js.put(temp);
				}
			}else{
				detail = new String("查询退货信息失败！");
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
