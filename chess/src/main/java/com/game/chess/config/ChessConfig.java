package com.game.chess.config;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.game.chess.service.ChessService;

@Component
public class ChessConfig {
	@PostConstruct
    public void init(){
       System.out.println("-----------Welcome to the chess board----------------");
       ChessService chessService = ChessService.getChessServiceInstance();
       chessService.playChess();
    }
}
