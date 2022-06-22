package se.etraveli.service;

public interface MessageSourceService {
    String logMessage(String sourceKey, Object ... objects);
}
