package br.ufpb.dcx.tdm.facade;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FileClassificationService {
    @GET("{fileName}")
    Call<File> fileClassification(@Path("fileName") String fileName);
}
