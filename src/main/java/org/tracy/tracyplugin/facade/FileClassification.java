// Copyright 2022 Ayty. Use of this source code is governed by the license that can be found in the LICENSE file.
package org.tracy.tracyplugin.facade;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * This interface is the connection with Tracy-TD
 * All calls and endpoints must be here
 *
 * @author Marcos Ludgerio
 * @see <a href="https://square.github.io/retrofit/">Retrofit Docs</a> for details.
 */
public interface FileClassification {
    /*
     * This method is called to get the artifact's priority
     *
     * @param name is a string and is the artifact's name
     * @return an integer representing the artifact's priority level
     */
    @GET("/api/v1/classification")
    Call<Integer> fileClassification(@Query("name") String name);
}
