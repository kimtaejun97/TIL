package com.studyweb.studyweb.modules.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyweb.studyweb.modules.account.form.*;
import com.studyweb.studyweb.modules.account.validator.NickNameValidator;
import com.studyweb.studyweb.modules.account.validator.PasswordValidator;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.tags.TagRepository;
import com.studyweb.studyweb.modules.tags.TagService;
import com.studyweb.studyweb.modules.zone.Zone;
import com.studyweb.studyweb.modules.zone.ZoneRepository;
import com.studyweb.studyweb.modules.zone.ZoneService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Table(name = "persistent_logins")
@Entity
public class PersistentLogins {

    @Id
    @Column(length = 64)
    private String series;

    @Column(nullable = false, length = 64)
    private String username;

    @Column(nullable = false, length = 64)
    private String token;

    @Column(name = "last_used",nullable = false, length = 64)
    private LocalDateTime lastUsed;


}
