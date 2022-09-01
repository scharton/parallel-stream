package parallel.stream;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RequestUtilTest {

  @Test
  @Disabled
  void basic() {
    RequestUtil util = new RequestUtil();
    String data = util.getData(Optional.of(1000), Optional.of(200));
    System.out.println(data);
  }

  @Test
  void multi() {
    List<String> l = new ArrayList<String>();
    l.add("2, 200");
    l.add("2, 200");
    l.add("2, 200");
    single(l);
    para(l);
  }

  private void single(List<String> l) {
    long start = Instant.now().toEpochMilli();
    l.stream()
        .forEach(
            item -> {
              int delay = Integer.parseInt(item.split(",")[0].trim());
              int http = Integer.parseInt(item.split(",")[1].trim());
              RequestUtil util = new RequestUtil();
              String data = util.getData(Optional.of(delay), Optional.of(http));
              System.out.println(data);
            });
    long finish = Instant.now().toEpochMilli();
    System.out.println("single ==> " + (finish - start));
  }

  private void para(List<String> l) {

    long start = Instant.now().toEpochMilli();
    l.parallelStream()
        .forEach(
            item -> {
              int delay = Integer.parseInt(item.split(",")[0].trim());
              int http = Integer.parseInt(item.split(",")[1].trim());
              RequestUtil util = new RequestUtil();
              String data = util.getData(Optional.of(delay), Optional.of(http));
              System.out.println(data);
            });
    long finish = Instant.now().toEpochMilli();
    System.out.println("parallel ==> " + (finish - start));
  }
}
