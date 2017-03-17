package com.ibm.cooking.servlet;

import com.ibm.cooking.TLSConnectionTest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by root on 3/17/17.
 */
public class MethodExecutorServlet extends HttpServlet {

  private String message;

  public void init() throws ServletException {
    // Do required initialization
    message = "Hello World";
  }

  public void doGet(HttpServletRequest request,
                    HttpServletResponse response)
    throws ServletException, IOException {

    String m = request.getParameter("m");

    try {
      TLSConnectionTest tester = new TLSConnectionTest();
      Method method = TLSConnectionTest.class.getMethod(m);
      method.invoke(tester);
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    } finally {
    }

    // Set response content type
    response.setContentType("text/html");

    // Actual logic goes here.
    PrintWriter out = response.getWriter();
    out.println("<h1>" + m + "</h1>");
  }

  public void destroy() {
    // do nothing.
  }
}
