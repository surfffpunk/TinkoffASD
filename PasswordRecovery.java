import java.math.BigInteger;
import java.util.Scanner;

public class PasswordRecovery {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        BigInteger passwordLength = inputScanner.nextBigInteger();
        BigInteger characterSetSize = inputScanner.nextBigInteger();
        BigInteger operationsPerSecond = inputScanner.nextBigInteger();
        BigInteger modValue = inputScanner.nextBigInteger();

        BigInteger totalCombinations = characterSetSize.modPow(passwordLength, modValue);
        BigInteger operationsPerSecondInverse = operationsPerSecond.modInverse(modValue);
        BigInteger timeRequiredInSeconds = totalCombinations.multiply(operationsPerSecondInverse).mod(modValue);

        System.out.println(timeRequiredInSeconds);
    }
}
