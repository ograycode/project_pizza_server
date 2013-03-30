package test;

import org.junit.*;
import static org.junit.Assert.*;

import play.mvc.*;
import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;

import models.*;
import utils.*;

public class httpClientTest {
  
  @Test
  public void httpPostObjTest() {
    Message msg = new Message();
    msg.version = "1.0";
    httpClient http = new httpClient();
    Boolean result = http.postJson(msg, "http://localhost:8082/command");
    assertTrue(result);
  }

}