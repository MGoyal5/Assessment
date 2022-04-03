package com.game.chess;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.game.chess.config.ChessConfig;

@SpringBootTest
class ChessApplicationTests {

	@MockBean
	private ChessConfig chessConfig;

	@Test
	void contextLoads() {
	}

}
