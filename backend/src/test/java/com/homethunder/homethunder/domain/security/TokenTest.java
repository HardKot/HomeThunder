package com.homethunder.homethunder.domain.security;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;


import java.time.*;
import java.util.Set;

import static org.mockito.Mockito.mockStatic;


class TokenTest {
    private Clock clock = Clock.fixed(Instant.parse("2024-04-12T00:00:00Z"), ZoneId.of("UTC"));
    private LocalDateTime localDateTime = LocalDateTime.now(clock);


    @Test
    public void TokenIsRefreshing() {
        Token token = new Token(
                null,
                null,
                "test junit",
                "127.0.0.1",
                Set.of(),
                LocalDateTime.of(2024, Month.APRIL, 15, 0, 39)
        );

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);

            Assertions.assertTrue(token.isRefreshing());
        }
    }

    @Test
    public void TokenIsNoRefreshing() {
        Token token = new Token(
                null,
                null,
                "test junit",
                "127.0.0.1",
                Set.of(),
                LocalDateTime.of(2024, Month.MARCH, 1, 0, 39)
        );

        try (MockedStatic<LocalDateTime> mockedStatic = mockStatic(LocalDateTime.class)) {
            mockedStatic.when(LocalDateTime::now).thenReturn(localDateTime);


            Assertions.assertFalse(token.isRefreshing());
        }
    }
}
