package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private final List<WiseSaying> list = new ArrayList<>();
    private int lastId = 0;

    public int enroll(String saying, String writer) {
        list.add(new WiseSaying(++lastId, saying, writer));
        return lastId;
    }

    public boolean delete(int id) {
        return list.removeIf(w -> w.num == id);
    }

    public void update(int id, String saying, String writer) {
        for (WiseSaying w : list) {
            if (w.num == id) {
                w.wiseSaying = saying;
                w.writer = writer;
                return;
            }
        }
    }

    public List<WiseSaying> getWisSayingList() {
        return new ArrayList<>(list);
    }

    public WiseSaying getWiseSaying(int id) {
        for (WiseSaying w : list) {
            if (w.num == id) return w;
        }
        return null;
    }
}
