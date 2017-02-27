package in.co.ismdhanbad.myapplication;

/**
 * Created by khandelwal on 27/02/17.
 */

public class Message {

    private int room;
    private int color;

    public Message() {
    }

    public Message(int room, int color) {
        this.room = room;
        this.color = color;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }


}
