package com.pinwheel.gofast.util;

import org.springframework.util.StringUtils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Freemarker utils. Static helper methods freemarker templates.
 *
 * @version 1.0.0
 */
public class FreeMarkerUtils {
    /**
     * Adds class to the attributes. If attributes already has class property, {@code targetClass} will be added to
     * the beginning of the class list. If class property does not exist, it will be created with {@code targetClass}.
     *
     * @param attributes  tag attributes.
     * @param targetClass class which will be added to attributes.
     * @return result attributes.
     */
    public static String addClass(String attributes, String targetClass) {
        if (!StringUtils.isEmpty(attributes) && attributes.contains("class=\"")) {
            return attributes.replace("class=\"", "class=\"" + targetClass + " ");
        }

        return attributes + "class=\"" + targetClass + "\"";
    }

    public static Map<String, Object> it(Object obj) throws IntrospectionException {
        return Arrays.stream(Introspector.getBeanInfo(obj.getClass())
                .getPropertyDescriptors())
                .filter(pd -> pd.getReadMethod() != null)
                .collect(Collectors.toMap(
                        PropertyDescriptor::getName,
                        pd -> {
                            try {
                                return pd.getReadMethod().invoke(null);
                            } catch (ReflectiveOperationException e) {
                                throw new RuntimeException(e);
                            }
                        }
                ));
    }
}
