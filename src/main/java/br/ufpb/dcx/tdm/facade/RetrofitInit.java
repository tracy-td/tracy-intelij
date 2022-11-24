// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.

package br.ufpb.dcx.tdm.facade;

import com.intellij.ide.plugins.IdeaPluginDescriptor;
import com.intellij.ide.plugins.PluginManagerCore;
import com.intellij.openapi.extensions.PluginId;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class responsible for using the interface and creating the call with the service
 * Also this class recovery the Tracy-TD url for the file plugin.xml
 *
 * @author Marcos Ludgerio
 * @see FileClassificationService
 * @see Retrofit
 */
public class RetrofitInit {

    /**
     * Retrofit reference object responsible for the configuration
     */
    private final Retrofit retrofit;

    /**
     * Plugin configuration for recovery the information from file plugin.xml
     */
    IdeaPluginDescriptor pluginConfig = PluginManagerCore.getPlugin(PluginId.getId("org.tracy"));

    /**
     * Default constructor
     * Recovery the base url from configuration file
     * Initialize the retrofit builder and add thr Gson Converter factory
     *
     * @see GsonConverterFactory
     */
    public RetrofitInit() {
        assert pluginConfig != null;
        String BASE_URL = pluginConfig.getVendorUrl();
        assert BASE_URL != null;
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * This method is responsible for retrieving the artifact classification using the FileClassificationService interface
     *
     * @see Retrofit#create
     */
    public FileClassificationService fileClassificationService() {
        return this.retrofit.create(FileClassificationService.class);
    }
}
