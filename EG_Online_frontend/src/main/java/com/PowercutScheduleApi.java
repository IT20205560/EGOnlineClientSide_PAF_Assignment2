package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.PowercutSchedule;;

/**
 * Servlet implementation class DoctorAPI
 */
@WebServlet("/PowercutScheduleAPI")
public class PowercutScheduleApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	PowercutSchedule appObj = new PowercutSchedule();
	
    public PowercutScheduleApi() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String result = appObj.insertPowercutSchedule(request.getParameter("description"), 
				request.getParameter("area"), 
				request.getParameter("date"),
				request.getParameter("time"));
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	
	private Map<String, String> getParasMap(HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();  
		try  {   
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");   
			String queryString = scanner.hasNext() ?
					scanner.useDelimiter("\\A").next() : "";   
			scanner.close(); 
		 
		  String[] params = queryString.split("&");   
		  for (String param : params)   {
			  String[] p = param.split("=");    
			  map.put(p[0], p[1]);   
		  }  
		  
		}catch (Exception e)  {  
			
		} 
		return map;
	}
	
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		String result = appObj.updatePowercutSchedule(param.get("hidApp_IDSave").toString(),
				param.get("description").toString().toString().replace("+", " "),     
				param.get("area").toString().toString().replace("+", " "),        
				param.get("date").toString().toString().replace("+", " "),       
				param.get("time").toString().toString().replace("+", " ")
		 	
 				);
		
		response.getWriter().write(result);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		Map<String, String> param = getParasMap(request);
		
		String result = appObj.deletePowercutSchedule(param.get("mcode").toString());
		
		response.getWriter().write(result);
	}

}
