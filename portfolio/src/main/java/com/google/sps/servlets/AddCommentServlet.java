package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.sps.data.Comment;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/add-comment")
public class AddCommentServlet extends HttpServlet {
      
   @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the input from the form.
    String username = request.getParameter("username");
    String message= request.getParameter("message");
    long timestamp = System.currentTimeMillis();

    //creates a new entity for the comment
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("Name", username);
    commentEntity.setProperty("Message", message);
    commentEntity.setProperty("Timestamp",timestamp);

    //adds the comment to the datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }

}
