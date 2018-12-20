package com.cxx.learndemo.mylearndemo;

import java.lang.annotation.Repeatable;

@Repeatable(Persons.class)
public @interface Person {
    String role() default "";
}
