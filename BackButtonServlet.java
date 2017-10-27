package com.sagipl.test;

import java.awt.print.Printable;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class BackButtonServlet
 */
@WebServlet("/BackButtonServlet")
public class BackButtonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession sessionObject;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BackButtonServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		try {
//			Object obj =  new JSONParser().parse(request.getReader());
//			if(obj instanceof org.json.simple.JSONObject) {
//				System.out.println("I am JSON Object..."+obj);
//				
//			} else {
//				System.out.println("I am Not JSON Object...");
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		String getsessionRequest = request.getParameter("action");
		System.out.println("getseesionObject    --------   "+getsessionRequest);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		StringBuffer colectReqData = new StringBuffer();
		
		try{
		
			BufferedReader readReqestData = request.getReader();
			String lineRead = null;
			while((lineRead = readReqestData.readLine()) !=null){
				colectReqData.append(lineRead);
			}
			JSONParser parseStringBuffer = new JSONParser();
			org.json.simple.JSONObject jsObject = new org.json.simple.JSONObject();
			jsObject = (org.json.simple.JSONObject)parseStringBuffer.parse(colectReqData.toString());
			String username = (String) jsObject.get("username");
			String password = (java.lang.String)jsObject.get("password");
			System.out.println("username ----- "+username+"\npassword   ----- "+password);
			response.setContentType("text/html");
			PrintWriter printObj = response.getWriter();;
			if(username != null && password !=null){
				if(username.equals("shubham") && password.equals("gaur")){
					System.out.println("user enter");
					sessionObject = request.getSession(true); 
					printObj.write("success");
					 	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
				        response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
				        response.setDateHeader("Expires", 0);
					
				}else{
					System.out.println("please enter valid name");
					printObj.write("please enter valid name");
				}
				
			}else{
				System.out.println("please fill entry");
				printObj.write("please fill entry");
			}
			printObj.flush();
			printObj.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		
	}
	
	

}
