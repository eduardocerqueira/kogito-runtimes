package org.jbpm.process.core.context.exception;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.jbpm.process.core.context.exception.ExceptionHandlerPolicyUtils.isExceptionErrorCode;

public class MessageContentRegexExceptionPolicy implements ExceptionHandlerPolicy {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessageContentRegexExceptionPolicy.class);

    @Override
    public boolean test(String errorCode, Throwable exception) {
        String msg = exception.getMessage();
        try {
            return msg != null && !isExceptionErrorCode(errorCode) && Pattern.compile(errorCode).matcher(msg).find();
        } catch (PatternSyntaxException ex) {
            LOGGER.debug("Failure parsing regular expression: {}", errorCode, ex);
        }
        return false;
    }
}
