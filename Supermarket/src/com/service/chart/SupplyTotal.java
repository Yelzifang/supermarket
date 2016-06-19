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
 * Servlet implementation class SupplyTotal
 */
@WebServlet("/SupplyTotal")
public class SupplyTotal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupplyTotal() {
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

		String sql = "select supname,total from supply";

		JSONObject supply_json = new JSONObject();
		JSONArray supply_msg = new JSONArray();
		JSONObject supply_item = null;

		boolean status = false;
		String detail = null;

		try {
			con = db.getConn();

			if (con != null) {
				rs = db.executeQuery(sql, null);

				while (rs.next()) {
					status = true;
					detail = "查询成功";

					supply_item = new JSONObject();
					supply_item.put("name", rs.getString(1));
					supply_item.put("value", rs.getFloat(2));

					supply_msg.put(supply_item);
				}
			} else
				detail = "连接失败";

			supply_json.put("status", status);
			supply_json.put("detail", detail);
			supply_json.put("message", supply_msg);

			System.out.println(supply_json.toString());

			out.print(supply_json);

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
