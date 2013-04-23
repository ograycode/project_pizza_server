package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class Message extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  public String version;

 // @OneToMany(cascade = CascadeType.ALL)
 // public List<CommandModel> commands;
}