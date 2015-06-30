package com.apwglobal.allegro.client.json;

import com.apwglobal.nice.exception.AllegroException;
import retrofit.ErrorHandler;
import retrofit.RetrofitError;

public class AllegroErrorHandler implements ErrorHandler {
    @Override
    public Throwable handleError(RetrofitError cause) {
        if (cause.getKind() == RetrofitError.Kind.HTTP && cause.getResponse().getStatus() == 500) {
            return (Throwable) cause.getBodyAs(AllegroException.class);
        } else {
            return cause.getCause();
        }
    }
}
