package org.kie.kogito.serverless.workflow.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.kie.kogito.serverless.workflow.utils.ExpressionHandlerUtils.SECRET_MAGIC;
import static org.kie.kogito.serverless.workflow.utils.ExpressionHandlerUtils.getSecret;

public class BuildEvaluator {

    private BuildEvaluator() {
    }

    private static final Pattern SECRET_PATTERN = Pattern.compile("\\$" + SECRET_MAGIC + "\\.(\\w+)");

    public static String eval(String value) {
        Matcher matcher = SECRET_PATTERN.matcher(value);
        return matcher.matches() ? getSecret(matcher.group(1)) : value;
    }
}
