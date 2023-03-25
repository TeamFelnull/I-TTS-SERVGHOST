package dev.felnull.itts.servghost.control;

public class ControlServer {
    private final Thread waitThread = new ConnectionWaitThread();
    private boolean accepting;

    public void acceptingStart() {
        if (accepting)
            throw new RuntimeException("Already accepted");
        accepting = true;

        waitThread.start();
    }
}
