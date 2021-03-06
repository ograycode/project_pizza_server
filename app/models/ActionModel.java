package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;
import java.util.Date;

@Entity
public class ActionModel extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  @ManyToMany
  public List<ClientGroup> groups;

  public String name;
  public String config;
  public Boolean executed;


  @Formats.DateTime(pattern="dd/MM/yyyy hh:mm:ss")
  public Date uploaded = new Date();

  @Formats.DateTime(pattern="dd/MM/yyyy hh:mm:ss")
  public Date execution = new Date();

  @OneToMany(cascade = CascadeType.ALL, mappedBy="action")
  public List<CommandModel> commands;

  public static Finder<Long,ActionModel> find = new Finder<Long, ActionModel>(
    Long.class, ActionModel.class
  ); 
}