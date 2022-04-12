package com.hungerheroes.backend.config;

import org.springframework.data.domain.AuditorAware;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AuditorAwareImpl extends AuditorAware {
    @Override
    Optional<String> getCurrentAuditor();

    Optional<LocalDateTime> getCreatedOn();
}
