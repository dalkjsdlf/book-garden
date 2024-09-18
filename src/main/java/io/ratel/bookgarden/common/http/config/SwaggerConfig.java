package io.ratel.bookgarden.common.http.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "User Book API", version = "v1"), 
                    security = @SecurityRequirement(name = "X_USER_ID"))
@SecurityScheme(name = "X_USER_ID", type = SecuritySchemeType.APIKEY, in = SecuritySchemeIn.HEADER)
public class SwaggerConfig {
}