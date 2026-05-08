package csh.service;

import csh.entity.WiseSaying;
import csh.repository.WiseSayingRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class WiseSayingService {
    private final WiseSayingRepository repo = new WiseSayingRepository();
    private int id = 1;

    public int create(String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id++,content,author);
        repo.save(wiseSaying);
        return wiseSaying.getId();
    }

    public List<WiseSaying> getWiseSayingList() {
        return repo.findAll();
    }

    public Map<Integer,WiseSaying> findByIdForUpdate(int targetId) {
        Map<Integer,WiseSaying> map = new HashMap<>();
        List<WiseSaying> list = repo.findAll();
        int idx = findIdxById(list, targetId);
        if(idx == -1) return null;
        WiseSaying ws = list.get(idx);
        map.put(idx,ws);
        return map;
    }

    public void updateByIdx(int idx, String content, String author) {
        List<WiseSaying> list = repo.findAll();
        WiseSaying ws = list.get(idx);
        ws.setContent(content);
        ws.setAuthor(author);
        repo.update(idx, ws);
    }

    public boolean deleteById(int id) {
        List<WiseSaying> list = repo.findAll();
        int idx = findIdxById(list, id);
        if(idx == -1) return false;
        repo.delete(idx);
        return true;
    }

    private int findIdxById(List<WiseSaying> list, int targetId) {
        if(list.isEmpty()) return -1;
        int idx = IntStream
                .range(0, list.size())
                .filter(i -> list.get(i).getId() == targetId)
                .findFirst().orElse(-1);
        return idx;
    }
}
