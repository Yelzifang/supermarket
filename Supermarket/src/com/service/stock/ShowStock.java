package com.service.stock;

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
 * Servlet implementation class ShowStock
 */
@WebServlet("/ShowStock")
public class ShowStock extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowStock() {
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
		String stockall = "tru";//request.getParameter("stockall");
		String stono = "";//request.getParameter("stono");
		String stano = "";//(String)session.getAttribute("stano");//个人进货查询
		String comno = "";//(String)request.getParameter("comno");//按商品查询
		String time = "3";//(String)request.getParameter("time");//按时间查询
		String stoamount1 = "100";//(String)request.getParameter("stoamount1");//按进货数量查询
		String stoamount2 = "1500";//(String)request.getParameter("stoamount2");//按进货数量查询
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
			if(stockall.equals("true")){
				sql = new String("SELECT commodity.comname,staff.staname,stodate,stoamount FROM commodity,staff,stock WHERE stock.comno=commodity.comno AND stock.stano=staff.stano");
			}else if(!stono.equals("")){
				sql = new String("SELECT commodity.comname,staff.staname,stodate,stoamount FROM commodity,staff,stock WHERE stock.comno=commodity.comno AND stock.stano=staff.stano AND stono="+stono);
			}else{
				String sql1="",sql2="",sql3="",sql4="",sql5="";
				if(!stano.equals("")){
					sql1=" AND stock.stano="+stano;
				}
				if(!comno.equals("")){
						sql2=" AND stock.comno="+comno;
				}
				if(time.equals("1")){
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(stodate) <= 1";	
				}else if(time.equals("2")){
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(stodate) <= 7";
				}else{
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(stodate) <= 30";
				}
				if(!stoamount1.equals("")){
						sql4=" AND stoamount>="+stoamount1;
				}
				if(!stoamount2.equals("")){
						sql5=" AND stoamount<="+stoamount2;	
				}
				sql = new String("SELECT commodity.comname,staff.staname,stodate,stoamount FROM commodity,staff,stock WHERE stock.comno=commodity.comno AND stock.stano=staff.stano"+sql1+sql2+sql3+sql4+sql5);
			}
			
			rs = db.executeQuery(sql, params);
			if(rs.next()){
				status = true;
				detail = new String("查询进货信息成功！");
				rs = db.executeQuery(sql, params);
				while(rs.next()){
					JSONObject temp = new JSONObject();
					temp.put("comname", rs.getString(1));
					temp.put("staname", rs.getString(2));
					temp.put("stadate", rs.getDate(3));
					temp.put("stoamount", rs.getInt(4));
					js.put(temp);
				}
			}else{
				detail = new String("查询进货信息失败！");
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
