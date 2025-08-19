package org.example.ytlearning.service;

import ch.qos.logback.core.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service

public class UserHandlerService {
    public boolean usernameHasText(String username) {
      return StringUtils.hasText(username);
    }
}
