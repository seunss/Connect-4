
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Seun Lawal 101039762
 */
public class ConnectFourTestClient {

    public static void main(String args[]) {
        ConnectFourGame game = new ConnectFourGame(ConnectFourEnum.BLACK);//Starts Game
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(game.toString());
            System.out.println(game.getTurn()
                    + ": Where do you want to Go? Enter row column");
            int row = scanner.nextInt();
            int column = scanner.nextInt();
            scanner.nextLine();
            game.takeTurn(row, column);

        } while (game.getGameState() == ConnectFourEnum.IN_PROGRESS);
        System.out.println(game.getGameState());

    }

}
