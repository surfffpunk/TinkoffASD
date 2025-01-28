import java.util.Scanner;

public class GearRotation {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long gearOneTeeth = input.nextLong();
        long gearTwoTeeth = input.nextLong();
        System.out.println(calculateMinimumRotations(gearOneTeeth, gearTwoTeeth));
    }

    public static long calculateGreatestCommonDivisor(long numberOne, long numberTwo) {
        if (numberTwo == 0) {
            return numberOne;
        } else {
            return calculateGreatestCommonDivisor(numberTwo, numberOne % numberTwo);
        }
    }

    public static long calculateMinimumRotations(long gearOneTeeth, long gearTwoTeeth) {
        return gearOneTeeth / calculateGreatestCommonDivisor(gearOneTeeth, gearTwoTeeth) * gearTwoTeeth;
    }
}
