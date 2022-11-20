package br.ufpb.dcx.tdm.services;

import br.ufpb.dcx.tdm.facade.RetrofitInit;
import br.ufpb.dcx.tdm.notification.ConnectionErrorNotifier;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;

public class RequestFileService {
    private static final Logger LOG = LoggerFactory.getLogger(RequestFileService.class);

    public Integer getClassification(String fileName) {
        Response<Integer> response;
        LOG.warn("Send a request for fileName: {}", fileName);
        try {
            response = new RetrofitInit().fileClassificationService().fileClassification(fileName).execute();
            LOG.warn("Response return with status code {}", response.code());
            if (response.code() == 200) return response.body();
        } catch (IOException ex) {
            ConnectionErrorNotifier.notify(ex.getMessage());
            LOG.error(ex.getMessage());
        }
        return 1000;
    }
}
