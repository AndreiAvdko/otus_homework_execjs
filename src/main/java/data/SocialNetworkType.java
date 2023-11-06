package data;

public enum SocialNetworkType {
    FACEBOOK("Facebook"),
    VK("VK"),
    OK("OK"),
    SKYPE("Skype"),
    VIBER("Viber"),
    TELEGRAM("Telegram"),
    WHATSAPP("WhatsApp"),
    HABR("Habr");

    private final String name;
    SocialNetworkType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
