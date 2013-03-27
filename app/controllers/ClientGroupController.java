package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import views.html.*;
import views.html.clientgroup.*;

import models.*;

public class ClientGroupController extends Controller {
  
  final static Form<ClientGroup> form = form(ClientGroup.class);

  public static Result newClientGroupForm() {
    return ok(newClientGroupForm.render(form));
  }

  public static Result newClientGroup() {
    Form<ClientGroup> filledForm = form.bindFromRequest();
    Logger.info("Request: " + filledForm.toString());
    ClientGroup client = filledForm.get();
    client.save();
    return redirect(routes.Application.index());
  }
}