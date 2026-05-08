package csh.controller;

import csh.entity.WiseSaying;
import csh.service.WiseSayingService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class WiseSayingController {
    private final Scanner sc;
    private final WiseSayingService service = new WiseSayingService();

    public WiseSayingController(Scanner scanner) {
        this.sc = scanner;
    }

    public void requestCreate() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        int id = service.create(content, author);
        System.out.println("%d번 명언이 등록되었습니다.".formatted(id));
    }

    public void requestShowList() {
        List<WiseSaying> list = service.getWiseSayingList();
        if(list.isEmpty()) {
            System.out.println("등록된 명언이 없습니다.");
            return;
        }
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");
        for (WiseSaying wiseSaying : list) {
            System.out.println(wiseSaying.getId() + " / " + wiseSaying.getAuthor() + " / " + wiseSaying.getContent());
        }
    }

    public void requestUpdate(int id) {
        Map<Integer,WiseSaying> map = service.findByIdForUpdate(id);
        if(map == null || map.containsKey(-1)) {
            System.out.println("%d번 명언은 존재하지않습니다.".formatted(id));
            return;
        }
        int idx = map.keySet().stream().findFirst().get();
        WiseSaying wiseSaying = map.get(idx);
        System.out.println("명언(기존) : %s".formatted(wiseSaying.getContent()));
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();
        System.out.println("저자(기존) : %s".formatted(wiseSaying.getAuthor()));
        System.out.print("저자 : ");
        String author = sc.nextLine().trim();
        service.updateByIdx(idx, content, author);
        System.out.println("%d번 명언이 수정되었습니다.".formatted(id));
    }

    public void requestDelete(int id) {
        boolean flag = service.deleteById(id);
        if(flag) System.out.println("%d번 명언이 삭제되었습니다.".formatted(id));
        else System.out.println("%d번 명언은 존재하지않습니다.".formatted(id));
    }
}
