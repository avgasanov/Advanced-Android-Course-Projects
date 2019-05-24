package com.tuesday_apps.poweradapter.item;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

import dagger.MapKey;

@MapKey
@Target(ElementType.METHOD)
public @interface RenderKey {

    String value();
}
