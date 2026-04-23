package com.back.domain.system.controller;

import com.back.domain.wiseSaying.controller.WiseSayingController;

public class SystemController {
    WiseSayingController wiseSayingController = new WiseSayingController();

    public void getCommand(String userCommand) {
        switch(userCommand) {
            case "등록":
                wiseSayingController.createWiseSaying();
                break;
            case "목록":
                wiseSayingController.getWiseSaying();
                break;
            default:
                String[] splitCommand = userCommand.split("\\?id=");
                switch (splitCommand[0]) {
                    case "삭제":
                        wiseSayingController.deleteWiseSaying(Integer.parseInt(splitCommand[1]));
                        break;
                    case "수정":
                        wiseSayingController.updateWiseSaying(Integer.parseInt(splitCommand[1]));
                        break;
                }
        }
    }
}
