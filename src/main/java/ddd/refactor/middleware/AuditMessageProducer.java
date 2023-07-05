package ddd.refactor.middleware;

import ddd.refactor.domain.AuditMessage;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
