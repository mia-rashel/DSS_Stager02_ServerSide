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

import java.util.ArrayList;
import java.util.List;



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

            // Check for missing parameters
            List<String> missingParameters = new ArrayList<>();
            if (resortId <= 0) {
                missingParameters.add("resortId");
            }
            if (seasonId <= 0) {
                missingParameters.add("seasonId");
            }
            if (dayId <= 0) {
                missingParameters.add("dayId");
            }
            if (skierId <= 0) {
                missingParameters.add("skierId");
            }
            if (time <= 0) {
                missingParameters.add("time");
            }
            if (liftId <= 0) {
                missingParameters.add("liftId");
            }

            // If any parameter is missing, return a bad request response
            if (!missingParameters.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write("Missing parameter(s): " + String.join(", ", missingParameters) + ". Response code: " + HttpServletResponse.SC_BAD_REQUEST);  return;
            }

            // If all validations pass, return a success response along with the data

            response.setContentType("application/json");
            // Create a JSON representation of the recorded lift ride
            String jsonResponse = mapper.writeValueAsString(liftRide);
            response.getWriter().write(jsonResponse);
            // Send a message indicating data received successfully
            response.getWriter().write("Data received successfully. Response code: " + HttpServletResponse.SC_CREATED);
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
