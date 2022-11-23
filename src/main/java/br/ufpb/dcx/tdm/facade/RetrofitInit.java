package br.ufpb.dcx.tdm.facade;

import br.ufpb.dcx.tdm.utils.ReadPropertiesUtil;
import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {


    IdeaPluginDescriptor pluginConfig = PluginManagerCore.getPlugin(PluginId.getId("org.tracy"));
    private final String BASE_URL;

    {
        assert pluginConfig != null;
        BASE_URL = pluginConfig.getVendorUrl();
    }

    private final Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    public FileClassificationService fileClassificationService() {
        return this.retrofit.create(FileClassificationService.class);
    }
}
