package com.back;

import com.back.domain.system.controller.SystemController;
import com.back.domain.wiseSaying.controller.WiseSayingController;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.Scanner;

public class App {
    private final Scanner scanner = new Scanner(System.in);
    private final WiseSayingController wiseSayingController;
    private final SystemController systemController;

    public App() {
        WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
        WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository);
        this.wiseSayingController = new WiseSayingController(scanner, wiseSayingService);
        this.systemController = new SystemController();
    }

    public void run() {
        System.out.println("== 명언 앱 ==");

        while1:
        while (true) {
            System.out.print("명언) ");
            String cmd = scanner.nextLine().trim();

            if (cmd.isBlank()) continue;

            Rq rq = new Rq(cmd);

            switch (rq.getActionName()) {
                case "종료" -> {
                    System.out.println("프로그램을 종료합니다.");
                    break while1;
                }
                case "등록" -> wiseSayingController.actionWrite();
                case "목록" -> wiseSayingController.actionList();
                case "삭제" -> wiseSayingController.actionDelete(rq);
                case "수정" -> wiseSayingController.actionModify(rq);
                case "도움" -> systemController.actionHelp();
            }
        }

        scanner.close();
    }
}