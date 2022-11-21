public class IsString {
    public boolean isString(String dataString) {
        if (dataString == null || dataString.isEmpty()) return false;
        for (int i = 0; i < dataString.length(); i++) {
            if (Character.isDigit(dataString.charAt(i))) return false;
        }
        return true;
    }
}
