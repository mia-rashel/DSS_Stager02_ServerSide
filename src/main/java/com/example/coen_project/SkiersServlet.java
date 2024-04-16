package com.example.coen_project;

import com.example.coen_project.error_injection.ErrorInjection;
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
import java.util.Random;



@WebServlet(name = "SkiersServlet", value = "/skiers")
public class SkiersServlet extends HttpServlet {
    private static final double FIVE_PERCENT = 0.05;
    private static final double TEN_PERCENT = 0.1;
    private static final double FIFTEEN_PERCENT = 0.15;
    private static final int MAX_RETRIES = 5;

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

            // Inject exceptions based on percentage
            ErrorInjection.injectError();

            // Process the request without exceptions
            processRequestSuccessfully(response, liftRide);
        } catch (IOException e) {
            // Handle exceptions with retries
            handleRequestWithRetries(request, response);
        }
    }

    private void processRequestSuccessfully(HttpServletResponse response, LiftRide liftRide) throws IOException {
        // Simulate processing and then return a successful response
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = mapper.writeValueAsString(liftRide);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }

    private void handleRequestWithRetries(HttpServletRequest request, HttpServletResponse response) throws IOException {
        for (int retry = 0; retry < MAX_RETRIES; retry++) {
            try {
                // Buffer the request body
                String requestBody = IOUtils.toString(request.getReader());

                // Deserialize the JSON request body into a LiftRide object
                ObjectMapper mapper = new ObjectMapper();
                LiftRide liftRide = mapper.readValue(requestBody, LiftRide.class);

                // Process the request
                processRequestSuccessfully(response, liftRide);
                return; // Exit the retry loop if successful
            } catch (IOException e) {
                // Log the retry exception
                System.err.println("Retry " + (retry + 1) + " failed with exception: " + e.getMessage());
            }
        }

        // If retries are exhausted, return an internal server error response
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Internal Server Error");
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