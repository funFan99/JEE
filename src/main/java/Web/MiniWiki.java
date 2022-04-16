package Web;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Model.JsonClient;

@WebServlet(
		urlPatterns = {
				"/list",
				"/login",
				"/register",
				"/logout"
		})
public class MiniWiki extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public MiniWiki() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonClient jc = new JsonClient();
		JsonReader jr;
		try {
			jr = Json.createReader(new StringReader(jc.get("http://127.0.0.1:8000/api/users")));
			JsonArray weaker = jr.readArray();
			response.getWriter().append(weaker.toString());
			
			switch (request.getServletPath()) {
				case "/list":
					request.setAttribute("weaker", weaker);
					this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
					break;
				case"/register":
					this.getServletContext().getRequestDispatcher("/Register.jsp").forward(request, response);
					break;
				case"/login":
					this.getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					break;
				case "/logout":
				
					HttpSession ss= request.getSession();
					String em =(String) ss.getAttribute("user");
					System.out.println(em);
					JsonObjectBuilder job = Json.createObjectBuilder();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					job.add("Date",dtf.format(LocalDateTime.now()));
					job.add("EstConnecté", false);
					jc.put("http://127.0.0.1:8000/api/session/"+em, job.build().toString());
					ss.invalidate();
					response.sendRedirect("login");
					break;
					
			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			JsonClient jc = new JsonClient();
			switch(request.getServletPath()) {
			case "/register":
				if (request.getParameter("username") != null && request.getParameter("email") != null && request.getParameter("password") != null) {
					
					JsonObjectBuilder job = Json.createObjectBuilder();
					job.add("username", request.getParameter("username"));
					job.add("email", request.getParameter("email"));
					job.add("password", request.getParameter("password"));
					job.add("estAdmin", false);
					jc.post("http://127.0.0.1:8000/api/register", job.build().toString());
					
					response.sendRedirect("list");
				}
				break;
			case "/login":
				if(request.getParameter("login") != null && request.getParameter("password") != null){
				String jj =	jc.get("http://127.0.0.1:8000/api/login/"+request.getParameter("login")+"/"+request.getParameter("password"));
				
				if(jj.equals("")) {
					response.sendRedirect("login");
				}else {
					HttpSession ss = request.getSession();
					ss.setAttribute("user",request.getParameter("login") );
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
					JsonObjectBuilder job = Json.createObjectBuilder();
					job.add("Date",dtf.format(LocalDateTime.now()));
					job.add("EstConnecté", true);
					
					jc.put("http://127.0.0.1:8000/api/session/"+request.getParameter("login"), job.build().toString());
					
					response.sendRedirect("list");
					}
			}
				break;
			
			}
			}catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
