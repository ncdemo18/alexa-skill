package com.netcracker.model;

public interface Constant {
    String START_MESSAGE = "Welcome to Netcracker. How may I help you?";

    String EXIT_MESSAGE = "Good bye.";

    String ERROR_MESSAGE = "Sorry, but server is not available. Please, consult a specialist";

    String UNRECOGNIZED_INTENT = "Sorry, I don't understand you.";

    String SERVER_URL = "https://alexa-control-panel.herokuapp.com/handle_user_request";

    String PARAM_FOR_POST = "userMessage";

}
