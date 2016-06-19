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
 * Servlet implementation class StaffTotal
 */
@WebServlet("/StaffTotal")
public class StaffTotal extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StaffTotal() {
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

		String sql = "{call pro_staff_sale_total()}";

		JSONObject staff_json = new JSONObject();
		JSONObject staff_msg = new JSONObject();
		JSONArray name_array = new JSONArray();
		JSONArray value_array = new JSONArray();

		boolean status = false;
		String detail = null;

		try {
			con = db.getConn();

			if (con != null) {
				cs = con.prepareCall(sql);

				rs = cs.executeQuery();

				while (rs.next()) {

					status = true;
					detail = "查询成功";

					name_array.put(rs.getString(1));
					value_array.put(rs.getString(2));
				}
			} else
				detail = "连接失败";

			staff_msg.put("name", name_array);
			staff_msg.put("value", value_array);

			staff_json.put("status", status);
			staff_json.put("detail", detail);
			staff_json.put("message", staff_msg);

			out.println(staff_json);
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
