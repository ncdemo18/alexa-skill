package com.netcracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.netcracker.model.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class DefaultHandler implements RequestHandler {

    private final Logger logger = LogManager.getLogger(DefaultHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        String speechText = Constant.START_MESSAGE;

        logger.info("Handle in DefaultHandler, return START_MESSAGE: {}", Constant.START_MESSAGE);

        return handlerInput.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }
}
