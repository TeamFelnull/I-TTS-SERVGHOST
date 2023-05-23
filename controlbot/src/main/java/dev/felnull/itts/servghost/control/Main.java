package dev.felnull.itts.servghost.control;

import dev.felnull.itts.servghost.control.connect.ControlServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * SERVGHOST版I-TTSを制御するためのプログラム
 */
public class Main {

    /**
     * ロガー
     */
    public static final Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * 制御サーバー
     */
    public static final ControlServer CONTROL_SERVER = new ControlServer();

    public static void main(String[] args) throws Exception {
        CONTROL_SERVER.start();
    }
}