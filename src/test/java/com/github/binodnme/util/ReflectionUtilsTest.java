package com.github.binodnme.util;

import com.github.binodnme.annotations.Filterable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.lang.reflect.Field;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */

class ReflectionUtilsTest {

    @Test
    @DisplayName("should return stream of fields with Filterable annotations")
    void getFilterableFields_Test() {

        Stream<Field> filterableFields = ReflectionUtils.getFilterableFields(User.class);

        assertEquals(1L, filterableFields.count());

    }

    class User {
        private String name;

        @Filterable
        private String address;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }


}