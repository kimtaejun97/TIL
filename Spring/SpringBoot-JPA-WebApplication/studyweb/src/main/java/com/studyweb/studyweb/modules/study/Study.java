package com.studyweb.studyweb.modules.study;

import com.studyweb.studyweb.modules.account.Account;
import com.studyweb.studyweb.modules.account.UserAccount;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;
import lombok.*;

import javax.persistence.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraph(name = "Study.withAll", attributeNodes = {
        @NamedAttributeNode("tags"),
        @NamedAttributeNode("zones"),
        @NamedAttributeNode("managers"),
        @NamedAttributeNode("members")
})

@NamedEntityGraph(name = "Study.withManagersTags", attributeNodes = {
        @NamedAttributeNode("tags"),
        @NamedAttributeNode("managers")
})

@NamedEntityGraph(name = "Study.withManagersZones", attributeNodes = {
        @NamedAttributeNode("zones"),
        @NamedAttributeNode("managers")
})

@NamedEntityGraph(name = "Study.withManagers", attributeNodes = {
        @NamedAttributeNode("managers")
})
@NamedEntityGraph(name = "Study.withMembers", attributeNodes = {
        @NamedAttributeNode("members")
})

@NamedEntityGraph(name = "Study.withTeams", attributeNodes = {
        @NamedAttributeNode("managers"),
        @NamedAttributeNode("members")
})

@NamedEntityGraph(name = "Study.withTagsAndZones", attributeNodes = {
        @NamedAttributeNode("tags"),
        @NamedAttributeNode("zones")
})

@Getter @Setter @AllArgsConstructor
@NoArgsConstructor @Builder
@Entity
public class Study {

    @Id @GeneratedValue
    private Long id;

    @ManyToMany
    private Set<Account> managers = new HashSet<>();

    @ManyToMany
    private Set<Account> members = new HashSet<>();

    @Column(unique = true)
    private String path;

    private String title;

    private String shortDescription;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String fullDescription;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String image;

    @ManyToMany
    Set<Tag> tags = new HashSet<>();

    @ManyToMany
    private Set<Zone> zones = new HashSet<>();

    private LocalDateTime publishedDateTime;

    private LocalDateTime closedDateTime;

    private LocalDateTime recruitingUpdateDateTime;

    private boolean recruiting;

    private  boolean published;

    private boolean closed;

    private boolean useBanner;

    @Column(nullable = true)
    private int memberCount;

    public static String getPath(String path) {
        return URLEncoder.encode(path, StandardCharsets.UTF_8);
    }
    public String getEncodedPath(){
        return URLEncoder.encode(this.path, StandardCharsets.UTF_8);
    }

    public void addManager(Account account) {
        managers.add(account);
    }

    public boolean isJoinable(UserAccount userAccount){
        return this.isPublished() && isRecruiting() && !isMember(userAccount) && !isManager(userAccount);
    }

    public boolean isMember(UserAccount userAccount){
        return this.members.contains(userAccount.getAccount());
    }

    public boolean isManager(UserAccount userAccount){
        return this.managers.contains(userAccount.getAccount());
    }

    public boolean canChangeStateOfRecruiting() {
        if(this.recruitingUpdateDateTime == null){
            return true;
        }

        return this.recruitingUpdateDateTime.isBefore(LocalDateTime.now().minusHours(1));
    }

    public boolean isRemovable() {
        return !this.published;
    }


}
