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
		String stockall = "tru";//request.getParameter("stockall");
		String resno = "";//request.getParameter("stono");
		String stano = "5";//(String)session.getAttribute("stano");//个人进货查询
		String comno = "";//(String)request.getParameter("comno");//按商品查询
		String time = "3";//(String)request.getParameter("time");//按时间查询
		String reamount1 = "100";//(String)request.getParameter("stoamount1");//按进货数量查询
		String reamount2 = "1500";//(String)request.getParameter("stoamount2");//按进货数量查询
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
				sql = new String("SELECT commodity.comname,staff.staname,redate,reamount,reason "+
			"FROM commodity,staff,restock WHERE commodity.comno=restock.comno AND staff.stano=restock.stano");
			}else if(!resno.equals("")){
				sql = new String("SELECT commodity.comname,staff.staname,redate,reamount,reason "+
			"FROM commodity,staff,restock WHERE commodity.comno=restock.comno AND staff.stano=restock.stano AND resno="+resno);
			}else{
				String sql1="",sql2="",sql3="",sql4="",sql5="";
				if(!stano.equals("")){
					sql1=" AND restock.stano="+stano;
				}
				if(!comno.equals("")){
						sql2=" AND restock.comno="+comno;
				}
				if(time.equals("1")){
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(redate) <= 1";	
				}else if(time.equals("2")){
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(redate) <= 7";
				}else{
						sql3=" AND TO_DAYS(NOW()) - TO_DAYS(redate) <= 30";
				}
				if(!reamount1.equals("")){
						sql4=" AND reamount>="+reamount1;
				}
				if(!reamount2.equals("")){
						sql5=" AND reamount<="+reamount2;	
				}
				sql = new String("SELECT commodity.comname,staff.staname,redate,reamount,reason FROM commodity,staff,restock "+
				"WHERE commodity.comno=restock.comno AND staff.stano=restock.stano"+sql1+sql2+sql3+sql4+sql5);
			}
			//sql = new String("SELECT commodity.comname,staff.staname,redate,reamount,reason FROM commodity,staff,restock WHERE commodity.comno=restock.comno AND staff.stano=restock.stano");
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
