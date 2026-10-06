package com.project.auth_app.auth.helpers;

import java.util.UUID;

public class UserHelper {
    public static UUID parseUUID(String uuid){
        return UUID.fromString(uuid);
    }
}
