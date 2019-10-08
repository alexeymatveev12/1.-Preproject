package main.java.servlet;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet("/result")
public class ResultServlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
      //  resp.getWriter().println(PageGenerator.getInstance().getPage("result.html", new HashMap<>()));

    //    PageGenerator.getInstance().getPage("result.html", new HashMap<>());
    //    resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println("ResultServlet2 yyyyyyyyyyyyyyyyyyyyyyyy");
    }
}
