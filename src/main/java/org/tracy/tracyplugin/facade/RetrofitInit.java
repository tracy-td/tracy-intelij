// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package org.tracy.tracyplugin.facade;

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
 * @see FileClassification
 * @see Retrofit
 */
public class RetrofitInit {
    /**
     * Retrofit reference object responsible for the configuration
     */
    private final Retrofit retrofit;


    private String baseUrl = "http://td/";

    /**
     * Retrofit reference object responsible for the configuration
     */
    IdeaPluginDescriptor pluginConfig = PluginManagerCore.getPlugin(PluginId.getId("org.tracy.tracy-plugin"));

    public RetrofitInit() {
        assert pluginConfig != null;
        baseUrl = this.getBaseUrl();
        System.out.println("BASE URL: " + baseUrl);
        assert baseUrl != null;
        retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
    }

    /**
     * This method is responsible for retrieving the artifact classification using the FileClassificationService interface
     *
     * @see Retrofit#create
     */
    public FileClassification fileClassificationService() {
        return this.retrofit.create(FileClassification.class);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
}
