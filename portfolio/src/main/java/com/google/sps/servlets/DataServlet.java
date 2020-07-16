// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.sps.servlets;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import java.io.IOException;
import java.util.ArrayList;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/data")
public class DataServlet extends HttpServlet {
    public class comment{
        public String Name;
        public String Message;

        public comment(String name,String message){
            Name=name;
            Message=message;
        }
    }   
  
  private ArrayList<comment> comments=new ArrayList<comment>();

  public void init(){
    comments.add(new comment("Paul","Amazing Portfolio"));
    comments.add(new comment("Sam","Beautiful art gallery"));
    comments.add(new comment("Harry","Perfect Layout"));
  }
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String json=convertJsonwithGson(comments);
    response.setContentType("application/json;");
    response.getWriter().println(json);
  }
   @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the input from the form.
    String username = request.getParameter("username");
    String message= request.getParameter("message");
    
    //creates a new entity for the comment
    Entity commentEntity = new Entity("Comment");
    commentEntity.setProperty("Name", username);
    commentEntity.setProperty("Message", message);

    //adds the comment to the datastore
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    datastore.put(commentEntity);

    // Redirect back to the HTML page.
    response.sendRedirect("/index.html");
  }
  private String convertJsonwithGson(ArrayList<comment> comments) {
        Gson gson = new Gson();
        String json = gson.toJson(comments);
        return json;
    }
}
