package com.back.domain.wiseSaying.repository;

import com.back.domain.wiseSaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

public class WiseSayingRepository {
    private int lastId = 0;
    private final List<WiseSaying> wiseSayings = new ArrayList<>();

    public int getNextId() {
        return ++lastId;
    }

    public WiseSaying save(WiseSaying wiseSaying) {
        wiseSayings.add(wiseSaying);
        return wiseSaying;
    }

    public List<WiseSaying> findAll() {
        List<WiseSaying> result = new ArrayList<>(wiseSayings);
        Collections.reverse(result);
        return result;
    }

    public WiseSaying findById(int id) {
        return wiseSayings.stream()
                .filter(w -> w.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public boolean deleteById(int id) {
        int index = IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getId() == id)
                .findFirst()
                .orElse(-1);

        if (index == -1) return false;

        wiseSayings.remove(index);
        return true;
    }
}