/*
 * 자동차 경주의 입력을 담당하는 클래스
 *
 * @author hj-woo
 * @version 2.0
 * */
package step3.racingcar.ui;

import step3.racingcar.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
    private static Scanner sc;
    private static List<Object> input;

    public static List<Object> input(String[] strArr) {
        input = new ArrayList<>();
        sc = new Scanner(System.in);
        for (String str : strArr) {
            System.out.println(str);
            checkInput(str);
        }
        return input;
    }

    public static void checkInput(String str) {
        if (str.equals(Game.HOW_MANY_CARS) || str.equals(Game.HOW_MANY_TRYS)) {
            input.add(sc.nextInt());
        }
        if (str.equals(Game.INPUT_CAR_NAMES)) {
            input.add(sc.nextLine());
        }
    }

    public static void closeScanner(){
        sc.close();
    }
}
