package com.apwglobal.allegro.client;

import com.apwglobal.allegro.client.json.OptionalTypeAdapterFactory;
import com.apwglobal.allegro.client.service.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.ConnectionPool;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import java.util.Base64;

public class AllegroClient {

    private long clientId;

    private IAuctionService auctionService;
    private IDealService dealService;
    private IJournalService journalService;
    private IFormFieldService formFieldService;
    private IPaymentService paymentService;
    private IFeedbackService feedbackService;

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
    public IFeedbackService getFeedbackService() {
        return feedbackService;
    }
    public long getClientId() {
        return clientId;
    }


    public static class Builder {
        private String endpoint;
        private String username;
        private String password;
        private long clientId;
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

        public Builder clientId(long clientId) {
            this.clientId = clientId;
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
            client.clientId = clientId;

            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectionPool(connectionPool);

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(new OptionalTypeAdapterFactory())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                    .create();

            RestAdapter restAdapter = new RestAdapter.Builder()
                    .setEndpoint(endpoint)
                    .setClient(new OkClient(okHttpClient))
                    .setConverter(new GsonConverter(gson))
                    .setLogLevel(logLevel)
                    .setRequestInterceptor(new BasicAuthInterceptor(username, password))
                    .build();

            client.auctionService = restAdapter.create(IAuctionService.class);
            client.dealService = restAdapter.create(IDealService.class);
            client.journalService = restAdapter.create(IJournalService.class);
            client.formFieldService = restAdapter.create(IFormFieldService.class);
            client.paymentService = restAdapter.create(IPaymentService.class);
            client.feedbackService = restAdapter.create(IFeedbackService.class);

            return client;
        }

        private class BasicAuthInterceptor implements RequestInterceptor {

            private String username;
            private String password;

            public BasicAuthInterceptor(String username, String password) {
                this.username = username;
                this.password = password;
            }

            @Override
            public void intercept(RequestFacade requestFacade) {
                String authorizationValue = encodeCredentialsForBasicAuthorization();
                requestFacade.addHeader("Authorization", authorizationValue);
            }
            private String encodeCredentialsForBasicAuthorization() {
                final String userAndPassword = username + ":" + password;
                return "Basic " + Base64.getEncoder().encodeToString(userAndPassword.getBytes());
            }
        }

    }

}
