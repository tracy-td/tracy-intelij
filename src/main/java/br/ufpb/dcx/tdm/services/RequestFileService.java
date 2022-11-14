package br.ufpb.dcx.tdm.services;

import br.ufpb.dcx.tdm.facade.File;
import br.ufpb.dcx.tdm.facade.RetrofitInit;
import com.intellij.openapi.diagnostic.Logger;
import okhttp3.Request;
import retrofit2.Response;

import java.io.IOException;

public class RequestFileService {
    private static final Logger LOG = Logger.getInstance(RequestFileService.class);

    public Integer getClassification(String fileName) {
        Response<File> response;
        File fileReturn = new File();
        try {
            Request request = new RetrofitInit().fileClassificationService().fileClassification(fileName).request();
            System.out.println(request.url());
            response = new RetrofitInit().fileClassificationService().fileClassification(fileName).execute();
            System.out.println(response.code());
            assert response.body() != null;
            fileReturn = response.body();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return fileReturn.getClassification();
    }

}
