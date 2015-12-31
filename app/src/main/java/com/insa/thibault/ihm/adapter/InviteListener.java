package com.insa.thibault.ihm.adapter;

import com.insa.thibault.ihm.business.User;

/**
 * Created by Thibault on 27/12/2015.
 */
public interface InviteListener {

    void invite(User user);
    void cancelInvite(User user);
}
