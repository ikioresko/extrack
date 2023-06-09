package com.home.extrack.database;

import com.home.extrack.entity.FilterEntity;
import com.home.extrack.repository.FilterEntityRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class FiltersDB {
    private final FilterEntityRepo filterEntityRepo;

    public FiltersDB(FilterEntityRepo filterEntityRepo) {
        this.filterEntityRepo = filterEntityRepo;
    }

    @Transactional(readOnly = true)
    public List<FilterEntity> findAllByType(String type) {
        return filterEntityRepo.findAllByType(type);
    }

    @Transactional(readOnly = true)
    public Long getRegexpTableSizeByType(String type) {
        return filterEntityRepo.countFilterEntityRepoByTypeContaining(type);
    }
}
