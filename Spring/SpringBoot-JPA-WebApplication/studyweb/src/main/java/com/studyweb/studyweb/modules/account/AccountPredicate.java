package com.studyweb.studyweb.modules.account;

import com.querydsl.core.types.Predicate;
import com.studyweb.studyweb.modules.tags.Tag;
import com.studyweb.studyweb.modules.zone.Zone;

import java.util.Set;

public class AccountPredicate {

    public static Predicate findByTagsAndZones(Set<Tag> tags, Set<Zone> zones){
        QAccount account = QAccount.account;
        return account.tags.any().in(tags).and(account.zones.any().in(zones));
    }
}
