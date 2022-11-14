package br.ufpb.dcx.tdm.facade;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FileClassificationService {
    @GET("/api/v1/classification")
    Call<Integer> fileClassification(@Query("name") String name);
}
