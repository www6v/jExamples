package ddd.refactor.domain.messaging;

import ddd.refactor.domain.domain.types.AuditMessage;

public interface AuditMessageProducer {
    SendResult send(AuditMessage message);
}
