package utils;

public class Message {
    public final static int VALID = 0;
    public final static int GAME_OVER = 1;
    public final static int GAME_WON = 2;
    public final static int GAME_UP = 3;
    public final static int messageLength = 4;
    private boolean[] signals;

    public Message(String msg) {
        signals = new boolean[messageLength];
        for(int i = 0; i < msg.length(); i++) {
            char bit = msg.charAt(i);
            if(bit == '1') {
                signals[i] = true;
            } else if (bit == '0'){
                signals[i] = false;
            }
        }
    }

    public boolean[] getContents() {
        return signals;
    }

    public boolean isValid() {
        return signals[VALID];
    }
}
