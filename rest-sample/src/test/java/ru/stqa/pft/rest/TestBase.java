package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;
import java.util.Set;



public class TestBase {

  protected Executor getExecutor(){
    return Executor.newInstance().auth("LSGjeU4yP1X493ud1hNniA==", "");
  }


 public boolean isIssueOpen(int issueId) throws IOException {
   Issue issue = getIssue(issueId);
   String status = issue.getStateName();
    if (status.equals("Resolved") || status.equals("Closed")) {
      return false;
    } else {
      return true;
    }
  }

  private Issue getIssue(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues/" + String.valueOf(issueId) + ".json"))
            .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement jsonIssues = parsed.getAsJsonObject().get("issues");
    Set<Issue> issues = new Gson().fromJson(jsonIssues, new TypeToken<Set<Issue>>(){}.getType());
    return issues.iterator().next();
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }
}


