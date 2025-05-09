package cn.xilio.turtle.core.lucene.annotations;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface TField {
    /**
     * 是否索引
     */
    boolean index() default true;

    /**
     * 是否存储
     */
    boolean store() default false;
}
