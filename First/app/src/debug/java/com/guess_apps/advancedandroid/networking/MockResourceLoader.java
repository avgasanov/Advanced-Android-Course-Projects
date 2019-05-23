package com.guess_apps.advancedandroid.networking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.Nullable;

import timber.log.Timber;

class MockResourceLoader {

    private MockResourceLoader() {

    }

    @Nullable
    static String getResponseString(Context context, String method, String[] endpointParts) {
        try {
            String currentPath = "mock";
            Set<String> mockList = new HashSet<>(Arrays.asList(context.getAssets().list(currentPath)));
            for(String endpointPart : endpointParts) {
                if(mockList.contains(endpointPart)) {
                    currentPath = currentPath + "/" + endpointPart;
                    mockList = new HashSet<>(Arrays.asList(context.getAssets().list(currentPath)));
                }
            }

            String finalPath = null;
            for(String path : mockList) {
                if(path.contains(method.toLowerCase())) {
                    finalPath = currentPath + "/" + path;
                    break;
                }
            }

            if(finalPath != null) {
                return responseFromPath(context, finalPath);
            }
            return null;
        } catch (IOException e) {
            Timber.e(e, "Error loading mock response from assets");
            return null;
        }
    }

    private static String responseFromPath(Context context, String path) {
        StringBuilder sb = new StringBuilder();
        try(InputStream assetStream = context.getAssets().open(path)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(assetStream));
            String line;
            while((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
