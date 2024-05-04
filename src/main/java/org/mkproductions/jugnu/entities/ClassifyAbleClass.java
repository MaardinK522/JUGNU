package org.mkproductions.jugnu.entities;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ClassifyAbleClass {
    String key() default "";
}
