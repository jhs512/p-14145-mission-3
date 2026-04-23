package com.back.domain.wiseSaying.controller;


import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    private final WiseSayingService wiseSayingService;
    private final Scanner sc; // final로 선언해서 외부에서 주입받기

    // 서비스 주입
    public WiseSayingController(WiseSayingService wiseSayingService, Scanner sc) {
        this.wiseSayingService = wiseSayingService;
        this.sc = sc;
    }

    // 명언 등록
    public void doWrite() {
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.print("작가 : ");
        String author = sc.nextLine();
        int id = wiseSayingService.write(content, author);
        System.out.println(id + "번 명언이 등록되었습니다.");
    }

    // 명언 리스트
    public void showList() {
        List<WiseSaying> wiseSayings = wiseSayingService.findAll();
        List<WiseSaying> sortedWiseSayings = new ArrayList<>(wiseSayings);
        Collections.reverse(sortedWiseSayings);

        System.out.println("번호 / 작가 / 명언");
        System.out.println("--------------------------");
        for (WiseSaying wiseSaying : sortedWiseSayings) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " +wiseSaying.getContent());
        }
    }

    // 명언 수정
    public void doModify(String command) {
        int id = getParamAsInt(command, "id", -1);
        if (id == -1) return;

        WiseSaying ws = wiseSayingService.findById(id);
        if(ws == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        System.out.println("명언(기존) : " + ws.getContent());
        System.out.print("명언 : ");
        String content = sc.nextLine();
        System.out.println("작가(기존) : " + ws.getAuthor());
        System.out.print("작가 : ");
        String author = sc.nextLine();

        if (wiseSayingService.modify(id, content, author)) {
            System.out.println(id + "번 명언이 수정되었습니다.");
        }
    }

    // 명언 삭제
    public void doRemove(String command) {
        int id = getParamAsInt(command, "id", -1);
        if (id == -1) return;

        WiseSaying ws = wiseSayingService.findById(id);
        if(ws == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }

        if (wiseSayingService.remove(id)) {
            System.out.println(id + "번 명언이 삭제되었습니다.");
        }
    }

    // 간단한 파싱 유틸리티 메서드 (코드 중복 제거 및 예외 방지)
    private int getParamAsInt(String command, String paramName, int defaultValue) {
        try {
            // 명령어를 분석하여 ID 값을 추출하는 로직 (삭제?id=1 등)
            String[] urlBits = command.split("\\?");
            String[] paramBits = urlBits[1].split("&");
            for (String paramBit : paramBits) {
                String[] bit = paramBit.split("=");
                if (bit[0].equals(paramName)) {
                    return Integer.parseInt(bit[1]);
                }
            }
        } catch (Exception e) {
            System.out.println("번호를 올바르게 입력해주세요.");
        }
        return defaultValue;
    }
}
