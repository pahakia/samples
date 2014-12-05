package pahakia.gplus.settings;

import pahakia.settings.PatternSetting;
import pahakia.settings.annotation.RuntimeSettings;

@RuntimeSettings
public final class GooglePlusSettings {
    private GooglePlusSettings() {
    }

    public static final PatternSetting GOOGLE_PLUS_CLIENT_ID = new PatternSetting("google.plus.client.id",
            "Google+ Sign-In client id, you must register as a developer.",
            "^\\d+-\\w+\\.apps\\.googleusercontent\\.com$");
    public static final PatternSetting GOOGLE_PLUS_CLIENT_SECRET = new PatternSetting("google.plus.client.secret",
            "Google+ Sign-In client secret, you must register as a developer.", "\\w+");
}
