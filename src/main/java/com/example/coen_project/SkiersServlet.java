package com.example.coen_project;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;


@WebServlet(name = "SkiersServlet", value = "/skiers")
public class SkiersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Set response content type
        response.setContentType("text/plain");

        // Write response content
        response.getWriter().println("GET request received by SkiersServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Buffer the request body
            String requestBody = IOUtils.toString(request.getReader());

            // Log the request body
            System.out.println("Request Body: " + requestBody);

            // Deserialize the JSON request body into a LiftRide object
            ObjectMapper mapper = new ObjectMapper();
            LiftRide liftRide = mapper.readValue(requestBody, LiftRide.class);

            // Extract parameters from the LiftRide object
            int resortId = liftRide.getResortId();
            int seasonId = liftRide.getSeasonId();
            int dayId = liftRide.getDayId();
            int skierId = liftRide.getSkierId();
            int time = liftRide.getTime();
            int liftId = liftRide.getLiftId();

            // Perform basic parameter validation using switch statement
            String invalidParameter ="";
            switch (invalidParameter) {
                case "resortId":
                    if (resortId < 0) invalidParameter = "resortId";
                    break;
                case "seasonId":
                    if (seasonId < 0) invalidParameter = "seasonId";
                    break;
                case "dayId":
                    if (dayId < 0) invalidParameter = "dayId";
                    break;
                case "skierId":
                    if (skierId < 0) invalidParameter = "skierId";
                    break;
                case "time":
                    if (time < 0) invalidParameter = "time";
                    break;
                case "liftId":
                    if (liftId < 0) invalidParameter = "liftId";
                    break;
            }

            // If any parameter is invalid, return a bad request response
            if (invalidParameter != "") {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing or invalid " + invalidParameter + " parameter");
                return;
            }

            // If all validations pass, return a success response
            response.setStatus(HttpServletResponse.SC_CREATED);
            response.getWriter().write("Lift ride recorded successfully");
        } catch (Exception e) {
            // Log the exception
            e.printStackTrace();

            // Return an internal server error response
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Internal Server Error");
        }
    }



    // Implementing the Servlet interface methods
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
    }

    @Override
    public ServletConfig getServletConfig() {
        return null; // You need to implement this method
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        super.service(servletRequest, servletResponse);
    }

    @Override
    public String getServletInfo() {
        return null; // You can implement this method if needed
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
