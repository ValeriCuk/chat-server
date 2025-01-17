package academy.prog;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Set;

public class GetListUsersOnlineServlet extends HttpServlet {
    private final UsersList usersList = UsersList.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("GetListUsersOnlineServlet");
        Set<String> onlineUsers = usersList.getOnlineUsers();
        String jsonResponse = new Gson().toJson(onlineUsers);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonResponse);
    }
}
