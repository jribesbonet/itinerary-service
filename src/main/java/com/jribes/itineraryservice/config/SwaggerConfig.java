package com.jribes.itineraryservice.config;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  @Bean
  public Contact contact(@Value("${swagger.contact.name}") String name,
      @Value("${swagger.contact.url}") String url,
      @Value("${swagger.contact.email}") String email) {
    return new Contact(name, url, email);
  }

  /**
   * Configure the ApiInfo swagger instance.
   * 
   * @param contact the contact.
   * @param title the title.
   * @param description the description.
   * @param version the version.
   * @param tosUrl the tosUrl.
   * @param license the license.
   * @param licenseUrl the license url.
   * @return the new ApiInfo instance with all content configured.
   */
  @Bean
  public ApiInfo apiInfo(Contact contact, @Value("${swagger.api.title}") String title,
      @Value("${swagger.api.description}") String description,
      @Value("${swagger.api.version}") String version,
      @Value("${swagger.api.tosUrl}") String tosUrl,
      @Value("${swagger.api.license}") String license,
      @Value("${swagger.api.licenseUrl}") String licenseUrl) {
    return new ApiInfoBuilder()//
        .title(title)//
        .description(description)//
        .version(version)//
        .termsOfServiceUrl(tosUrl)//
        .contact(contact)//
        .license(license)//
        .licenseUrl(licenseUrl)//
        .build();
  }

  /**
   * The Docket instance configuration.
   * 
   * @param apiInfo api information.
   * @param produces produced content type.
   * @param consumes consumes content type.
   * @return the new {@link Docket} type.
   */
  @Bean
  public Docket api(ApiInfo apiInfo, @Value("${swagger.api.produces}") String[] produces,
      @Value("${swagger.api.consumes}") String[] consumes) {
    // All the paths
    // All the apis
    return new Docket(DocumentationType.SWAGGER_2)//
        .apiInfo(apiInfo)//
        .produces(Sets.newHashSet(produces))//
        .consumes(Sets.newHashSet(consumes));
  }

}
