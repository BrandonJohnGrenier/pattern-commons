/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fm.pattern.commons.util;

import org.apache.commons.beanutils.PropertyUtils;

@SuppressWarnings("unchecked")
public final class Reflection {

    private Reflection() {

    }

    public static <T> T get(Object instance, String property, Class<T> type) {
        return (T) get(instance, property);
    }

    public static Object get(Object instance, String property) {
        try {
            return PropertyUtils.getProperty(instance, property);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static <T> T set(T instance, String property, Object value) {
        try {
            PropertyUtils.setProperty(instance, property, value);
            return instance;
        }
        catch (Exception e) {
            return null;
        }
    }

}
