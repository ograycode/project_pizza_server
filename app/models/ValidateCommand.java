package models;

import javax.persistence.*;

import play.db.ebean.*;
import play.data.format.*;
import play.data.validation.*;

import java.util.List;

@Entity
public class ValidateCommand extends Model {

  @Id
  @Constraints.Min(20)
  public Long id;

  /* from client.go
  description     string
  order           int
  cmd_type        string
  exec            string
  expected_result string
  pass            bool
  err             string
  */

  public String description;
  @Column(name="Validate_Command_Order")
  public int order;
  public String cmd_type;
  public String exec;
  public String expected_result;
  public Boolean pass;
  public String err;

}