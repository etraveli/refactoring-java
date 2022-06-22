package se.etraveli.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class MessageSourceServiceImpl implements MessageSourceService {
    private final MessageSource messageSource;

    @Override
    public String logMessage(String sourceKey, Object ... objects) {
        return messageSource.getMessage(
                sourceKey,
                objects,
                Locale.getDefault()
        );
    }
}
