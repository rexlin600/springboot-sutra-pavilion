package xyz.rexlin600.java8.lambda;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LambdaBizTest {

    @Test
    public void buildStream() {
        log.info("==================================== buildStream Test Start ====================================");
        LambdaBiz lambdaBiz = new LambdaBiz();
        lambdaBiz.buildStream();
        log.info("==================================== buildStream Test End ====================================");
    }
}