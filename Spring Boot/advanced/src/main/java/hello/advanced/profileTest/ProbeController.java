package hello.advanced.profileTest;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ProbeController {
    private final AppProps props;
    private final Environment env;

    public ProbeController(AppProps props, Environment env) {
        this.props = props;
        this.env = env;
    }

    @GetMapping("/probe")
    public Map<String, Object> probe() {
        return Map.of(
                "app.profile", props.profile(),                // yml에 넣어둔 확인용 값
                "app.message", props.message(),
                "activeProfiles", List.of(env.getActiveProfiles()) // 진짜 활성 프로필
        );
    }
}