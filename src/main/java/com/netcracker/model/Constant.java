package com.netcracker.model;

public interface Constant {
    String START_MESSAGE = "Welcome to Netcracker. How may I help you?";

    String EXIT_MESSAGE = "Good bye.";

    String ERROR_MESSAGE = "Sorry, but server is not available. Please, consult a specialist";

    String UNRECOGNIZED_INTENT = "Sorry, I don't understand you.";

    String SERVER_HANDLE_URL = "https://alexa-control-panel.herokuapp.com/handle_user_request";

    String SERVER_START_PAGE_URL = "https://alexa-control-panel.herokuapp.com/user/ricky/set_page?number_page=1";

    String PARAM_FOR_POST = "userMessage";

}
