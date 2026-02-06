package common;

import java.io.Serializable;

public class Message implements Serializable {
    private String sender;
    private String content;

    public static final long serialVersionUID = 1L;

    public Message(String Sender, String Content) {
        sender = Sender;
        content = Content;
    }

    public String toString() {
        return sender + ";" + content;
    }

    public void setSender(String Sender) {
        sender = Sender;
    }
}
