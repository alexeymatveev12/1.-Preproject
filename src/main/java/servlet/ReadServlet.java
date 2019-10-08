package main.java.servlet;


import main.java.model.User;
import main.java.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/read")
public class ReadServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List <User> allUsers =new UserService().getAllUsers();
  //      req.setAttribute("allUsers", allUsers);
    //    req.getServletContext().getRequestDispatcher("/jsp/read.jsp").forward(req, resp);
        List<User> allUsers = new UserService().getAllUsers();
        req.setAttribute("allUsers", allUsers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/web/jsp/read.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
