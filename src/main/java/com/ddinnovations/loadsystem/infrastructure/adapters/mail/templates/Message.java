package com.ddinnovations.loadsystem.infrastructure.adapters.mail.templates;


import com.ddinnovations.loadsystem.domain.entity.enums.EmailTemplate;

public class Message {
    private Message() {

        throw new IllegalStateException("Utility class");
    }

    public static String welcome(String name, EmailTemplate email) {

        return switch (email) {
            case WELCOME -> "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                    "    <title>Bet billions</title>\n" +
                    "    <style>\n" +
                    "        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap');\n" +
                    "\n" +
                    "        body {\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        table {\n" +
                    "            width: 100%;\n" +
                    "            max-width: 600px;\n" +
                    "            margin: 0 auto;\n" +
                    "            background-image: url(https://res.cloudinary.com/ddd95zydp/image/upload/v1687887971/djfggdbmv4zeluds1yfa.png);\n" +
                    "            background-repeat: no-repeat;\n" +
                    "            background-size: cover;\n" +
                    "        }\n" +
                    "\n" +
                    "        td {\n" +
                    "            padding: 10px;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1,\n" +
                    "        h2,\n" +
                    "        h3,\n" +
                    "        p {\n" +
                    "            font-family: 'Poppins', sans-serif;\n" +
                    "            font-size: 16px;\n" +
                    "            margin: 0;\n" +
                    "            padding: 0;\n" +
                    "        }\n" +
                    "\n" +
                    "        h1 {\n" +
                    "            font-size: 2rem;\n" +
                    "        }\n" +
                    "\n" +
                    "        h2 {\n" +
                    "            font-size: 1rem;\n" +
                    "        }\n" +
                    "\n" +
                    "        h3 {\n" +
                    "            font-size: 16px;\n" +
                    "            padding: 2rem;\n" +
                    "            text-align: center;\n" +
                    "        }\n" +
                    "\n" +
                    "        a {\n" +
                    "            text-decoration: none;\n" +
                    "            padding: 1rem;\n" +
                    "            font-family: 'Poppins', sans-serif;\n" +
                    "            border-radius: 20px;\n" +
                    "            background-color: #b2fbc6;\n" +
                    "            cursor: pointer;\n" +
                    "            margin-top: 2rem;\n" +
                    "            display: inline-block;\n" +
                    "        }\n" +
                    "\n" +
                    "        img {\n" +
                    "            height: auto;\n" +
                    "            max-width: 100%;\n" +
                    "            display: block;\n" +
                    "            margin: 0 auto;\n" +
                    "        }\n" +
                    "    </style>\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<table>\n" +
                    "    <tr>\n" +
                    "        <td>\n" +
                    "            <img src=\"https://res.cloudinary.com/ddd95zydp/image/upload/v1687879055/BetBillions-images/eqktlycxsmsmakhgnr7k.png\" alt=\"Logo\">\n" +
                    "            <h1>¡Hola " + name + "!</h1>\n" +
                    "            <h2>Te damos la bienvenida a nuestra casa de juegos</h2>\n" +
                    "            <h3>\n" +
                    "                Preparate para sumergirte en una experiencia excepcional, donde entrarás\n" +
                    "                en la mejor oportunidad de generar ingresos adicionales. Únete a la\n" +
                    "                plataforma digital líder en juegos de azar y disfruta de la emoción en\n" +
                    "                cada apuesta.\n" +
                    "            </h3>\n" +
                    "        </td>\n" +
                    "    </tr>\n" +
                    "</table>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";
            default -> "No data";
        };


    }

}

