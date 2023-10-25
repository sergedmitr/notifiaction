package ru.sergdm.ws;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sergdm.ws.exception.ResourceNotFoundException;
import ru.sergdm.ws.model.Notification;
import ru.sergdm.ws.model.SystemName;
import ru.sergdm.ws.service.NotificationService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ApiController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private NotificationService notificationService;

	@GetMapping("/")
	public ResponseEntity<Object> name() {
		SystemName name = new SystemName();
		return new ResponseEntity<>(name, HttpStatus.OK);
	}

	@GetMapping("/notifications")
	public ResponseEntity<List<Notification>> deliveries(){
		logger.info("notifications");
		return new ResponseEntity(notificationService.getNotifications(), HttpStatus.OK);
	}

	@GetMapping("/notifications/{notificationId}")
	public ResponseEntity<?> rest(@PathVariable Long notificationId){
		logger.info("notifications. notificationId = {}", notificationId);
		try {
			Notification notification = notificationService.getNotification(notificationId);
			return new ResponseEntity(notification, HttpStatus.OK);
		} catch (ResourceNotFoundException ex) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
		}
	}

	@PostMapping("/notifications")
	public ResponseEntity<?> addNotification(@Valid @RequestBody Notification notification) {
		logger.info("addNotification. notification = {}", notification);
		Notification notificationNew = notificationService.addNotification(notification);
		return ResponseEntity.ok().body(notificationNew);
	}

	@DeleteMapping("/notifications")
	public ResponseEntity<?> deleteNotifications(){
		logger.info("Delete all notifications");
		notificationService.deleteAll();
		return ResponseEntity.ok().body(HttpStatus.OK);
	}
}
