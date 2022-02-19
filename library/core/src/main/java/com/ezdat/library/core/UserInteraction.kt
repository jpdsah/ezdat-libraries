package com.ezdat.library.core

import kotlin.reflect.KClass

interface UserInteraction

@Target(AnnotationTarget.FUNCTION) @Retention(AnnotationRetention.RUNTIME)
annotation class OnInteraction(val target : KClass<out UserInteraction>)