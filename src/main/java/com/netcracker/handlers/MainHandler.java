package com.netcracker.handlers;

import com.amazon.ask.Skill;
import com.amazon.ask.builder.StandardSkillBuilder;
import com.amazon.ask.model.RequestEnvelope;
import com.amazon.ask.model.ResponseEnvelope;
import com.amazon.ask.util.JacksonSerializer;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainHandler implements RequestStreamHandler {

    private final Logger logger = LogManager.getLogger(MainHandler.class);

    private final Skill skill;
    private final JacksonSerializer serializer;

    public MainHandler() {
        skill = new StandardSkillBuilder()
                .addRequestHandlers(
                        new AnswerIntentHandler(),
                        new StopIntentHandler(),
                        new DefaultHandler())
                .build();
        serializer = new JacksonSerializer();
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
        String request = IOUtils.toString(inputStream);
        logger.info("Received request: {}", request);

        RequestEnvelope requestEnvelope = serializer.deserialize(request, RequestEnvelope.class);
        ResponseEnvelope responseEnvelope = skill.invoke(requestEnvelope);

        String response = serializer.serialize(responseEnvelope);
        logger.info("Send response: {}", response);

        outputStream.write(response.getBytes(StandardCharsets.UTF_8));
    }
}
