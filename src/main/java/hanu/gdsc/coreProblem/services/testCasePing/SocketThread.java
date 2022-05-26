package hanu.gdsc.coreProblem.services.testCasePing;

import com.fasterxml.jackson.databind.ObjectMapper;
import hanu.gdsc.coreProblem.config.TestCasePingConfig;
import hanu.gdsc.share.domains.Id;
import hanu.gdsc.share.scheduling.Scheduler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketThread {
    private Id coderId;
    private final Socket socket;
    private final DataInputStream socketIn;
    private final DataOutputStream socketOut;
    private final boolean closed[];

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Scheduler getCoderIdThread;

    public SocketThread(ServerSocket serverSocket) throws Exception {
        socket = serverSocket.accept();
        socketIn = new DataInputStream(socket.getInputStream());
        socketOut = new DataOutputStream(socket.getOutputStream());
        closed = new boolean[]{false};

        getCoderIdThread = new Scheduler(TestCasePingConfig.GET_CODER_ID_RATE, new Scheduler.Runner() {
            @Override
            public void run() throws Exception {
                try {
                    String coderIdFromSocket = socketIn.readUTF();
                    coderId = new Id(coderIdFromSocket);
                    stop();
                } catch (Exception ignored) {
                }
            }
        });
        getCoderIdThread.start();
    }

    private void close() throws IOException {
        socket.close();
        socketIn.close();
        socketOut.close();
        closed[0] = true;
        getCoderIdThread.stop();
    }

    public Id getCoderId() {
        return coderId;
    }

    public void send(Object payload) throws IOException {
        if (closed()) {
            return;
        }
        try {
            socketOut.writeUTF(objectMapper.writeValueAsString(payload));
            socketOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
    }

    public boolean closed() {
        return closed[0];
    }
}
