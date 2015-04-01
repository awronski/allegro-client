package com.apwglobal.allegro.client;

import com.apwglobal.allegro.client.json.OptionalTypeAdapterFactory;
import com.apwglobal.allegro.client.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.*;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.io.IOException;
import java.net.Proxy;

public class AllegroClient {

    private IAuctionService auctionService;
    private IDealService dealService;
    private IJournalService journalService;
    private IFormFieldService formFieldService;
    private IPaymentService paymentService;

    public IAuctionService getAuctionService() {
        return auctionService;
    }
    public IDealService getDealService() {
        return dealService;
    }
    public IJournalService getJournalService() {
        return journalService;
    }
    public IFormFieldService getFormFieldService() {
        return formFieldService;
    }
    public IPaymentService getPaymentService() {
        return paymentService;
    }

    public static class Builder {
        private String endpoint;
        private String username;
        private String password;
        private RestAdapter.LogLevel logLevel = RestAdapter.LogLevel.BASIC;
        private ConnectionPool connectionPool;

        public Builder endpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder logLevel(RestAdapter.LogLevel logLevel) {
            this.logLevel = logLevel;
            return this;
        }

        public Builder connectionPool(ConnectionPool connectionPool) {
            this.connectionPool = connectionPool;
            return this;
        }

        public AllegroClient build() {
            if (connectionPool == null) {
                this.connectionPool = new ConnectionPool(5, 5 * 60 * 1000);
            }

            AllegroClient client = new AllegroClient();

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectionPool(connectionPool);
            okHttpClient.setAuthenticator(new Authenticator() {
                @Override
                public Request authenticate(Proxy proxy, Response response) throws IOException {
                    String basic = Credentials.basic(username, password);
                    return response.request().newBuilder()
                            .header("Authorization", basic)
                            .build();
                }

                @Override
                public Request authenticateProxy(Proxy proxy, Response response) throws IOException {
                    return null;
                }
            });
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new OptionalTypeAdapterFactory())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .setClient(new OkClient(okHttpClient))
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(logLevel)
                    .build();

            client.auctionService = restAdapter.create(IAuctionService.class);
            client.dealService = restAdapter.create(IDealService.class);
            client.journalService = restAdapter.create(IJournalService.class);
            client.formFieldService = restAdapter.create(IFormFieldService.class);
            client.paymentService = restAdapter.create(IPaymentService.class);

            return client;
        }
    }

}
