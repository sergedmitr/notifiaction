package ru.sergdm.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sergdm.ws.exception.ResourceNotFoundException;
import ru.sergdm.ws.model.Notification;
import ru.sergdm.ws.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NotificationRepository notificationRepository;

	public List<Notification> getNotifications() {
		List<Notification> notifications = new ArrayList<>();
		notificationRepository.findAll().forEach(notifications::add);
		logger.info("notifications = {}", notifications);
		return notifications;
	}

	public Notification getNotification(Long notificationId) throws ResourceNotFoundException{
		Notification notification = notificationRepository.findById(notificationId)
				.orElseThrow(() -> new ResourceNotFoundException("Notification not found"));
		logger.info("notification = {}", notification);
		return notification;
	}

	public Notification addNotification(Notification notification) {
		return notificationRepository.save(notification);
	}

	public void deleteAll() {
		notificationRepository.deleteAll();
	}
}
