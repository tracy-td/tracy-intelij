package br.ufpb.dcx.tdm.facade;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {
    private static final String BASE_URL = "http://localhost:8080/";

    private final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public FileClassificationService fileClassificationService(){
        return this.retrofit.create(FileClassificationService.class);
    }
}
