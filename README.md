2048 Game

<img width="1004" alt="Screenshot 2024-12-05 at 9 15 39 AM" src="https://github.com/user-attachments/assets/62993e14-43c4-47a3-9a3f-80846041b63b">
<img width="1006" alt="Screenshot 2024-12-05 at 9 32 10 AM" src="https://github.com/user-attachments/assets/5bb90afe-3794-46a6-99be-0d7b4c636980">

Overview:
This project is a Java implementation of the classic game 2048. This project uses the MVC design pattern.

- Model: Board.java
  * Handles the game logic, tile movement, scoring, and game state
- View: BoardGUI.java (Graphic User Interface), View.java (Text-Based Interface)
- Controller: GameManager.java
  * Manages game state, helps coordination between the Model (Board.java) and View
    (BoardGUI.java)
- Other Supporting Classes: Direction.java, Tile.java, Composite2048Observer.java, Leaderboard.java, ScoreEntry.java
  * Direction.java - Enum for shift directions
  * Tile.java - Creates a tile for the board
  * Composite2038Observer.java - Observer interface
  * Leaderboard.java - Stores the names, scores, and board size of players and ranks them.
  * ScoreEntry.java - manages individual player scores.
- Tests: BoardTest.java
- Text files: Leaderboard.txt

Set Up Instructions:
