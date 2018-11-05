package Controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.User;
import service.Encrypter;

/**
 * Servlet implementation class loginController
 */
@WebServlet("/login")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HttpSession session;
    private int loginAttemptCount;
    private String url;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public loginController() {
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
		session = request.getSession();
		
		if(session.getAttribute("loginAttemptCount") == null)
		{
			loginAttemptCount=0;
		}
		
		if(loginAttemptCount >3) {
			String loginCountMessage= "Login attempt exceeds limit";
			request.setAttribute("attemptMsg", loginCountMessage);
			url = "login.jsp";
		}
		else {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			Encrypter encryptObj = new Encrypter();
			String encryptedPws = encryptObj.encrypt(password);
			
			User user = null;//change need to call database and authenticate here
			if(user!=null) {
				session.invalidate();
				session=request.getSession(true);
				session.setAttribute("user", user);
				url="game.jsp";
			}
			else {
				String errorMsg = "Username or password doesn't exist";
				request.setAttribute("errorMsg", errorMsg);
				session.setAttribute("loginAttemptCount",loginAttemptCount++);
				url = "index.jsp";
			}
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
	
	public void logout() {
		session.invalidate();
	}

}
