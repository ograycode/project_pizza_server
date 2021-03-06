package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class CommandModel extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  /* From client.go
  version     string
  description string
  cmd_type    string
  order       int
  exec        string
  validates   []validate_command
  needed_file file
  pass        bool
  err         string
  */

  public String version = "";
  public String description = "";
  public String cmd_type = "";
  @Column(name="Command_Order")
  public int order;
  public String exec = "";
  public Boolean pass = false;
  public String err = "";

  @OneToMany(cascade = CascadeType.ALL)
  public List<ValidateCommand> validates;

  @OneToOne
  public File file;

  @ManyToOne
  @JoinColumn(name="action_id")
  public ActionModel action;

  public static Finder<Long, CommandModel> find = new Finder<Long, CommandModel>(
    Long.class, CommandModel.class
  ); 

}