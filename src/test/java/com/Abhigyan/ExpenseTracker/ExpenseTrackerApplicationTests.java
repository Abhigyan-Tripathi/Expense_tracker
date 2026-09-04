package com.Abhigyan.Expensetracker;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest boots the full Spring application context for this
// test - it's a smoke test that fails if any bean is misconfigured
// (e.g. a missing dependency, a broken @Autowired wiring) even before we
// write any real test logic.
@SpringBootTest
class ExpenseTrackerApplicationTests {

    @Test
    void contextLoads() {
        // Intentionally empty - if the context fails to start, this
        // test fails automatically.
    }
}
