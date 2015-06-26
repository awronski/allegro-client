package com.apwglobal.allegro.client.service;

import com.apwglobal.allegro.client.AllegroClient;
import org.junit.BeforeClass;
import retrofit.RestAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AbstractIntegrationTest {

    protected static AllegroClient client;

    @BeforeClass
    public static void abstractServiceSetup() throws IOException {
        Properties properties = new Properties();
        InputStream resourceAsStream = AbstractIntegrationTest.class.getResourceAsStream("/integration-test.properties");
        properties.load(resourceAsStream);

        String address = properties.getProperty("test.server.address");
        String user = properties.getProperty("test.server.user");
        String password = properties.getProperty("test.server.password");

        client = new AllegroClient.Builder()
                .endpoint(address)
                .username(user)
                .password(password)
                .logLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

}
