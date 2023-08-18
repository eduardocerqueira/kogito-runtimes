package org.kie.kogito.process.expr;

import java.util.Optional;
import java.util.ServiceLoader;

public class ExpressionHandlerFactory {

    private ExpressionHandlerFactory() {
    }

    private static final ServiceLoader<ExpressionHandler> serviceLoader = ServiceLoader.load(ExpressionHandler.class);

    public static Expression get(String lang, String expr) {
        return getExpressionHandler(lang).orElseThrow(
                () -> new IllegalArgumentException("Unsupported language " + lang)).get(expr);
    }

    public static boolean isSupported(String lang) {
        return serviceLoader.stream().anyMatch(p -> p.get().lang().equals(lang));
    }

    private static Optional<ExpressionHandler> getExpressionHandler(String lang) {
        return serviceLoader.stream().filter(p -> p.get().lang().equals(lang)).findFirst().map(p -> p.get());
    }
}
