package com.netcracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.netcracker.model.Constant;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class StopIntentHandler implements RequestHandler {

    private final Logger logger = LogManager.getLogger(StopIntentHandler.class);

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.StopIntent")
                .or(Predicates.intentName("AMAZON.PauseIntent")
                        .or(Predicates.intentName("AMAZON.CancelIntent"))));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {

        logger.info("Handle in StopIntentHandler, stop session and return EXIT_MESSAGE: {}", Constant.EXIT_MESSAGE);

        return input.getResponseBuilder().withSpeech(Constant.EXIT_MESSAGE).withShouldEndSession(true).build();
    }
}