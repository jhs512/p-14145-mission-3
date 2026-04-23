package com.back.domain.wiseSaying.service;


import com.back.domain.wiseSaying.entity.WiseSaying;
import com.back.domain.wiseSaying.repository.WiseSayingRepository;

import java.util.List;

public class WiseSayingService {
    WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void register(int id, String content, String author) {
        WiseSaying wiseSaying = new WiseSaying(id, content, author);
        wiseSayingRepository.save(wiseSaying);
    }

    public List<WiseSaying> getList() {
        return wiseSayingRepository.findAll();
    }

    public boolean delete (int id) {
        WiseSaying byId = wiseSayingRepository.findById(id);
        if (byId != null) {
            wiseSayingRepository.delete(byId);
            return true;
        }
        return false;
    }



}
