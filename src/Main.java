//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Map;
import java.util.HashMap;

public class Main {
    private static final Map<Character, Integer> RomanToArabic = new HashMap<>();
    private static final Map<Integer, String> ArabicToRoman = new HashMap<>();
    static {
        RomanToArabic.put('I', 1);
        RomanToArabic.put('V', 5);
        RomanToArabic.put('X', 10);
        ArabicToRoman.put(1, "I");
        ArabicToRoman.put(4, "IV");
        ArabicToRoman.put(5, "V");
        ArabicToRoman.put(9, "IX");
        ArabicToRoman.put(10, "X");
    }

    public static void main(String[] args) {
        try {
            String input = "1 + 2";
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        input = input.trim();
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new Exception("throws Exception //Неверный формат ввода.");
        }
        String aStr = parts[0];
        String _op = parts[1];
        String bStr = parts[2];
        boolean isRoman = isRoman(aStr) && isRoman(bStr);
        boolean isArabic = isArabic(aStr) && isArabic(bStr);
        if (!(isRoman || isArabic)) {
            throw new Exception("throws Exception Разные системы счисления.");
        }
        int a=isRoman ? RomanToArabic(aStr) : Integer.parseInt(aStr);
        int b=isRoman ? RomanToArabic(bStr) : Integer.parseInt(bStr);
        if (a<1 || a>10 || b<1 || b>10) {
            throw new Exception("throws Exception Числа должны быть от 1 до 10 включительно.");
        }
        int result;
        switch (_op) {
            case "+":
                result = a+b;
                break;
            case "*":
                result = a*b;
                break;
            case "-":
                result = a-b;
                break;
            case "/":
                result = a/b;
                break;
            default:
                throw new Exception("throws Exception Неверный оператор.");
        }
        if (isRoman) {
            if (result<1) {
                throw new Exception("throws Exception Результат работы с римскими числами должен быть больше нуля.");
            }
            else {
                return ArabicToRoman(result);
            }
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRoman(String str) {
        return str.matches("[IVX]+");
    }

    private static boolean isArabic(String str) {
        return str.matches("\\d+");
    }

    private static int RomanToArabic(String roman) throws Exception {
        int result = 0;
        int i = 0;
        while (i < roman.length()) {
            char currentChar = roman.charAt(i);
            int currentValue = RomanToArabic.get(currentChar);

            if (i + 1 < roman.length()) {
                char nextChar = roman.charAt(i + 1);
                int nextValue = RomanToArabic.get(nextChar);

                if (currentValue < nextValue) {
                    result += nextValue - currentValue;
                    i += 2;
                    continue;
                }
            }
            result += currentValue;
            i++;
        }
        return result;
    }

    private static String ArabicToRoman(int number) {
        StringBuilder result = new StringBuilder();
        for (Integer key : ArabicToRoman.keySet()) {
            while (number >= key) {
                result.append(ArabicToRoman.get(key));
                number -= key;
            }
        }
        return result.toString();
    }
}