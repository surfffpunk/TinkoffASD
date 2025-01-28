import java.util.*;

public class Alchemy {
    static Map<String, ArrayList<String>> reactions = new HashMap<>();
    static Map<String, Integer> transformationSteps = new HashMap<>();

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfReactions = input.nextInt();
        input.nextLine();

        for (int i = 0; i < numberOfReactions; i++) {
            String[] substances = input.nextLine().split(" -> ");
            String initialSubstance = substances[0];
            String resultSubstance = substances[1];

            if (!reactions.containsKey(initialSubstance)) {
                reactions.put(initialSubstance, new ArrayList<>());
            }
            reactions.get(initialSubstance).add(resultSubstance);
        }

        String startSubstance = input.nextLine();
        String endSubstance = input.nextLine();

        transformationSteps.put(startSubstance, 0);
        int steps = findTransformationSteps(startSubstance, endSubstance);

        System.out.println(steps == Integer.MAX_VALUE ? -1 : steps);
    }

    public static int findTransformationSteps(String startSubstance, String endSubstance) {
        if (startSubstance.equals(endSubstance)) {
            return transformationSteps.get(startSubstance);
        }

        int minSteps = Integer.MAX_VALUE;
        if (reactions.containsKey(startSubstance)) {
            for (String nextSubstance : reactions.get(startSubstance)) {
                if (!transformationSteps.containsKey(nextSubstance) || transformationSteps.get(nextSubstance) > transformationSteps.get(startSubstance) + 1) {
                    transformationSteps.put(nextSubstance, transformationSteps.get(startSubstance) + 1);
                    int steps = findTransformationSteps(nextSubstance, endSubstance);
                    minSteps = Math.min(minSteps, steps);
                }
            }
        }

        return minSteps;
    }
}
