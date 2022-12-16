import java.util.Locale;

public class AlbertiCipher {
    private char key[];
    private final char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'L','M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'Z', '1', '2', '3', '4', '5'};

    public AlbertiCipher(String key) {
        this.key = key.toCharArray();
    }

    public String encrypt(String message) {
        String encryptedMessage = "";
        String upperCaseMessage = message.toUpperCase(Locale.ROOT);
        boolean toShift = false;
        int indexOfShift = -1;
        int offset = 0;
        for(int i = 0; i < upperCaseMessage.length(); i++) {
            char character = upperCaseMessage.charAt(i);
            if(getSpecialCharValue(character) != -1) {
                System.out.println("special char trovato: " + getSpecialCharValue(character));
                indexOfShift = getSpecialCharValue(character) + i;
                toShift = true;
            }
            int index = Math.abs(getIndex(character) + offset) % this.alphabet.length;
            encryptedMessage += key[index];
            if(toShift && i == indexOfShift + 1) {
                System.out.println("shift eseguito a: " + character);
                offset += getOffset(this.key[i])+2;
                toShift = false;
            }
        }
        System.out.println(this.alphabet);
        System.out.println(this.key);
        System.out.println(message);
        return encryptedMessage;
    }
    private int getSpecialCharValue(char character) {
        char [] specialChars = {'1', '2', '3', '4', '5'};
        for(int i = 0; i < specialChars.length; i++) {
            if(specialChars[i] == character) {
                return i + 1;
            }
        }
        return -1;
    }
    private int getIndex(char character) {
        for(int i = 0; i < this.alphabet.length; i++) {
            if(character == this.alphabet[i]) {
                return i;
            }
        }
        return -1;
    }
    private int getOffset(char keyChar) {
        for(int i = 0; i < this.key.length; i++) {
            if(keyChar == this.key[i]) {
                return i;
            }
        }
        return -1;
    }
}
