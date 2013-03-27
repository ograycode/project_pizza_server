package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.client.*;
import views.html.*;

import models.*;

public class ClientController extends Controller {

  /**
  * Defines a form wrapping the Client class.
  */ 
  final static Form<Client> form = form(Client.class);
  
  /* Retrieves the form for creating new clients */
  public static Result newClientForm() {
    Logger.info("Inside newClientForm");
    return ok(newClientForm.render(form));
  }

  /*Creates a new client based upon data POSTed */
  public static Result newClient() {
    Form<Client> filledForm = form.bindFromRequest();
    Logger.info("Request: " + filledForm.toString());
    Client client = filledForm.get();
    client.save();
    return redirect(routes.Application.index());
  }


}