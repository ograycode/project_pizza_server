package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.*;

import java.util.List;

public class Application extends Controller {
  
    public static Result index() {
        List<Client> clients = Client.find.all();
        for (Client client : clients) {
          Logger.info("Client id: " + client.id);
        }
        return ok(index.render(clients));
    }
  
}
