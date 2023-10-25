package ru.sergdm.ws.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.sergdm.ws.model.Notification;

import java.util.List;

public interface NotificationRepository extends CrudRepository<Notification, Long>,
		JpaSpecificationExecutor<Notification> {
}
