package com.example.locadora.annotations;

import java.lang.annotation.*;

@Target(ElementType.METHOD)

@Retention(RetentionPolicy.RUNTIME)

public @interface PublicRoute {}