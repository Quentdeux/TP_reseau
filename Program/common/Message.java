package common;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String content;

    public static final long serialVersionUID = 1L;

    public void Message (String Sender, String Content) {
        sender = Sender;
        content = Content;
    }

    public String toString() {
        return sender + ";" + content;
    }
}
