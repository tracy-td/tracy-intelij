package br.ufpb.dcx.tdm.facade;

import br.ufpb.dcx.tdm.utils.ReadPropertiesUtil;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {

    private static final String BASE_URL = ReadPropertiesUtil.getProperty("tracy.plugin.host");//http://td.phoebus.local

    private final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public FileClassificationService fileClassificationService(){
        return this.retrofit.create(FileClassificationService.class);
    }
}
