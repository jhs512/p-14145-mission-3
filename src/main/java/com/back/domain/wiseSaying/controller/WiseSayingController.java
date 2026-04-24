package com.back.domain.wiseSaying.controller;
import com.back.AppContext;
import com.back.domain.wiseSaying.entity.WiseSayingEntity;

import java.util.*;

public class WiseSayingController {
    public void actionList() {
        List<WiseSayingEntity> entities =
                AppContext.wiseSayingService.findForList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("====================");
        for (var entity : entities) {
            System.out.printf("%d / %s / %s\n",
                    entity.getId(), entity.author, entity.wise);
        }
    }

    public void actionWrite() {
        System.out.print("명언 : ");
        String content = AppContext.scanner.nextLine();
        System.out.print("작가 : ");
        String author = AppContext.scanner.nextLine();
        int id = AppContext.wiseSayingService.write(content, author);
        System.out.printf("%d번 명언이 등록되었습니다.\n", id);
    }

    public void actionDelete(int targetId) {
        if (!AppContext.wiseSayingService.delete(targetId)) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", targetId);
            return;
        }
        System.out.printf("%d번 명언이 삭제되었습니다.\n", targetId);
    }

    public void actionModify(int targetId) {
        WiseSayingEntity entity =
                AppContext.wiseSayingService.findById(targetId);

        if (entity == null) {
            System.out.printf("%d번 명언은 존재하지 않습니다.\n", targetId);
            return;
        }
        System.out.printf("명언(기존) : %s\n", entity.wise);
        System.out.print("명언 : ");
        String content = AppContext.scanner.nextLine();
        System.out.printf("작가(기존) : %s\n", entity.author);
        System.out.print("작가 : ");
        String author = AppContext.scanner.nextLine();
        AppContext.wiseSayingService.modify(targetId, content, author);
    }
}
