package org.example;

//고객의 명령을 입력받고 적절한 응답을 표현 / 명언에 관한 응대

import java.util.ArrayList;

public class WiseSayingController {
    public void generatePrint(int count){

        System.out.println(count + "번 명언이 등록되었습니다.");
    }

    public void viewPrint(ArrayList<WiseSaying> arrList){
        System.out.println("번호 / 작가 / 명언");
        System.out.println("----------------------");

        for (int i = arrList.size() - 1; i >= 0; i--) {
            WiseSaying ws = arrList.get(i);
            System.out.println(ws.number + " / " + ws.writer + " / " + ws.wise);
        }
    }
    public void deleteWiseSaying(int md5, int f){
        if(f ==1){
            System.out.println(md5 + "번 명언은 존재하지 않습니다.");
        }else{
            System.out.println(md5 + "번 명언이 삭제되었습니다.");
        }
    }

    public void scanWiseSaying1(String md5){
        System.out.println("명언(기존) : " + md5);
    }
    public void scanWiseSaying2(String md5){
        System.out.println("작가(기존) : " + md5);
    }
}
