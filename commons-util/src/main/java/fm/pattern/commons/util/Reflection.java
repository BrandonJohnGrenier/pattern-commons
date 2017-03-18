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

    /**
     * Returns the specified <em>property</em> value on the given <em>instance</em>, returned as the specified
     * <em>type</em>.
     * 
     * @param instance The object instance.
     * @param property The property to get.
     * @param type The return type to use.
     * 
     * @return An Object representing the property value, or null if no property value exists.
     */
    public static <T> T get(Object instance, String property, Class<T> type) {
        return (T) get(instance, property);
    }

    /**
     * Returns the specified <em>property</em> value on the given <em>instance</em>.
     * 
     * @param instance The object instance.
     * @param property The property to get.
     * 
     * @return An Object representing the property value, or null if no property value exists.
     */
    public static Object get(Object instance, String property) {
        try {
            return PropertyUtils.getProperty(instance, property);
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * Sets the specified <em>property</em> value on the given <em>instance</em>.
     * 
     * @param instance The object instance.
     * @param property The property to set.
     * @param value The property value.
     * 
     * @return The updated instance if the property was set correctly, null otherwise.
     */
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
