package com.studyweb.studyweb.settings;

import com.studyweb.studyweb.domain.Account;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Data
public class Profile {

    @Length(max =35)
    private String bio;

    private String url;
    @Length(max =50)
    private String occupation;

    private String location;

    public Profile(Account account) {
        this.bio = account.getBio();
        this.url = account.getUrl();
        this.occupation = account.getOccupation();
        this.location =  account.getLocation();
    }
}
