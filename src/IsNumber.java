public class IsNumber {
    public static boolean isNumber(String number) {
        return number != null && number.matches("[+-]?\\d+[.]?\\d+");
    }
    public static String formatNumber(String number) {
        return number != null ? number.trim().replaceAll(",", ".") : null;
    }

}
