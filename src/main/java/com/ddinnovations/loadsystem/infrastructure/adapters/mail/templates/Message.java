package com.ddinnovations.loadsystem.infrastructure.adapters.mail.templates;


import com.ddinnovations.loadsystem.domain.entity.enums.EmailTemplate;

public class Message {
    private Message() {

        throw new IllegalStateException("Utility class");
    }

    public static String welcome(String name, String token, EmailTemplate template) {

        return switch (template) {
            case WELCOME -> "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "  <head>\n" +
                    "    <meta charset=\"UTF-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\n" +
                    "    <title>Recuperacion de contraseña</title>\n" +
                    "    <style>\n" +
                    "      @import url(\"https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap\");\n" +
                    "      body {\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "        font-family: sans-serif;\n" +
                    "        font-family: \"Outfit\", sans-serif;\n" +
                    "      }\n" +
                    "\n" +
                    "      table {\n" +
                    "        width: 100%;\n" +
                    "        max-width: 600px;\n" +
                    "        margin: 0 auto;\n" +
                    "      }\n" +
                    "\n" +
                    "      td {\n" +
                    "        padding: 10px;\n" +
                    "        text-align: center;\n" +
                    "      }\n" +
                    "\n" +
                    "      h1 {\n" +
                    "        color: #2c654d;\n" +
                    "        font-weight: bold;\n" +
                    "        text-align: center;\n" +
                    "        font-size: 36px;\n" +
                    "      }\n" +
                    "\n" +
                    "      p {\n" +
                    "        color: #2c654d;\n" +
                    "        font-weight: normal;\n" +
                    "        text-align: center;\n" +
                    "        font-size: 14px;\n" +
                    "        margin-bottom: 2rem;\n" +
                    "      }\n" +
                    "      .link {\n" +
                    "        width: 100px;\n" +
                    "        padding: 1rem;\n" +
                    "        text-decoration: none;\n" +
                    "        border-radius: 12px;\n" +
                    "        cursor: pointer;\n" +
                    "        overflow: hidden;\n" +
                    "        border: none;\n" +
                    "        background-color: #2c654d;\n" +
                    "      }\n" +
                    "\n" +
                    "      span {\n" +
                    "        color: #ffffff;\n" +
                    "        text-align: center;\n" +
                    "        font-weight: bold;\n" +
                    "        text-transform: capitalize;\n" +
                    "        font-size: 16px;\n" +
                    "        text-decoration: none;\n" +
                    "      }\n" +
                    "    </style>\n" +
                    "  </head>\n" +
                    "\n" +
                    "  <body>\n" +
                    "    <table>\n" +
                    "      <tr>\n" +
                    "        <td>\n" +
                    "          <h1>Hola, "+name+"</h1>\n" +
                    "          <p class=\"templateContainer__paragraph\">\n" +
                    "            Has solicitado la recuperación de contraseña para acceder al sistema\n" +
                    "            de prestamos puedes hacer el cambio dando clic en el botón de abajo.\n" +
                    "          </p>\n" +
                    "          <a class=\"link\" href=\"https://www.cashmoneyrd.com/changePassword/"+token+"\">\n" +
                    "            <span>Restablecer Contraseña</span>\n" +
                    "          </a>\n" +
                    "        </td>\n" +
                    "      </tr>\n" +
                    "    </table>\n" +
                    "  </body>\n" +
                    "</html>\n";
            default -> "No data";
        };


    }

}

