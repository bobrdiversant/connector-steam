package ru.dmm.steamservice.http;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.SocketTimeoutException;

/**
 * Created by Dmitry
 */
public class LoggingInterceptor implements Interceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingInterceptor.class);

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;

        try {
            response = chain.proceed(request);
            trackAll(request, response);



           /* if (!response.isSuccessful()) {
                response = trackError(request, response);
            }*/
        } catch (SocketTimeoutException e) {
            trackError(request, "Timeout", e.getMessage());
            throw e;
        } catch (IOException e) {
            trackError(request, "Device Connection Failure", e.getMessage());
            throw e;
        }

        return response;
    }

    private Response trackError(Request request, Response response) throws IOException {
        String responseBodyString = response.body().string();
        String cause = String.valueOf(response.code());
        String errorMessage;
        final Response newResponse;

        try {
            errorMessage = "";
//            ErrorResponse errorResponse = mGson.fromJson(responseBodyString, ErrorResponse.class);
//            errorMessage = errorResponse.getMessage();
        } catch (NullPointerException e) {
            errorMessage = "Unknown error";
        } finally {
            ResponseBody body = ResponseBody.create(response.body().contentType(), responseBodyString);
            newResponse = response.newBuilder()
                    .body(body)
                    .build();
        }

        trackError(request, cause, errorMessage);
        return newResponse;
    }

    private void trackError(Request request, String cause, String message) {
        String url = request.url().toString();
        String method = request.method();
        LOGGER.debug("Ошибка в {} метод {} с сообщением {} и ошибкой {}", url, method, message, cause);
    }

    private void trackAll(Request request, Response response) throws IOException {
        String url = request.url().toString();
        String method = request.method();

        LOGGER.debug("-----------------------------------------------------");
        LOGGER.debug("REQUEST {}", request.toString());
        LOGGER.debug("Headers REQUEST {}", request.headers().toString());
        LOGGER.debug("RESPONSE {}", response.toString());
        LOGGER.debug("Headers RESPONSE {}", response.headers().toString());
        LOGGER.debug("-----------------------------------------------------");

    }
}