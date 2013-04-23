package controllers;

import play.*;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;

import org.codehaus.jackson.JsonNode;

import views.html.command.*;
import views.html.*;

import models.*;

import java.util.List;

public class CommandController extends Controller {
  /**
  * Defines a form wrapping the CommandModel class.
  */ 
  private static Form<CommandModel> form = form(CommandModel.class);

  /* Base for /commands and lists all commands */
  public static Result all() {
    List<CommandModel> commands = CommandModel.find.all();
    return ok(list.render(commands));
  }

  /* Adds the ability to delete by id */
  public static Result delete(Long id) {
    CommandModel command = CommandModel.find.byId(id);
    if (command != null) {
      command.delete();
      return redirect(routes.CommandController.all());
    }
    return badRequest("Unable to find command with id " + id);
  }

  /* Renders the form for a new command */
  public static Result newForm() {
    List<ActionModel> models = ActionModel.find.all();
    return ok(newForm.render(form, models));
  }

  /* Accepts new command from form */
  public static Result newCommand() {
    Form<CommandModel> filledForm = form.bindFromRequest();
    Logger.info("Request: " + filledForm.toString());
    CommandModel command = new CommandModel();
    command.cmd_type = filledForm.field("cmd_type").value();
    command.exec = filledForm.field("exec").value();
    command.order = new Integer(filledForm.field("order").value());
    command.version = filledForm.field("version").value();
    command.description = filledForm.field("description").value();

    ActionModel action = ActionModel.find.byId(1l);

    action.commands.add(command);

    action.save();
    return redirect(routes.Application.index());
  }

  /* Edits command based upon the form */
  public static Result edit() {
    CommandModel command = form.bindFromRequest().get();
    command.update();
    return redirect(routes.CommandController.all()); 
  }

  /* Renders edit command form */
  public static Result editForm(Long id) {
    CommandModel command = CommandModel.find.byId(id);
    form = form.fill(command);
    return ok(edit.render(form));
  }

}