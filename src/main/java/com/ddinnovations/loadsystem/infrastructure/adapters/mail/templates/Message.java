package com.ddinnovations.loadsystem.infrastructure.adapters.mail.templates;


import com.ddinnovations.loadsystem.domain.entity.enums.EmailTemplate;

public class Message {
    private Message() {

        throw new IllegalStateException("Utility class");
    }

    public static String welcome(String name, String token, EmailTemplate email) {

        return switch (email) {
            case WELCOME -> "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "  <head>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "    <title>Email template</title>\n" +
                    "  </head>\n" +
                    "  <style>\n" +
                    "    @import url(\"https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap\");\n" +
                    "\n" +
                    "    body {\n" +
                    "      width: 100%;\n" +
                    "      height: 100vh;\n" +
                    "      font-family: \"Outfit\", sans-serif;\n" +
                    "      font-optical-sizing: auto;\n" +
                    "      display: grid;\n" +
                    "      place-content: center;\n" +
                    "    }\n" +
                    "\n" +
                    "    div {\n" +
                    "      width: 100%;\n" +
                    "      height: 100vh;\n" +
                    "      border-radius: 16px;\n" +
                    "      display: flex;\n" +
                    "      justify-content: center;\n" +
                    "      align-items: center;\n" +
                    "      gap: 30px;\n" +
                    "      flex-direction: column;\n" +
                    "      position: relative;\n" +
                    "      box-shadow: rgba(0, 0, 0, 0.5) 0 0 10px;\n" +
                    "      padding: 16px 30px;\n" +
                    "      overflow: hidden;\n" +
                    "    }\n" +
                    "\n" +
                    "    h1 {\n" +
                    "      color: #2c654d;\n" +
                    "      font-weight: bold;\n" +
                    "      text-align: center;\n" +
                    "      font-size: 36px;\n" +
                    "    }\n" +
                    "\n" +
                    "    p {\n" +
                    "      color: #2c654d;\n" +
                    "      font-weight: normal;\n" +
                    "      text-align: center;\n" +
                    "      font-size: 14px;\n" +
                    "    }\n" +
                    "\n" +
                    "    /* Button styles */\n" +
                    "    a {\n" +
                    "      width: 300px;\n" +
                    "      text-decoration: none;\n" +
                    "      height: auto;\n" +
                    "      padding: 18px 0;\n" +
                    "      border-radius: 12px;\n" +
                    "      display: inline-flex;\n" +
                    "      justify-content: center;\n" +
                    "      align-items: center;\n" +
                    "      gap: 12px;\n" +
                    "      cursor: pointer;\n" +
                    "      position: relative;\n" +
                    "      overflow: hidden;\n" +
                    "      border: none;\n" +
                    "      background-color: #2c654d;\n" +
                    "    }\n" +
                    "\n" +
                    "    span {\n" +
                    "      color: #ffffff;\n" +
                    "      text-align: center;\n" +
                    "      font-weight: bold;\n" +
                    "      text-transform: capitalize;\n" +
                    "      font-size: 16px;\n" +
                    "      text-decoration: none;\n" +
                    "    }\n" +
                    "\n" +
                    "    @media (min-width: 768px) {\n" +
                    "      p {\n" +
                    "        font-size: 16px;\n" +
                    "        width: 80%;\n" +
                    "        white-space: pre-wrap;\n" +
                    "        overflow: hidden;\n" +
                    "      }\n" +
                    "\n" +
                    "      div {\n" +
                    "        width: 748px;\n" +
                    "        height: auto;\n" +
                    "        padding: 30px;\n" +
                    "      }\n" +
                    "    }\n" +
                    "\n" +
                    "    @media (min-width: 1400px) {\n" +
                    "      div {\n" +
                    "        width: 990px;\n" +
                    "      }\n" +
                    "    }\n" +
                    "  </style>\n" +
                    "\n" +
                    "  <body>\n" +
                    "    <div>\n" +
                    "      <h1>Hola, Diego Alejandro Diaz</h1>\n" +
                    "      <p class=\"templateContainer__paragraph\">\n" +
                    "        Has solicitado la recuperación de contraseña para acceder al sistema de\n" +
                    "        prestamos puedes hacer el cambio dando clic en el botón de abajo.\n" +
                    "      </p>\n" +
                    "      <a href=\"http://localhost:5173/changePassword/"+token+"\">\n" +
                    "        <span>Restablecer Contraseña</span>\n" +
                    "      </a>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>\n";
            default -> "No data";
        };


    }

}

