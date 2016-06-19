package com.service.chart;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
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
 * Servlet implementation class SupplyEach
 */
@WebServlet("/SupplyEach")
public class SupplyEach extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupplyEach() {
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
		CallableStatement cs = null;
		DBO db = new DBO();

		String sql = "{call pro_supply_com(?)}";
		String name = request.getParameter("name");

		JSONObject supply_json = new JSONObject();
		JSONObject supply_msg = new JSONObject();
		JSONArray name_array = new JSONArray();
		JSONArray value_array = new JSONArray();

		boolean status = false;
		String detail = null;

		try {
			con = db.getConn();

			if (con != null) {
				cs = con.prepareCall(sql);
				cs.setString(1, name);

				rs = cs.executeQuery();

				while (rs.next()) {
					// System.out.println(rs.getString(1));
					status = true;
					detail = "查询成功";

					name_array.put(rs.getString(1));
					value_array.put(String.valueOf(rs.getFloat(2)
							* rs.getInt(3) / 1000));

				}
			} else
				detail = "连接失败";

			supply_msg.put("name", name_array);
			supply_msg.put("value", value_array);

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
