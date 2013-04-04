package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import org.codehaus.jackson.JsonNode;

import views.html.client.*;
import views.html.*;

import models.*;

import java.util.List;

public class ClientController extends Controller {

  /**
  * Defines a form wrapping the Client class.
  */ 
  private static Form<Client> form = form(Client.class);

  /* Retrieves all clients */
  public static Result all() {
    List<Client> clients = Client.find.all();
    return ok(listClients.render(clients));
  }
  
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

  /* Creates a new form for the given client id */
  public static Result editForm(Long id) {
    Client client = Client.find.byId(id);
    form = form.fill(client);
    return ok(editClientForm.render(form));
  }

  /* Edits client from POST request */
  public static Result edit() {
    Client client = form.bindFromRequest().get();
    client.update();
    return redirect(routes.ClientController.show(client.id));
  }

  public static Result show(long id) {
    Client client = Client.find.byId(id);
    if (client == null) {
      return badRequest("No client found with id " + id);
    } 
    return ok(showClient.render(client));
  }

  /* Handles the client check-in functionality */
  @BodyParser.Of(BodyParser.Json.class)
  public static Result checkIn() {
    Result result = ok();
    JsonNode json = request().body().asJson();
    String status = json.findPath("status").getTextValue();
    String uuid = json.findPath("uuid").getTextValue();
    if (status == null || uuid == null) {
      result = badRequest("Missing status or uuid");
    } else {
      Boolean b_status = false;
      if (status.equalsIgnoreCase("ok")) {
        b_status = true;
      }
      Client client = Client.find.where().eq("uuid", uuid).findUnique();
      if (client == null) {
        result = badRequest("No matching uuid");
      } else {
        client.statusOk = b_status;
        client.save(); 
      }
    }
    return result;
  }


}