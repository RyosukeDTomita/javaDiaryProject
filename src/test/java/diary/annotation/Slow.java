package diary.annotation;

import org.junit.jupiter.api.Tag;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * &#064;Slow is able to use instead of &#064;("slow")
 * 
 * @author iceman
 * @version 1.0
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Tag("slow")
public @interface Slow {
}
