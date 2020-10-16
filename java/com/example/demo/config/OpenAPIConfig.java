package com.example.demo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration

// DO NOT USE this annotation for values that are dynamic, and need to come from configserver/props, use Bean OpenAPI instead.
@OpenAPIDefinition(info = @Info(title = "Person API", version = "${springdoc.version:V1}", description = "Person Books Data", license = @License(name = "Apache 2.0", url = "http://foo.bar"), contact = @Contact(url = "http://foo.com", name = "sp", email = "sp@foo.com")))

// Use below for any local variations. Put GLOBAL security in  the openAPI bean.
// @SecurityScheme(name = "security_auth", type = SecuritySchemeType.OAUTH2,flows = @OAuthFlows(authorizationCode = @OAuthFlow(authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}", tokenUrl = "${springdoc.oAuthFlow.tokenUrl}", scopes = {@OAuthScope(name = "read", description = "read scope"),@OAuthScope(name = "write", description = "write scope") })))
public class OpenAPIConfig
{
    
    
    @Value("${springdoc.swagger-ui.oauth.access-token-uri}")
    private String accessTokenUri;
    
    
    
    
    // Use this instead of @OpenAPIDef annotation if, you want to pass the values dynamically from app.properties. 
    
    //@formatter:off
    /*
           ${springdoc.uri}
            String uri;
            
        @Bean
        public OpenAPI customOpenAPI(@Value("${springdoc.version: oas3;1.4.8}") String appVersion)
        {
            
            return new OpenAPI().components(new Components())
                    .info(new Info().title("Person Books API").version(appVersion)
                            .license(new License().name("Apache 2.0")
                                    .url(uri)));
          
          }
     */
    //@formatter:on
     
  //@formatter:off
   
    
    @Bean
    public OpenAPI customOpenAPI() {
        
      Scopes scopes = new Scopes();
      scopes.addString("sp-api-doc.read", " sp-api-doc.read");
      scopes.addString("sp-api-doc.write", " sp-api-doc.read");
      
      OAuthFlow clientCreds = new OAuthFlow();      
      clientCreds.setTokenUrl(accessTokenUri);
      clientCreds.setScopes(scopes);
        
      return new OpenAPI()
                          .components(
                                  new Components()
                                  .addSecuritySchemes(
                                          "sasi-sec",
                                          new SecurityScheme().type(SecurityScheme.Type.OAUTH2)
                                                                                  .scheme("bearer")
                                                                                  .bearerFormat("JWT")
                                                                                  .in(SecurityScheme.In.HEADER)
                                                                                  .name("Authorization")
                                                                                  .flows(new OAuthFlows()
                                                                                              .clientCredentials(clientCreds))
                                                                                  
                                                                             )
                                                                                  
                                  ) 
                .addSecurityItem(new SecurityRequirement().addList("sasi-sec"))
              //.addSecurityItem(new SecurityRequirement().addList("sasi-sec", Arrays.asList("read", "write")))
              ;
    }
    
  //@formatter:on

}
