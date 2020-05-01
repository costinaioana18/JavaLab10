package com.company;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * An instance of this class will create a ServerSocket running at a specified port
 */
public class GameServer {
    public static final int PORT = 8100;
    private int playersNo = 0;
    private int gameStarted = 0;

    /**
     * we create a clientThread for each player
     */
    public GameServer() throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);

            while (true) {

                if (playersNo < 2) {
                    if (playersNo == 0) System.out.println("Waiting for two players ...");
                    else System.out.println("Player 1 joined the game. Waiting for another player ...");
                    Socket socket = serverSocket.accept();
                    playersNo++;
                    new ClientThread(socket, this).start();
                    if (playersNo == 2) {
                        System.out.println("Player 2 joined the game. Game started");
                        gameStarted = 1;
                    }
                }


            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();
        }
    }

    public int getPlayersNo() {
        return playersNo;
    }

    public int getGameStarted() {
        return gameStarted;
    }

    public void decreasePlayersNo() {
        playersNo--;
    }
}