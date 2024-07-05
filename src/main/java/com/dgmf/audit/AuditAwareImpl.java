package com.dgmf.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

// Help Spring Data JPA to Identify Who is the Logged Trying to
// Perform Certain Actions inside the Application
@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // Retrieve Current User Name or null
        return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
