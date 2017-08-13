package ru.stqa.pft.github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import org.testng.annotations.Test;

import java.io.IOException;

/**
 * Created by Пользователь on 13.08.2017.
 */
public class GithubTests {

    @Test

    public void testCommits() throws IOException {
        Github github = new RtGithub("82e7120c36bb502038680df14d82985652096d33");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("sungatullina", "java_pft")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())){
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
