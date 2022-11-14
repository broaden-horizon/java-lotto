package lotto.lottoCashier;

import java.util.List;

public interface LottoCashier {
    public int receiveMoney();
    public int calculateNumberOfLottos(int money);
    public void printNumberPurchased();
}