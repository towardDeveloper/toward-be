package toward.towardbackend.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContext;
import toward.towardbackend.config.jwt.JwtProperties;
import toward.towardbackend.config.jwt.JwtTokenProvider;

@OpenAPIDefinition(
        info = @Info(title = "향해 API 명세서",
                description = "향해 API 명세서입니다",
                version = "v1"))
@Configuration
public class SwaggerConfig {

    @Bean
    // 운영 환경에는 Swagger를 비활성화하기 위해 추가했습니다.
    @Profile("!deploy")
    public OpenAPI api() {
        SecurityScheme apiKey = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .scheme("bearer")
                .bearerFormat("JWT");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("Bearer Token");

        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKey))
                .addSecurityItem(securityRequirement);
    }




}
