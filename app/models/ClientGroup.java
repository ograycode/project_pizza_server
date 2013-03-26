package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class ClientGroup extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  @OneToMany(cascade = CascadeType.ALL)
  public List<Client> clients;

  @ManyToMany
  public List<ActionModel> actions;

  public String name;

  public static Finder<Long,ClientGroup> find = new Finder<Long, ClientGroup>(
    Long.class, ClientGroup.class
  ); 
}