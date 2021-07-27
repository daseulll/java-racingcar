package racingCar.domain;

import racingCar.domain.strategy.MoveStrategy;
import racingCar.domain.strategy.RacingMoveStrategy;

import java.util.ArrayList;
import java.util.List;

public class Race {

    private static final List<Round> rounds = new ArrayList<>();
    private MoveStrategy moveStrategy = new RacingMoveStrategy();
    private final int MIN_VALUE_BY_CAR = 1;
    private final static String IS_NOT_DUAL_CAR_COUNT_ERROR_MESSAGE = "자동차 대수는 1대 이상이어야 합니다.";
    private final static String IS_NOT_DUAL_ROUND_COUNT_ERROR_MESSAGE = "라운드는 1라운드 이상이어야 합니다.";
    private final String COMMA = ",";
    private final int ZERO = 0;

    public Race(String nameList, int roundCount) {
        validateInput(nameList, roundCount);
        lineUp(nameList);
        startRace(roundCount);
    }

    private void validateInput(String nameList, int roundCount) {
        if(isNotCarCountOverZero(nameList)) {
            throw new IllegalArgumentException(IS_NOT_DUAL_CAR_COUNT_ERROR_MESSAGE);
        }
        if(isNotRoundCountOverZero(roundCount)) {
            throw new IllegalArgumentException(IS_NOT_DUAL_ROUND_COUNT_ERROR_MESSAGE);
        }
    }

    private boolean isNotCarCountOverZero(String nameList) {
        return nameList.split(COMMA).length < MIN_VALUE_BY_CAR;
    }

    private boolean isNotRoundCountOverZero(int roundCount) {
        return roundCount < MIN_VALUE_BY_CAR;
    }

    private void lineUp(String nameList) {
        List<Car> cars = new ArrayList();
        String[] names = nameList.split(COMMA);

        for(String name : names) {
            cars.add(new Car(new Name(name)));
        }

        this.rounds.add(new Round(cars));
    }

    private void startRace(int roundCount) {
        int roundNumber = ZERO;
        while (roundNumber < roundCount){
            Round round = rounds.get(roundNumber).startRound(moveStrategy);
            this.rounds.add(round);
            roundNumber++;
        }
    }

    public Round getRoundResult(int roundNumber) {
        return rounds.get(roundNumber);
    }

    public boolean isNotEnd(int roundNumber) {
        return roundNumber < rounds.size();
    }

    public List<String> announceWinners() {
        Round finalRound = rounds.get(rounds.size()-1);
        return finalRound.getFirstCar();
    }

}