package dev.felnull.itts.servghost.control;

public class Main {
    public static final ControlServer CONTROL_SERVER = new ControlServer();

    public static void main(String[] args) {
        CONTROL_SERVER.acceptingStart();
        System.out.println("Hello world!");
    }
}