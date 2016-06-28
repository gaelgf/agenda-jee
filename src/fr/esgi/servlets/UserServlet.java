package fr.esgi.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.esgi.model.IUserManager;
import fr.esgi.model.UserManagerDB;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(
		name = "user-servlet",
		description = "Servlet handling user login",
		urlPatterns={"/login", "/create", "/list"}
)
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IUserManager userManager = new UserManagerDB(); 
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String uri = request.getRequestURI();
		if(uri.contains("/login")) {
			this.login(request, response);
		} else if(uri.contains("/create")) {
			this.create(request, response);
		} else if(uri.contains("/list")) {
			this.list(request, response);
		} else {
			response.getWriter().append("Index");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("user");
		final String password = request.getParameter("password");
		
		if (login == null || password == null) {
			// just display the login
		} else if(this.userManager.checkLogin(login)) {
			if (this.userManager.checkLoginWithPassword(login, password)) {
				request.setAttribute("success", "User found");
			} else {
				request.setAttribute("errorMessage", "Bad password");
			}
		} else {
			request.setAttribute("errorMessage", "User not found");
		}
		
		request.setAttribute("action", "login");
		request.setAttribute("title", "Login form");
		request.getRequestDispatcher("/WEB-INF/html/userForm.jsp").forward(request, response);
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final String login = request.getParameter("user");
		final String password = request.getParameter("password");
		
		if (login != null && password != null) {
			if (this.userManager.checkLogin(login)) {
				request.setAttribute("errorMessage", "User already exists. Please chose another");
			} else {
				if(this.userManager.createUser(login, password))
					request.setAttribute("success", "User created");
			}	
		}
		
		request.setAttribute("action", "create");
		request.setAttribute("title", "Create form");
		request.getRequestDispatcher("/WEB-INF/html/userForm.jsp").forward(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String pageType = request.getParameter("output");
		
		if (pageType == null || !pageType.equals("json")) {
			pageType = "html";
		} 
		request.setAttribute("userList", this.userManager.allUsers());
		request.getRequestDispatcher("/WEB-INF/" + pageType + "/userList.jsp").forward(request, response);
	}

}
