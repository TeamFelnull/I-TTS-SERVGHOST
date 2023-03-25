package dev.felnull.itts.servghost.control;

public class ConnectionWaitThread extends Thread {
    @Override
    public void run() {
        while (true) {
            try {
                connectWait();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void connectWait() {

    }
}
