package com.guess_apps.advancedandroid.data;

import com.guess_apps.advancedandroid.model.Repo;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RepoService {

    @GET("search/repositories?q=language:java&order=desc&sort=stars")
    Single<TrendingReposResponse> getTrendingRepos();

    @GET("repos/{owner}/{name}")
    Single<Repo>getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);
}
