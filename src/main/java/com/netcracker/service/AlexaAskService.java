package com.netcracker.service;

import com.netcracker.handlers.MainHandler;
import com.netcracker.model.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AlexaAskService {

    private static final Logger logger = LogManager.getLogger(AlexaAskService.class);

    public static String askAlexa(String message) {
        String alexaAnswer = Constant.ERROR_MESSAGE;
        try {
            alexaAnswer = sendRequest(message);
        } catch (IOException e) {
            logger.error("Error in askAlexa: {}", e);
        }
        logger.info("Result for alexaAnswer: {}", alexaAnswer);
        return alexaAnswer;
    }

    private static String sendRequest(String message) throws IOException {
        URL url = new URL(Constant.SERVER_URL);
        byte[] postDataBytes = transformPostData(message);

        logger.info("Open connection on url = {}", Constant.SERVER_URL);
        HttpURLConnection alexaConnection = (HttpURLConnection)url.openConnection();

        alexaConnection.setRequestMethod("POST");
        alexaConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        alexaConnection.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        alexaConnection.setDoOutput(true);
        alexaConnection.getOutputStream().write(postDataBytes);

        logger.info("Set POST param, send request");

        return getServerAnswer(alexaConnection);
    }

    private static byte[] transformPostData(String message) throws IOException {
        String paramForRequest = URLEncoder.encode(Constant.PARAM_FOR_POST, "UTF-8") + '=' + URLEncoder.encode(message, "UTF-8");
        logger.info("Param for request: {}", paramForRequest);

        return paramForRequest.getBytes(StandardCharsets.UTF_8);
    }

    private static String getServerAnswer(HttpURLConnection alexaConnection) throws IOException {
        BufferedReader inputStream = new BufferedReader(new InputStreamReader(alexaConnection.getInputStream()));
        StringBuilder result = new StringBuilder();
        String inputLine;
        while ((inputLine = inputStream.readLine()) != null){
            result.append(inputLine);
            logger.info("Get string from server: {}", inputLine);
        }
        inputStream.close();

        logger.info("Close connection");

        return result.toString();
    }
}
