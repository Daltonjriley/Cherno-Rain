
import javax.swing.JFrame;


public class App {
    public static void main(String[] args) {
        
        Game game = new Game();
        game.getFrame().setResizable(false);
        game.getFrame().setTitle("Rain");
        game.getFrame().add(game);
        game.getFrame().pack();
        game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getFrame().setLocationRelativeTo(null);
        game.getFrame().setVisible(true);

        game.start();

    }
}
