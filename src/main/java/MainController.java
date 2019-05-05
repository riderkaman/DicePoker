import java.util.ArrayList;
import java.util.List;

public class MainController {

    public static void main(String[] args) {

//        System.out.println("헬로");

        int[] myDiceArr = new int[7];
        int[] oppDiceArr = new int[7];

        // 다이스 포커 첫번째
        // 5개의 1d6주사위를 굴려서 나온 숫자를 배열에 넣는다.
        // 이 때, 각 숫자는 배열의 하나의 인덱스에 해당한다.
        // 예를 들어 1 3 2 5 3 이 나왔다면
        // myDiceArr[1]++, myDiceArr[3]++, myDiceArr[2]++, myDiceArr[5]++, myDiceArr[3]++
        // 이런 식이다.
        // 배열은 [0] 부터 시작이지만 계산 편의성을 위해 맨 앞은 비워둠.
        // 나중에 계산 편의성보다 로직상 문제가 생기는 경우 다시 [0] 부터 사용

        for (int i = 0; i < 5; i++) {
            myDiceArr[(int) (Math.random() * 6) + 1]++;
        }
        for (int i = 0; i < 5; i++) {
            oppDiceArr[(int) (Math.random() * 6) + 1]++;
        }


        for (int i = 1; i < myDiceArr.length; i++) {
            System.out.println(myDiceArr[i]);
        }
        System.out.println("---------------");
        for (int i = 1; i < myDiceArr.length; i++) {
            System.out.println(oppDiceArr[i]);
        }

        int myResult = checkRank(myDiceArr);
        int oppResult = checkRank(oppDiceArr);

        if (myResult > oppResult) {
            System.out.println("You Win.");
            System.out.println("My Rank:" + getRankName(myResult));
            System.out.println("Opposing Rank:" + getRankName(oppResult));
        }
        else if (myResult < oppResult) {
            System.out.println("Opposing Win");
            System.out.println("My Rank:" + getRankName(myResult));
            System.out.println("Opposing Rank:" + getRankName(oppResult));
        }
        else {
            System.out.println("draw");
            System.out.println("My Rank:" + getRankName(myResult));
            System.out.println("Opposing Rank:" + getRankName(oppResult));
        }



    }

    public static int checkRank(int[] myDiceArr) {
        boolean fiveKind = false;
        boolean fourKind = false;
        boolean fullHouseThree = false;
        boolean fullHousePair = false;
        boolean fullHouse = false;
        boolean sixHighStraight = false;
        boolean fiveHighStraight = false;
        boolean threeKind = false;
        boolean twoPairs = false;
        boolean pair = false;

        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 5) {
                fiveKind = true;
//                System.out.println("Five-of-a-Kind");
                break;
            }
        }

        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 4) {
                fourKind = true;
//                System.out.println("Four-of-a-Kind");
                break;
            }
        }

        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 3) {
                fullHouseThree = true;
//                System.out.println("fullHouseThree");
            } else if (myDiceArr[i] == 2) {
                fullHousePair = true;
//                System.out.println("fullHousePair");
            }

            if (fullHouseThree && fullHousePair) {
//                System.out.println("Full House");
                fullHouse = true;
                break;
            }
        }

        int j = 0;
        for (int i = 2; i < myDiceArr.length; i++) {
            if (myDiceArr[i] > 1 || myDiceArr[i] < 1) {
                break;
            } else {
                j++;
            }

            if (j == 5) {
//                System.out.println("Six High Straight");
                sixHighStraight = true;
            }
        }

        j = 0;
        for (int i = 1; i < myDiceArr.length - 1; i++) {
            if (myDiceArr[i] > 1 || myDiceArr[i] < 1) {
                break;
            } else {
                j++;
            }

            if (j == 5) {
//                System.out.println("Five High Straight");
                fiveHighStraight = true;
            }
        }

        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 3) {
                threeKind = true;
//                System.out.println("Three-of-a-Kind");
                break;
            }
        }

        j = 0;
        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 2) {
                j++;
            }

            if (j == 2) {
                twoPairs = true;
//                System.out.println("Two Pairs");
                break;
            }
        }

        for (int i = 1; i < myDiceArr.length; i++) {
            if (myDiceArr[i] == 2) {
                pair = true;
//                System.out.println("Pair");
                break;
            }
        }

        int result;
        if (fiveKind) result = 8;
        else if (fourKind) result = 7;
        else if (fullHouse) result = 6;
        else if (sixHighStraight) result = 5;
        else if (fiveHighStraight) result = 4;
        else if (threeKind) result = 3;
        else if (twoPairs) result = 2;
        else if (pair) result = 1;
        else result = 0;
        return result;
    }

    public static String getRankName(int rank) {
        String name = "";

        switch (rank) {
            case 8:
                name = "Five-of-a-Kind";
                break;
            case 7:
                name = "Four-of-a-Kind";
                break;
            case 6:
                name = "Full House";
                break;
            case 5:
                name = "Six High Straight";
                break;
            case 4:
                name = "Five High Straight";
                break;
            case 3:
                name = "Three-of-a-Kind";
                break;
            case 2:
                name = "Two Pairs";
                break;
            case 1:
                name = "Pair";
                break;
            default:
                name = "Nothing";
        }

        return name;
    }


}
