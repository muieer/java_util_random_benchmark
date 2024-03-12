package io.github.muieer.java;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBenchmark {

    private static final Random RANDOM = new Random();
    private static final ThreadLocal<Random> MY_THREAD_LOCAL_RANDOM = ThreadLocal.withInitial(Random::new);

    @Benchmark
    public static void measureRandom() {
        RANDOM.nextInt(1_000_000);
    }

    @Benchmark
    public static void measureThreadLocalRandom() {
        ThreadLocalRandom.current().nextInt(1_000_000);
    }

    @Benchmark
    public static void measureMyThreadLocalRandom() {
        MY_THREAD_LOCAL_RANDOM.get().nextInt(1_000_000);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(RandomBenchmark.class.getSimpleName())
                .threads(1)
                .forks(1)
                .warmupIterations(10)
                .build();

        new Runner(opt).run();
    }

}
