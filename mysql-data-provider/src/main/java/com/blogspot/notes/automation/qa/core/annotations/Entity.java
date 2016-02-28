package com.blogspot.notes.automation.qa.core.annotations;

import com.blogspot.notes.automation.qa.core.enums.Schema;

import java.lang.annotation.*;

/**
 * Author: Sergey Korol
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Entity {
	Class model();

	Schema schema();
}
