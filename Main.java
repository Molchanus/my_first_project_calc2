import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static int arab2, arab1;
    static int romanValue1, romanValue2;
    static char operator;
    static int arabResult;

    public static void main(String[] args) {
        System.out.println("Введите выражение из двух арабских или двух римских чисел");
        String userInput = scanner.nextLine();
        String result = calc(userInput);
        System.out.println(result);
    }

    private static String calc(String userInput) {
        char[] charArray = new char[10];
        int k = 0;
        for (int i = 0; i < userInput.length(); i++) {
            charArray[i] = userInput.charAt(i);

            if (charArray[i] == '+') {
                operator = '+';
                k++;
            }
            if (charArray[i] == '-') {
                operator = '-';
                k++;
            }
            if (charArray[i] == '*') {
                operator = '*';
                k++;
            }
            if (charArray[i] == '/') {
                operator = '/';
                k++;
            }
        }
        if (k == 0) {
            throw new IllegalArgumentException("Должен быть хотя бы 1 оператор. Строка не является математической операцией");
        }

        String[] part = userInput.split("[+-/*]");

        String firstParam = part[0].trim();
        String secondParam = part[1].trim();
        if (part.length > 2) {
            throw new IllegalArgumentException("слагаемых больше двух");

        }
        try {
            arab1 = Integer.parseInt(firstParam);
            arab2 = Integer.parseInt(secondParam);
        } catch (NumberFormatException e) {
            romanValue1 = romToArab(firstParam);
            romanValue2 = romToArab(secondParam);
        }
        if (isArabian(firstParam) && isArabian(secondParam)) {
            arabResult = calculated(arab1, arab2, operator);
            return "" + arabResult;
        } else if (isRoman(firstParam) && isRoman(secondParam)) {
            arabResult = calculated(romanValue1, romanValue2, operator);
            if (arabResult < 0) {
                throw new IllegalArgumentException("резултат вычитания римских чисел не может быть меньше 0 по условию");
            }
            return convertNumToRom(arabResult);
        } else {
            throw new IllegalArgumentException("Входные данные:" + " " + "(" + userInput + ")" + " " + "не соответствуют условию <Введите выражение из двух арабских или двух римских чисел>");
        }
    }

    private static int romToArab(String roman) {
        if (roman.equals("I")) {
            return 1;
        } else if (roman.equals("II")) {
            return 2;
        } else if (roman.equals("III")) {
            return 3;
        } else if (roman.equals("IV")) {
            return 4;
        } else if (roman.equals("V")) {
            return 5;
        } else if (roman.equals("VI")) {
            return 6;
        } else if (roman.equals("VII")) {
            return 7;
        } else if (roman.equals("VIII")) {
            return 8;
        } else if (roman.equals("IX")) {
            return 9;
        } else if (roman.equals("X")) {
            return 10;
        } else if (roman.equals("Х")) {
            return 10;//русская раскладка
        }

        return -1;
    }

    private static boolean isArabian(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isRoman(String value) {
        return romToArab(value) != -1;

    }

    public static int calculated(int num1, int num2, char op) {
        if (num1 > 10 || num2 > 10) {
            throw new IllegalArgumentException("оба числа на входе должны быть меньше 10");
        }
        int result = 0;
        switch (op) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    throw new IllegalArgumentException("на ноль делить нельзя");
                }
                result = num1 / num2;
                break;
        }
        return result;
    }

    private static String convertNumToRom(int numArabian) {
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        String s = roman[numArabian];
        return s;
    }
}

