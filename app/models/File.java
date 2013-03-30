package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class File extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  /* from client.go
  url         string
  destination string
  name        string
  */

  public String url;
  public String destination;
  public String name;

}