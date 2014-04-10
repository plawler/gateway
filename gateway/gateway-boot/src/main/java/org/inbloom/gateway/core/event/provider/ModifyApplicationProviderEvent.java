package org.inbloom.gateway.core.event.provider;

import org.inbloom.gateway.core.domain.ApplicationProvider;
import org.inbloom.gateway.core.domain.User;
import org.inbloom.gateway.core.event.RequestEvent;

/**
 * Created by lloydengebretsen on 3/24/14.
 */
public class ModifyApplicationProviderEvent implements RequestEvent {

    private final ApplicationProvider data;
    private final Long id;

    public ModifyApplicationProviderEvent(Long id, ApplicationProvider data)
    {
        this.id = id;
        this.data = data;
    }


    public Long getId() {
        return id;
    }

    public String getOrganizationName(){
        return data.getOrganizationName();
    }

    public String getApplicationProviderName(){
        return data.getApplicationProviderName();
    }


    public Long getUserId(){
        return data.getUser().getUserId();
    }

    public String getUserEmail(){
        return data.getUser().getEmail();
    }

    public String getUserFirstName(){
        return data.getUser().getFirstName();
    }

    public String getUserLastName(){
        return data.getUser().getLastName();
    }
}
