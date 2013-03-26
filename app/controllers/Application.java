package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.*;

import java.util.List;

public class Application extends Controller {
  
    public static Result index() {
        List<Client> clients = Client.find.all();
        return ok(index.render(clients));
    }
  
}
