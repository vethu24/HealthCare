package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Servlet implementation class paymentAPI
 */
@WebServlet("/paymentAPI")
public class paymentAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	payment p=new payment();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public paymentAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String output = p.addCard(request.getParameter("holderName"),
				request.getParameter("ctype"),
				request.getParameter("cardNo"),
				request.getParameter("cvv"),
				request.getParameter("expMonth"),
				request.getParameter("expYear"),
				request.getParameter("total"));
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = p.updatePayment(paras.get("hidItemIDSave").toString(),
		paras.get("holderName").toString(),
		paras.get("ctype").toString(),
		paras.get("cardNo").toString(),
		paras.get("cvv").toString(),
		paras.get("expMonth").toString(),
		paras.get("expYear").toString(),
		paras.get("total").toString());
		
		response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = p.deletePayment(paras.get("cardpayID").toString());
		response.getWriter().write(output);
	}
	
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request){
		
		Map<String, String> map = new HashMap<String, String>();
		
		try{
		
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		
			String queryString = scanner.hasNext() ?
		
			scanner.useDelimiter("\\A").next() : "";
		
			scanner.close();
		
			String[] params = queryString.split("&");
		
			for (String param : params){
		
			
				String[] p = param.split("=");
			
				map.put(p[0], p[1]);
			
			}
		}catch (Exception e){}
		return map;
	}
}
