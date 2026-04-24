package com.back;
import java.util.Scanner;

public class App {

    public void run() {

        System.out.println("== 명언 앱 ==");

        Scanner sc = AppContext.scanner;

        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료")) {
                break;
            }
            if (cmd.equals("등록")) {
                AppContext.wiseSayingController.actionWrite();
                continue;
            }
            if (cmd.equals("목록")) {
                AppContext.wiseSayingController.actionList();
                continue;
            }

            String[] cmds = cmd.split("\\?id=");

            if (cmds.length < 2) continue;

            int targetId = Integer.parseInt(cmds[1]);

            if (cmds[0].equals("삭제")) {
                AppContext.wiseSayingController.actionDelete(targetId);
                continue;
            }
            if (cmds[0].equals("수정")) {
                AppContext.wiseSayingController.actionModify(targetId);
            }
        }
    }
}
