package com.github.binodnme.util;

import com.github.binodnme.annotations.Filterable;
import com.github.binodnme.pojo.FilterUnit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Binod Shrestha <binodshrestha@lftechnology.com> on 10/31/17.
 */

class FilterUtilsTest {

    @Test
    @DisplayName("should return stream of fields with Filterable annotations")
    void getFilterableFields_Test() {
        //given

        //when
        Stream<Field> filterableFields = FilterUtils.getFilterableFields(User.class);

        //then
        assertEquals(2L, filterableFields.count());

    }

    @Test
    @DisplayName("Should return usable filter unit i.e remove filter unit which contains invalid key")
    void getUsableFilterUnits_test() {
        //given
        List<FilterUnit> filterUnits = Arrays.asList(new FilterUnit("name", "haku"), new FilterUnit("address", "ktm"));

        //when
        Stream<FilterUnit> usableFilterUnits = FilterUtils.getValidFilterUnits(filterUnits, User.class);
        long count = usableFilterUnits.count();

        //then
        assertEquals(1, count);

    }


    @Test
    @DisplayName("Should return true if filter unit key matches any of the field names")
    void isUsable_positiveTest() throws Exception {
        //given
        FilterUnit filterUnit = new FilterUnit("address", "ktm");
        Field[] filterableFields = {User.class.getDeclaredField("address"), User.class.getDeclaredField("age")};

        //when
        boolean usable = FilterUtils.isValid(filterUnit, Arrays.asList(filterableFields));

        //then
        assertTrue(usable);
    }

    class User {
        private String name;

        @Filterable
        private String address;

        @Filterable
        private Integer age;

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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }


}