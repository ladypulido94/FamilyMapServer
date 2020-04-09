package Result;

public class MainResult {

    String message;
    boolean success;

    /**
     * This will store the message from the clear services
     * @param message
     */
    public MainResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    /**
     * This will return the message to the client
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     * This will set the message value
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess(){
        return success;
    }

    public void setSuccess(boolean success){this.success = success;}
}
