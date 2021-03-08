import multiplicators.IMultiplicator;

import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.run();
    }
}


class Calculator {
    public void run() {
        List<IMultiplicator> multiplicatorList = new ArrayList<>();
        for (IMultiplicator multiplicator : multiplicatorList) {
            List<Long> results = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                multiplicator.getResult();
                long stopTime = System.nanoTime();
                Long time = stopTime - startTime;
                results.add(time);
            }
            Long averageResult = average(results);
            String description = multiplicator.getDescription();
            System.out.println(description + ": " + averageResult);
        }
    }

    public Long average(List<Long> results) {
        Long sum = 0L;
        if (!results.isEmpty()) {
            for (Long result : results) {
                sum += result;
            }
            return sum / results.size();
        }
        return sum;
    }
}
