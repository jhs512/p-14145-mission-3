package com.back.domain.WiseSaying.controller;

import com.back.AppTestRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WiseSayingControllerTest {

    @Test
    @DisplayName("등록")
    void t1() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                종료
                """);

        assertThat(out)
                .contains("명언 :")
                .contains("작가 :")
                .contains("1번 명언이 등록되었습니다.");
    }

    @Test
    @DisplayName("목록")
    void t2() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                등록
                나의 죽음을 적에게 알리지 마라.
                이순신
                목록
                종료
                """);

        assertThat(out)
                .contains("번호 / 작가 / 명언")
                .contains("1 / 작자미상 / 현재를 사랑하라.")
                .contains("2 / 이순신 / 나의 죽음을 적에게 알리지 마라.");
    }

    @Test
    @DisplayName("삭제 - 존재하는 명언")
    void t3() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                삭제?id=1
                종료
                """);

        assertThat(out).contains("1번 명언이 삭제되었습니다.");
    }

    @Test
    @DisplayName("삭제 - 존재하지 않는 명언")
    void t4() {
        String out = AppTestRunner.run("""
                삭제?id=99
                종료
                """);

        assertThat(out).contains("99번 명언은 존재하지 않습니다.");
    }

    @Test
    @DisplayName("수정")
    void t5() {
        String out = AppTestRunner.run("""
                등록
                현재를 사랑하라.
                작자미상
                수정?id=1
                현재를 사랑하라!
                홍길동
                종료
                """);

        assertThat(out)
                .contains("명언(기존) : 현재를 사랑하라.")
                .contains("작가(기존) : 작자미상");
    }

    @Test
    @DisplayName("종료")
    void t6() {
        String out = AppTestRunner.run("""
                종료
                """);

        assertThat(out).contains("프로그램을 종료합니다.");
    }
}
