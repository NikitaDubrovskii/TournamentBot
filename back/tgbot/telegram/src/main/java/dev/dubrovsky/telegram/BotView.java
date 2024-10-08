package dev.dubrovsky.telegram;

import dev.dubrovsky.action.*;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BotView {

    private final ActionStart actionStart;
    private final ActionRegistration actionRegistration;
    private final ActionSettings actionSettings;

    public BotView(ActionStart actionStart, ActionRegistration actionRegistration,
                   ActionSettings actionSettings) {
        this.actionStart = actionStart;
        this.actionRegistration = actionRegistration;
        this.actionSettings = actionSettings;
    }

    public Map<String, IAction> getActions() {
        return Map.of(
                "/start", actionStart,
                "/register", actionRegistration,
                "/settings", actionSettings
        );
    }

}
