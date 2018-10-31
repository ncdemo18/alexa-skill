package com.netcracker.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.netcracker.service.AlexaAskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class AnswerIntentHandler implements RequestHandler {

    private final Logger logger = LogManager.getLogger(AnswerIntentHandler.class);

    @Override
    public boolean canHandle(HandlerInput handlerInput) {
        return handlerInput.matches(Predicates.intentName("AnswerIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput) {
        IntentRequest request = (IntentRequest) handlerInput.getRequestEnvelope().getRequest();
        String slotValue = request.getIntent().getSlots().get("Text").getValue();

        logger.info("Handle in AnswerIntentHandler for slotValue = '{}'", slotValue);

        String speechText = AlexaAskService.askAlexa(slotValue);

        logger.info("Send from AnswerIntentHandler: {}", speechText);

        return handlerInput.getResponseBuilder().withSpeech(speechText).withShouldEndSession(false).build();
    }
}
