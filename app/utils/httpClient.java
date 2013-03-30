package utils;

import play.*;

import org.codehaus.jackson.map.ObjectMapper;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Content;
import org.apache.http.entity.ContentType;

public class httpClient {

  public Boolean postJson(Object obj, String url) {
    Boolean result = true;
    ObjectMapper mapper = new ObjectMapper();

    try {
      String value = mapper.writeValueAsString(obj);
      result = postJson(value, url);
    } catch (Exception ex) {
      result = false;
      Logger.error("Exception while getting json: " + ex);
    }
    return result;
  }

  public Boolean postJson(String value, String url) {
    String content = null;
    Boolean result = true;
    try{
      Logger.info("Posting: " + value);
      content = Request.Post(url)
        .bodyString(value, ContentType.APPLICATION_JSON)
        .execute()
        .returnContent()
        .asString();
      Logger.info("Content from post request: " + content);
    } catch (Exception ex) {
      result = false;
      Logger.error("Exception while posting json: " + ex);
    }
    return result;
  }
}