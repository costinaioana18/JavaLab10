# JavaLab10

I have completed the following tasks:

- Creating the the server project, which responsible with the game management and mediating the players,recieving and executing their commands.

- Creating the GameServer class. An instance of this class will create a ServerSocket running at a specified port.

- Creating the ClientThread class which communicates with a client.

- Creating the client project which will communicate with the server, sending it commands such as: joining a game, exiting and submitting a move.

- Creating the GameClient class. An instance of this class will read commands from the keyboard and it will send them to the server. The client stops when it reads from the keyboard the string "Exit". For joining a game, the client should write "Join", and for submitting a move, the client should write two integers separated by a black space, like "8 9"

*Check the Functionality.jpg to see how 2 playes join, play and exit a game*
