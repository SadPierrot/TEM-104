package com.company;

import com.company.util.NetworkParameterValidator;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class DeviceConverter {
    public String ip;
    public int port;

    Socket socket = new Socket();

    public DeviceConverter(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public ConnectionChannel getChannel() throws Exception {
        try {
            if (NetworkParameterValidator.validationValidIP(ip) && NetworkParameterValidator.validationValidPort(port)) {
                socket.connect(new InetSocketAddress(ip, port), 5000);
                socket.setSoTimeout(3000);
                return new ConnectionChannel(socket);
            }
        } catch (SocketTimeoutException | NullPointerException e1) {
            if (!socket.isConnected()) {
                socket.close();
            }
        }
        throw new Exception("Канал связи не открыт");
    }
}
