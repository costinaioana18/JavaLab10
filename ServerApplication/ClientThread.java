package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * An instance of this class communicates with a client
 */
class ClientThread extends Thread {
    private Socket socket = null;
    private GameServer game;
    private boolean run = true;
    private int id;

    public ClientThread(Socket socket, GameServer game) {
        this.socket = socket;
        this.game = game;
    }

    /**
     * the method receives commands from the players and executes them
     */
    public void run() {
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            while (run) {
                String request = in.readLine();
                PrintWriter out = new PrintWriter(socket.getOutputStream());

                if (request.equals("Join")) {
                    joinGame(out, request);
                } else if (request.equals("Exit")) {
                    System.out.println("Player " + id + " left the game");
                    String raspuns = "Exit";
                    out.println(raspuns);
                    out.flush();
                    run = false;
                    game.decreasePlayersNo();
                } else {
                    insertPiece(out, request);
                }

            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close(); // or use try-with-resources
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    /**
     * The method allows a player to join the game
     */
    private void joinGame(PrintWriter out, String request) {

        if (game.getPlayersNo() == 1) {
            id = 1;
            String raspuns = "You are player 1. Your colour is black. Wait for your opponent!";
            out.println(raspuns);
            out.flush();
        }
        if (game.getPlayersNo() == 2) {
            id = 2;
            String raspuns = "Your are player 2. Your colour is white.";
            out.println(raspuns);
            out.flush();
        }
    }

    /**
     * The method "inserts" a piece on the table, sends a confirmation to the client
     */
    private void insertPiece(PrintWriter out, String request) {
        System.out.println("Player " + id + " inserted a " + this.getColor(id) + " piece on the table at: " + request + "(row and column no)!");
        String raspuns = "Your " + this.getColor(id) + " piece was inserted on the table at: " + request + "(row and column no)!";
        out.println(raspuns);
        out.flush();
    }

    /**
     * The method returns the color of the player
     */
    private String getColor(int id) {
        if (id == 1) return "black";
        else return "white";
    }
}