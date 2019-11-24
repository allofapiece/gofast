package com.pinwheel.gofast.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.pinwheel.gofast.service.validation.UniqueEmail;
import com.pinwheel.gofast.service.validation.UniqueSlug;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.*;

@Data
@EqualsAndHashCode(of = {"id"})
@ToString(of = {"id", "email", "status", "displayName", "firstName", "lastName", "about", "createdAt", "updatedAt",
        "roles"})
@NoArgsConstructor
@UniqueSlug
@UniqueEmail
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.WithId.class)
    private Long id;

    @JsonView(Views.WithGeneral.class)
    private boolean isCompany;

    @NotNull
    @Size(min = 4, max = 40)
    @JsonView(Views.WithExtended.class)
    @Column(updatable = false)
    private String email;

    @Enumerated(value = EnumType.STRING)
    @JsonView(Views.WithGeneral.class)
    private Status status;

    @JsonView(Views.WithGeneral.class)
    private String slug;

    @Type(type = "text")
    @JsonView(Views.WithExtended.class)
    private String about;

    @CreationTimestamp
    @JsonView(Views.WithTimestamps.class)
    private Timestamp createdAt;

    @UpdateTimestamp
    @JsonView(Views.WithTimestamps.class)
    private Timestamp updatedAt;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    @JsonView(Views.WithGeneral.class)
    private Set<Role> roles;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JsonView(Views.WithDependencies.class)
    @JsonIgnore
    private List<Password> passwords = new LinkedList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonView(Views.WithDependencies.class)
    private List<VerificationToken> verificationTokens = new LinkedList<>();

    public boolean isAdmin() {
        return this.getRoles().contains(Role.ADMIN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.status != Status.BANNED;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.status == Status.ACTIVE;
    }

    public void setPassword(Password password) {
        this.getPasswords().add(0, password);
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return !this.getPasswords().isEmpty() ? this.getPasswords()
                .stream()
                .filter(p -> p.getStatus().equals(Status.ACTIVE))
                .findFirst()
                .get()
                .getValue() : null;
    }

    public void addVerificationToken(VerificationToken verificationToken) {
        this.verificationTokens.add(verificationToken);
    }

    public VerificationToken getVerificationToken(int i) {
        return this.verificationTokens.get(i);
    }

    public VerificationToken getLastVerificationToken() {
        return !getVerificationTokens().isEmpty() ? getVerificationToken(getVerificationTokens().size() - 1) : null;
    }

    public String getFullName() {
        return this.id.toString();
    }
}
