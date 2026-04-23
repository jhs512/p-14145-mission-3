package org.example;

//사용자 입력을 받고 WiseSayingController 에게 넘거야 하는지 판단해서 맞으면 넘김

import java.util.Scanner;

public class App {
    public void run() {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        System.out.println("== 명언 앱 ==");

        WiseSayingRepository ac =  new WiseSayingRepository();

        while(true) {
            count++;
            System.out.print("명령)");
            String md = sc.nextLine().trim();

            if (md.equals("종료")) {
                return;
            }

            if (md.equals("등록")) {
                System.out.print("명언 : ");
                String md2 = sc.nextLine().trim();
                System.out.print("작가 : ");
                String md3 = sc.nextLine().trim();

               ac.generateWiseSaying(count, md2 , md3);

            } else if (md.equals("목록")) {
                ac.viewWiseSaying();

            } else if (md.contains("삭제")) {
                int md4 = Integer.parseInt(md.substring(6).trim());
                ac.deleteWiseSaying(md4);

            } else if (md.contains("수정")) {
                int md4 = Integer.parseInt(md.substring(6).trim());
                ac.modifyWiseSaying(md4);

            } else {
                System.out.println("잘못된접근");
            }
        }



    }

}
