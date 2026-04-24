package com.back.domain.wiseSaying.controller;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.service.WiseSayingService;

import java.util.List;
import java.util.Scanner;

public class WiseSayingController {
    WiseSayingService service = new WiseSayingService();
    Scanner sc = new Scanner(System.in);

    public void enroll() {
        System.out.print("명언 : ");
        String saying = sc.nextLine().trim();
        System.out.print("작가 : ");
        String writer = sc.nextLine().trim();
        System.out.println(service.enroll(saying, writer) + "번 명언이 등록되었습니다.");
    }

    public void printList() {
        List<WiseSaying> list =  service.getWisSayingList();
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for(int i = list.size() -1; i >= 0; i--) {
            WiseSaying cur = list.get(i);
            System.out.println(cur.num + " / " + cur.writer + " / " + cur.wiseSaying);
        }
    }

    public void delete(String cmd) {
        int id;
        try {
            id = Integer.parseInt(cmd.split("=")[1]);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            System.out.println("올바른 형식이 아닙니다. 예: 삭제?id=1");
            return;
        }

        if (id < 1) {
            System.out.println("잘못된 id 값입니다.");
            return;
        }

        boolean isOk = service.delete(id);
        if (isOk) System.out.println(id + "번 명언이 삭제되었습니다.");
        else      System.out.println(id + "번 명언은 존재하지 않습니다.");
    }

    public void update(String cmd) {
        try {
            int id = Integer.parseInt(cmd.split("=")[1]);

            if(id < 1) {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
                return;
            }
            WiseSaying bef = service.getWiseSaying(id);
            if (bef == null) {
                System.out.println(id + "번 명언은 존재하지 않습니다.");
                return;
            }

            System.out.println("명언(기존) : " + bef.wiseSaying);
            System.out.print("명언 : ");
            String saying = sc.nextLine().trim();
            System.out.println("작가(기존) : " + bef.writer);
            System.out.print("작가 : ");
            String writer = sc.nextLine().trim();
            service.update(id, saying, writer);

            System.out.println(id + "번 명언이 수정되었습니다.");
        } catch(Exception e) {
            System.out.println("id는 숫자여야 합니다.");
        }
    }
}
