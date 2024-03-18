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
                    "  <!-- Styles -->\n" +
                    "<style>\n" +
                    "  @import url(\"https://fonts.googleapis.com/css2?family=Outfit:wght@100..900&display=swap\");\n" +
                    "\n" +
                    "  :root {\n" +
                    "    --white: #ffffff;\n" +
                    "    --primary: #2c654d;\n" +
                    "    --secondary: #56beb8;\n" +
                    "    --gray-dark: #43494b;\n" +
                    "    --gray-base: #78909c;\n" +
                    "    --gray-light: #cfd8dc;\n" +
                    "    --red: #e53535;\n" +
                    "    --warning: #e06f2d;\n" +
                    "    --bg-color: linear-gradient(180deg, var(--white) 80%, var(--primary) 100%);\n" +
                    "    --overlay-color: rgba(0, 0, 0, 0.5);\n" +
                    "    --button-hover-color: rgba(0, 0, 0, 0.3);\n" +
                    "  }\n" +
                    "\n" +
                    "  * {\n" +
                    "    margin: 0;\n" +
                    "    padding: 0;\n" +
                    "    box-sizing: border-box;\n" +
                    "  }\n" +
                    "\n" +
                    "  body {\n" +
                    "    width: 100%;\n" +
                    "    height: 100vh;\n" +
                    "    font-family: \"Outfit\", sans-serif;\n" +
                    "    font-optical-sizing: auto;\n" +
                    "    display: grid;\n" +
                    "    place-content: center;\n" +
                    "    overflow-y: hidden;\n" +
                    "    background-image: var(--bg-color);\n" +
                    "    background-repeat: no-repeat;\n" +
                    "    background-size: cover;\n" +
                    "    background-attachment: fixed;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer {\n" +
                    "    width: 100%;\n" +
                    "    height: 100vh;\n" +
                    "    border-radius: 16px;\n" +
                    "    display: flex;\n" +
                    "    justify-content: center;\n" +
                    "    align-items: center;\n" +
                    "    gap: 30px;\n" +
                    "    flex-direction: column;\n" +
                    "    position: relative;\n" +
                    "    box-shadow: var(--overlay-color) 0 0 10px;\n" +
                    "    padding: 16px 30px;\n" +
                    "    overflow: hidden;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__logoContainer {\n" +
                    "    display: flex;\n" +
                    "    justify-content: center;\n" +
                    "    align-items: center;\n" +
                    "    flex-direction: column;\n" +
                    "    gap: 16px;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__logoContainer__logo {\n" +
                    "    width: 80px;\n" +
                    "    height: 80px;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__logoContainer__text {\n" +
                    "    font-size: 30px;\n" +
                    "    font-weight: 500;\n" +
                    "    text-align: center;\n" +
                    "    color: var(--primary);\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__svg1 {\n" +
                    "    width: 90px;\n" +
                    "    height: 97px;\n" +
                    "    position: absolute;\n" +
                    "    top: 10px;\n" +
                    "    right: 10px;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__svg2 {\n" +
                    "    width: 90px;\n" +
                    "    height: 97px;\n" +
                    "    position: fixed;\n" +
                    "    bottom: 20px;\n" +
                    "    left: 20px;\n" +
                    "    opacity: 0;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__title {\n" +
                    "    color: var(--primary);\n" +
                    "    font-weight: bold;\n" +
                    "    text-align: center;\n" +
                    "    font-size: 36px;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__paragraph {\n" +
                    "    color: var(--primary);\n" +
                    "    font-weight: normal;\n" +
                    "    text-align: center;\n" +
                    "    font-size: 14px;\n" +
                    "  }\n" +
                    "\n" +
                    "  /* Button styles */\n" +
                    "  .templateContainer__button {\n" +
                    "    width: 300px;\n" +
                    "    height: auto;\n" +
                    "    padding: 18px 0;\n" +
                    "    border-radius: 12px;\n" +
                    "    display: inline-flex;\n" +
                    "    justify-content: center;\n" +
                    "    align-items: center;\n" +
                    "    gap: 12px;\n" +
                    "    cursor: pointer;\n" +
                    "    position: relative;\n" +
                    "    overflow: hidden;\n" +
                    "    border: none;\n" +
                    "    background-color: var(--primary);\n" +
                    "\n" +
                    "    &::before {\n" +
                    "      content: \"\";\n" +
                    "      background-color: var(--button-hover-color);\n" +
                    "      width: 100%;\n" +
                    "      height: 100%;\n" +
                    "      top: 0;\n" +
                    "      left: 0;\n" +
                    "      position: absolute;\n" +
                    "      transition: transform ease 0.6s;\n" +
                    "      transform: translateX(-110%);\n" +
                    "    }\n" +
                    "\n" +
                    "    &:active::before {\n" +
                    "      transform: translateX(0);\n" +
                    "    }\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__button__icon {\n" +
                    "    color: var(--white);\n" +
                    "    width: 20px;\n" +
                    "    height: 20px;\n" +
                    "    transition: transform 0.6s ease;\n" +
                    "  }\n" +
                    "\n" +
                    "  .templateContainer__button__span {\n" +
                    "    color: var(--white);\n" +
                    "    text-align: center;\n" +
                    "    font-weight: bold;\n" +
                    "    text-transform: capitalize;\n" +
                    "    font-size: 16px;\n" +
                    "  }\n" +
                    "\n" +
                    "  @media (min-width: 768px) {\n" +
                    "    .templateContainer__svg1 {\n" +
                    "      position: fixed;\n" +
                    "    }\n" +
                    "    .templateContainer__svg2 {\n" +
                    "      width: 100px;\n" +
                    "      height: 108px;\n" +
                    "      opacity: 1;\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer__button__icon {\n" +
                    "      width: 24px;\n" +
                    "      height: 24px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer__button {\n" +
                    "      width: 350px;\n" +
                    "      padding: 24px 0;\n" +
                    "\n" +
                    "      &:hover::before {\n" +
                    "        transform: translateX(0);\n" +
                    "      }\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer__paragraph {\n" +
                    "      font-size: 16px;\n" +
                    "      width: 80%;\n" +
                    "      white-space: pre-wrap;\n" +
                    "      overflow: hidden;\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer__logoContainer__logo {\n" +
                    "      width: 116px;\n" +
                    "      height: 116px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer {\n" +
                    "      width: 748px;\n" +
                    "      height: auto;\n" +
                    "      padding: 30px;\n" +
                    "    }\n" +
                    "  }\n" +
                    "\n" +
                    "  @media (min-width: 1400px) {\n" +
                    "    .templateContainer {\n" +
                    "      width: 990px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .templateContainer__svg1 {\n" +
                    "      width: 156px;\n" +
                    "      height: 169px;\n" +
                    "    }\n" +
                    "  }\n" +
                    "</style>\n" +
                    "\n" +
                    "  <body>\n" +
                    "    <div class=\"templateContainer\">\n" +
                    "      <svg\n" +
                    "        class=\"templateContainer__svg1\"\n" +
                    "        viewBox=\"0 0 156 169\"\n" +
                    "        fill=\"none\"\n" +
                    "        xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "      >\n" +
                    "        <path d=\"M156 60H96V0H156V60Z\" fill=\"var(--primary)\" />\n" +
                    "        <path d=\"M154 130H104V80H154V130Z\" fill=\"var(--primary)\" />\n" +
                    "        <path d=\"M84 50H34V0H84V50Z\" fill=\"var(--primary)\" />\n" +
                    "        <path d=\"M84 120H44V80H84V120Z\" fill=\"var(--primary)\" />\n" +
                    "        <path d=\"M151 169H131V149H151V169Z\" fill=\"var(--primary)\" />\n" +
                    "        <path d=\"M20 20H0V0H20V20Z\" fill=\"var(--primary)\" />\n" +
                    "      </svg>\n" +
                    "      <figure class=\"templateContainer__logoContainer\">\n" +
                    "        <svg\n" +
                    "          width=\"120\"\n" +
                    "          height=\"120\"\n" +
                    "          viewBox=\"0 0 121 120\"\n" +
                    "          fill=\"none\"\n" +
                    "          xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "          class=\"templateContainer__logoContainer__logo\"\n" +
                    "        >\n" +
                    "          <rect x=\"2.5\" y=\"2\" width=\"116\" height=\"116\" rx=\"58\" fill=\"#2C654D\" />\n" +
                    "          <rect\n" +
                    "            x=\"2.5\"\n" +
                    "            y=\"2\"\n" +
                    "            width=\"116\"\n" +
                    "            height=\"116\"\n" +
                    "            rx=\"58\"\n" +
                    "            stroke=\"white\"\n" +
                    "            stroke-width=\"4\"\n" +
                    "          />\n" +
                    "          <path\n" +
                    "            fill-rule=\"evenodd\"\n" +
                    "            clip-rule=\"evenodd\"\n" +
                    "            d=\"M93.8333 60C93.8333 78.41 78.91 93.3333 60.5 93.3333C55.1667 93.3333 50.1267 92.08 45.66 89.8533C44.4801 89.2435 43.118 89.0858 41.83 89.41L34.41 91.3966C33.6756 91.5926 32.9025 91.5919 32.1684 91.3947C31.4343 91.1975 30.765 90.8106 30.2277 90.2729C29.6904 89.7353 29.304 89.0658 29.1072 88.3315C28.9104 87.5973 28.9102 86.8243 29.1067 86.09L31.09 78.67C31.4126 77.3826 31.2537 76.0217 30.6433 74.8433C28.3488 70.2322 27.1585 65.1504 27.1667 60C27.1667 41.59 42.09 26.6666 60.5 26.6666C78.91 26.6666 93.8333 41.59 93.8333 60ZM60.5 44.1666C61.163 44.1666 61.7989 44.43 62.2678 44.8989C62.7366 45.3677 63 46.0036 63 46.6666V46.7C66.63 47.6166 69.6667 50.4766 69.6667 54.4433C69.6667 55.1063 69.4033 55.7422 68.9344 56.2111C68.4656 56.6799 67.8297 56.9433 67.1667 56.9433C66.5036 56.9433 65.8677 56.6799 65.3989 56.2111C64.9301 55.7422 64.6667 55.1063 64.6667 54.4433C64.6667 53.1633 63.2467 51.39 60.5 51.39C57.7533 51.39 56.3333 53.1633 56.3333 54.4433C56.3333 55.7233 57.7533 57.5 60.5 57.5C65.1167 57.5 69.6667 60.7 69.6667 65.5566C69.6667 69.5233 66.63 72.3866 63 73.3V73.3333C63 73.9963 62.7366 74.6322 62.2678 75.1011C61.7989 75.5699 61.163 75.8333 60.5 75.8333C59.837 75.8333 59.2011 75.5699 58.7322 75.1011C58.2634 74.6322 58 73.9963 58 73.3333V73.3C54.37 72.3866 51.3333 69.5233 51.3333 65.5566C51.3333 64.8936 51.5967 64.2577 52.0656 63.7889C52.5344 63.32 53.1703 63.0566 53.8333 63.0566C54.4964 63.0566 55.1323 63.32 55.6011 63.7889C56.0699 64.2577 56.3333 64.8936 56.3333 65.5566C56.3333 66.8366 57.7533 68.61 60.5 68.61C63.2467 68.61 64.6667 66.8366 64.6667 65.5566C64.6667 64.2766 63.2467 62.5 60.5 62.5C55.8833 62.5 51.3333 59.3 51.3333 54.4433C51.3333 50.4766 54.37 47.6166 58 46.7V46.6666C58 46.0036 58.2634 45.3677 58.7322 44.8989C59.2011 44.43 59.837 44.1666 60.5 44.1666Z\"\n" +
                    "            fill=\"white\"\n" +
                    "          />\n" +
                    "        </svg>\n" +
                    "        <span class=\"templateContainer__logoContainer__text\">\n" +
                    "          Sistema de prestamos\n" +
                    "        </span>\n" +
                    "      </figure>\n" +
                    "\n" +
                    "      <h1 class=\"templateContainer__title\">Hola ," + name + "</h1>\n" +
                    "      <p class=\"templateContainer__paragraph\">\n" +
                    "        Has solicitado la recuperación de contraseña para acceder al sistema de\n" +
                    "        prestamos puedes hacer el cambio dando clic en el botón de abajo.\n" +
                    "      </p>\n" +
                    "      <a class=\"templateContainer__button\" href=\"http://localhost:5731/changePassword/" + token + ">\n" +
                    "        <svg\n" +
                    "          width=\"24\"\n" +
                    "          height=\"24\"\n" +
                    "          viewBox=\"0 0 24 24\"\n" +
                    "          fill=\"none\"\n" +
                    "          xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "          class=\"templateContainer__button__icon\"\n" +
                    "        >\n" +
                    "          <path\n" +
                    "            d=\"M21.168 8C20.3892 6.21646 19.107 4.69884 17.4786 3.63311C15.8501 2.56738 13.9462 1.99983 12 2C6.815 2 2.55 5.947 2.05 11\"\n" +
                    "            stroke=\"white\"\n" +
                    "            stroke-width=\"1.5\"\n" +
                    "            stroke-linecap=\"round\"\n" +
                    "            stroke-linejoin=\"round\"\n" +
                    "          />\n" +
                    "          <path\n" +
                    "            d=\"M17 8H21.4C21.4788 8 21.5568 7.98448 21.6296 7.95433C21.7024 7.92417 21.7685 7.87998 21.8243 7.82426C21.88 7.76855 21.9242 7.70241 21.9543 7.62961C21.9845 7.55681 22 7.47879 22 7.4V3M2.881 16C4.425 19.532 7.949 22 12.049 22C17.235 22 21.499 18.053 22 13\"\n" +
                    "            stroke=\"white\"\n" +
                    "            stroke-width=\"1.5\"\n" +
                    "            stroke-linecap=\"round\"\n" +
                    "            stroke-linejoin=\"round\"\n" +
                    "          />\n" +
                    "          <path\n" +
                    "            d=\"M7.05 16H2.65C2.49087 16 2.33826 16.0632 2.22574 16.1757C2.11322 16.2883 2.05 16.4409 2.05 16.6V21\"\n" +
                    "            stroke=\"white\"\n" +
                    "            stroke-width=\"1.5\"\n" +
                    "            stroke-linecap=\"round\"\n" +
                    "            stroke-linejoin=\"round\"\n" +
                    "          />\n" +
                    "        </svg>\n" +
                    "\n" +
                    "        <span class=\"templateContainer__button__span\"\n" +
                    "          >Restablecer Contraseña</span\n" +
                    "        >\n" +
                    "      </a>\n" +
                    "      <svg\n" +
                    "        class=\"templateContainer__svg2\"\n" +
                    "        viewBox=\"0 0 100 109\"\n" +
                    "        fill=\"none\"\n" +
                    "        xmlns=\"http://www.w3.org/2000/svg\"\n" +
                    "      >\n" +
                    "        <path d=\"M0 69.8718H38.4615V108.333H0V69.8718Z\" fill=\"white\" />\n" +
                    "        <path d=\"M1.28205 25H33.3333V57.0513H1.28205V25Z\" fill=\"white\" />\n" +
                    "        <path\n" +
                    "          d=\"M46.1538 76.2821H78.2051V108.333H46.1538V76.2821Z\"\n" +
                    "          fill=\"white\"\n" +
                    "        />\n" +
                    "        <path\n" +
                    "          d=\"M46.1538 31.4103H71.7949V57.0513H46.1538V31.4103Z\"\n" +
                    "          fill=\"white\"\n" +
                    "        />\n" +
                    "        <path d=\"M3.20513 0H16.0256V12.8205H3.20513V0Z\" fill=\"white\" />\n" +
                    "        <path d=\"M87.1795 95.5128H100V108.333H87.1795V95.5128Z\" fill=\"white\" />\n" +
                    "      </svg>\n" +
                    "    </div>\n" +
                    "  </body>\n" +
                    "</html>\n";
            default -> "No data";
        };


    }

}

