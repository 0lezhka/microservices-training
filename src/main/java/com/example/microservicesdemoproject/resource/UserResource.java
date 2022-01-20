package com.example.microservicesdemoproject.resource;

import com.example.microservicesdemoproject.dto.HateoasResponseDto;
import com.example.microservicesdemoproject.dto.ValidationDto;
import com.example.microservicesdemoproject.exception.UserNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
@Log4j2
public class UserResource {
    private static int ID;

    @GetMapping
    public String helloWorld() {
        return "Hello world";
    }

    /*
        Коли створюєш новий ресурс, то кльово додавати location, щоб можна було знати, як до того ресурсу доступитись.
        location Додається в header і являє собою лінк на ресурс, який був створений, у цьому випадку:
        http://localhost:8080/users/2
     */
    @PostMapping("users")
    public ResponseEntity<Object> createUser(String name) {
        log.debug("New User '%s' created".formatted(name));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(++ID)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("validation")
    public EntityModel<HateoasResponseDto> validationEndpoint(@Valid ValidationDto validationDto) {
        // Так можна отримати лінку на ендпоінт за назвою методу та класом контроллера
//        MvcUriComponentsBuilder.fromMethodName(UserResource.class, "causeNotFoundException").build();
        // Якщо є залежність на hateoas, то можна так
//        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).causeNotFoundException());

        // Дана конструкація повертає об'єкт, який містить HateoasResponseDto, а також додатково поле, яке містить
        return EntityModel.of(new HateoasResponseDto("Validation result"),
                List.of(
                        // Якщо передати параметер, то він підставиться в урлу
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).causeNotFoundException(12L)).withRel("not-found"),
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(getClass()).createUser(null)).withRel("create-user")
                ));
    }

    @GetMapping("not-found/{id}")
    public EntityModel<Object> causeNotFoundException(@PathVariable("id") Long id) {
        throw new UserNotFoundException("User not found");
    }

    @Autowired
    MessageSource messageSource;

    /*
        Даний підхід можна використовувати для інтернаціоналізації
     */
    @GetMapping("localized")
    public String getLocalizedMessage(
            @RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, "Default message", locale);
    }

    @GetMapping("xml-representation")
    public ValidationDto getXmlRepresentation() {
        return new ValidationDto("Test name", 12L);
    }
}
