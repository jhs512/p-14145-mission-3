package csh.repository;

import csh.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;

public class WiseSayingRepository {
    private List<WiseSaying> list = new ArrayList<>();
    public void save(WiseSaying wiseSaying) {
        list.add(0, wiseSaying);
    }

    public List<WiseSaying> findAll() {
        return list;
    }

    public void update(int idx, WiseSaying wiseSaying) {
        list.set(idx, wiseSaying);
    }

    public void delete(int idx) {
        WiseSaying ws = list.remove(idx);
    }
}
