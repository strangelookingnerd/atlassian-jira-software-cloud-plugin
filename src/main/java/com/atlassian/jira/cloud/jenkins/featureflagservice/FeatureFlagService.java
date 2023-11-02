package com.atlassian.jira.cloud.jenkins.featureflagservice;

import com.launchdarkly.sdk.LDUser;
import com.launchdarkly.sdk.server.LDClient;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FeatureFlagService {
    private static final String LAUNCH_DARKLY_SDK_KEY = "<need-to-pass-this-in>";
    private static final String PROJECT_KEY = "jenkins-for-jira";
    private final LDClient ldClient;
    private final OkHttpClient httpClient;

    public FeatureFlagService() {
        this.ldClient = new LDClient(LAUNCH_DARKLY_SDK_KEY);
        this.httpClient = new OkHttpClient();
    }

    public boolean getBooleanValue(final String featureFlagKey) {
        LDUser ldUser = new LDUser.Builder(PROJECT_KEY).build();
        return ldClient.boolVariation(featureFlagKey, ldUser, false);
    }

    public String getFeatureFlag(final String featureFlagKey) throws IOException {
        String url =
                "https://app.launchdarkly.com/api/v2/flags/" + PROJECT_KEY + "/" + featureFlagKey;

        Request request =
                new Request.Builder()
                        .url(url)
                        .header("Authorization", "Bearer " + LAUNCH_DARKLY_SDK_KEY)
                        .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            }
        }

        return null;
    }
}
