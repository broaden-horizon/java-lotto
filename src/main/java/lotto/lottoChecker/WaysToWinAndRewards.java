package lotto.lottoChecker;

import lotto.constants.LottoConstants;

import java.util.Arrays;

import static lotto.constants.ExceptionMessage.NOT_VALID_MATCH;
import static lotto.constants.ExceptionMessage.NULL_POINTER;

public enum WaysToWinAndRewards {
    FIRST(1, 6, 0, 2_000_000_000),
    SECOND(2, 5, 1, 30_000_000),
    THIRD(3, 5, 0, 1_500_000),
    FOURTH(4, 4, 0, 50_000),
    FIFTH(5, 3, 0, 5_000),
    NONE(-1, -1, 0, 0);
    private int rank;
    private int mainNumberMatches;
    private int bonusNumberShouldMatch; //0이면 상관없음
    private int reward;
    WaysToWinAndRewards(int rank, int mainNumberMatches, int bonusNumberMatch, int reward) {
        this.rank = rank;
        this.mainNumberMatches = mainNumberMatches;
        this.bonusNumberShouldMatch = bonusNumberMatch;
        this.reward = reward;
    }

    public int getRank() {
        return rank;
    }

    public int getReward() {
        return reward;
    }

    public static WaysToWinAndRewards findByMatches(int[] matchResult) {
        if(matchResult == null || matchResult.length != 2) throw new IllegalArgumentException(NOT_VALID_MATCH);

        int numberOfMainNumbersMatches = matchResult[0];
        int isBonusNumberMatch = matchResult[1];

        if(numberOfMainNumbersMatches < 0 || numberOfMainNumbersMatches > LottoConstants.NUMBER_OF_PICKED
                || isBonusNumberMatch < 0 || isBonusNumberMatch > 1 || numberOfMainNumbersMatches+isBonusNumberMatch > 6) {
            throw new IllegalArgumentException(NOT_VALID_MATCH);
        }
        return Arrays.stream(WaysToWinAndRewards.values())
                .filter(way -> {
                    return (way.mainNumberMatches == numberOfMainNumbersMatches &&
                            way.bonusNumberShouldMatch == isBonusNumberMatch);
                })
                .findAny()
                .orElse(NONE);
    }
}
