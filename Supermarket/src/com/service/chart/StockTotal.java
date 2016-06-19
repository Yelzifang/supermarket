package com.service.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dao.DBO;

/**
 * Servlet implementation class StockTotal
 */
@WebServlet("/StockTotal")
public class StockTotal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StockTotal() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		Connection con = null;
		ResultSet rs = null;
		DBO db = new DBO();

		String sql = "select MONTH(stodate),sum(stoamount) from stock GROUP BY MONTH(stodate)";

		JSONObject stock_json = new JSONObject();
		JSONObject stock_msg = new JSONObject();
		JSONArray name_array = new JSONArray();
		JSONArray value_array = new JSONArray();

		String detail = null;
		boolean status = false;

		try {
			con = db.getConn();

			if (con != null) {

				rs = db.executeQuery(sql, null);
				status = true;
				detail = "查询成功";

				while (rs.next()) {
					name_array.put(rs.getInt(1));
					value_array.put(rs.getInt(2));
				}
			} else
				detail = "连接失败";

			stock_msg.put("name", name_array);
			stock_msg.put("value", value_array);

			stock_json.put("status", status);
			stock_json.put("detail", detail);
			stock_json.put("message", stock_msg);

			out.print(stock_json);

			db.closeAll();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
