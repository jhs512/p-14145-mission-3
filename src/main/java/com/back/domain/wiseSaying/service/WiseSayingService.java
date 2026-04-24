package com.back.domain.wiseSaying.service;

import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public WiseSaying write(String content, String author) {
        int id = wiseSayingRepository.getNextId();
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        return wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> findForList() {
        return wiseSayingRepository.findAll();
    }

    public boolean delete(int id) {
        return wiseSayingRepository.deleteById(id);
    }

    public WiseSaying findById(int id) {
        return wiseSayingRepository.findById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {
        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}