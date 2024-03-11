# Java 伪随机数生成器微基准测试

测试`java.util.Random`和`java.util.concurrent.ThreadLocalRandom`之间在多线程使用环境下的性能差异。

[性能测试代码](/src/main/java/io/github/muieer/java/RandomBenchmark.java)。

## 测试设备

The MacBook Pro (14-inch, Nov 2023) is a Mac laptop with an Apple M3 Pro processor with 11 CPU cores.

## 测试细节

预热十次，每次 10 秒，预热结束后执行 5 次，每次 10 秒。

### 单线程

Benchmark                                  Mode  Cnt           Score           Error  Units  
RandomBenchmark.measureRandom             thrpt    5   251203591.972 ±     86700.161  ops/s  
RandomBenchmark.measureThreadLocalRandom  thrpt    5  1164952470.575 ± 207160994.394  ops/s  

ThreadLocalRandom 单位吞吐是 Random 的 4 倍。

### 四线程

Benchmark                                  Mode  Cnt           Score          Error  Units  
RandomBenchmark.measureRandom             thrpt    5    26208154.695 ±   128410.716  ops/s  
RandomBenchmark.measureThreadLocalRandom  thrpt    5  4566191975.796 ± 54779410.176  ops/s  

ThreadLocalRandom 单位吞吐是 Random 的 228 倍。

### 八线程

Benchmark                                  Mode  Cnt           Score            Error  Units  
RandomBenchmark.measureRandom             thrpt    5     3883794.386 ±      42268.757  ops/s  
RandomBenchmark.measureThreadLocalRandom  thrpt    5  6567553418.240 ± 1078907213.443  ops/s  

ThreadLocalRandom 单位吞吐是 Random 的 2189 倍。

## 测试结论

在多线程使用环境下，`java.util.concurrent.ThreadLocalRandom`性能远胜于`java.util.Random`，线程数越多，性能优势越明显。