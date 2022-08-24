package br.com.lucas.forum.config.dto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;

public class ErrorFormDto {

    private String field;

    private String error;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static List<ErrorFormDto> convert(BindingResult bindingResult, MessageSource messageSource) {
        return bindingResult.getFieldErrors().stream().map(error -> {
            ErrorFormDto dto = new ErrorFormDto();
            dto.setField(error.getField());
            dto.setError(messageSource.getMessage(error, LocaleContextHolder.getLocale()));
            return dto;
        }).collect(Collectors.toList());
    }

}
