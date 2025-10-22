package hello.advanced.profileTest;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
public record AppProps(String name, String profile, String message) {}