package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

import models.*;

import java.util.*;
import java.text.*;

public class Application extends Controller {
  
    public static Result index() {
        List<Client> clientsWhoFailedToCheckIn = Client
          .find
          .where()
          .eq("statusOk", false)
          .setMaxRows(10)
          .findList();

        List<Client> clientsWhoCheckedIn = Client
          .find
          .where()
          .eq("statusOk", true)
          .setMaxRows(10)
          .findList();

        List<ActionModel> upcomingActions = ActionModel
          .find
          .where()
          .ge("execution", new Date())
          .setMaxRows(10)
          .findList();

        List<ActionModel> recentlyExecutedActions = ActionModel
          .find
          .where()
          .le("execution", new Date())
          .setMaxRows(10)
          .findList();

        return ok(index.render(clientsWhoFailedToCheckIn, 
          clientsWhoCheckedIn, 
          upcomingActions, 
          recentlyExecutedActions));
    }
  
}
