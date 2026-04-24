package com.back.domain.system.controller;

public class SystemController {

    public void actionHelp() {
        System.out.println("""
                = 명령어 목록 =
                1. 등록
                2. 목록
                3. 수정?id={번호}
                4. 삭제?id={번호}
                """);
    }
}