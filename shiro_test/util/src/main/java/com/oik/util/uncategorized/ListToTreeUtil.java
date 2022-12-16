package com.oik.util.uncategorized;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * 列表树跑龙套
 *
 * @author LEAF
 * @date 2022-09-07
 */
@Slf4j
public class ListToTreeUtil {
    public interface GetLambdaName extends Serializable {
        String METHOD_NAME = "writeReplace";

        default String getLambdaMethodName() {
            final Class<? extends GetLambdaName> aClass = this.getClass();
            String implMethodName = null;
            try {
                final Method method = aClass.getDeclaredMethod(METHOD_NAME);
                method.setAccessible(true);
                SerializedLambda lambda = (SerializedLambda) method.invoke(this);
                implMethodName = lambda.getImplMethodName();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return implMethodName;
        }
    }

    @FunctionalInterface
    public interface XKFunction<T, R> extends Function<T, R>, GetLambdaName {
    }

    @FunctionalInterface
    public interface XKBiConsumer<T, R> extends BiConsumer<T, R>, GetLambdaName {
    }

    @FunctionalInterface
    public interface XKPredicate<T> extends Predicate<T>, GetLambdaName {
    }


    private static <T> String getMethodName(GetLambdaName fun) {
        if (fun != null) {
            return fun.getLambdaMethodName();
        }
        return null;
    }

    public static <T> List<T> listToTree(List<T> source, XKPredicate<T> isRoot, XKFunction<T, ?> idFun, XKFunction<T, ?> pidFun, XKFunction<T, List<T>> getChildFun, XKBiConsumer<T, List<T>> setChildFun) {
        if (Objects.isNull(source) || Objects.isNull(idFun) || Objects.isNull(pidFun) || Objects.isNull(getChildFun) || Objects.isNull(setChildFun) || source.isEmpty()) {
            return new ArrayList<>();
        }
        final List<T> ret = new ArrayList<>();
        final Map<Object, T> map = new HashMap<>();
        source.forEach(t -> {
            Optional.ofNullable(isRoot).map(r -> {
                if (isRoot.test(t)) {
                    ret.add(t);
                }
                return r;
            }).orElseGet(() -> {
                Optional.ofNullable(pidFun.apply(t)).orElseGet(() -> {
                    ret.add(t);
                    return null;
                });
                return null;
            });
            map.put(idFun.apply(t), t);
        });
        source.forEach(t -> map.computeIfPresent(pidFun.apply(t), (k, v) -> {
            Optional.ofNullable(getChildFun.apply(v)).orElseGet(() -> {
                final List<T> list = new ArrayList<>();
                setChildFun.accept(v, list);
                return list;
            }).add(t);
            return v;
        }));
        return ret;
    }

    public static <T> List<T> listToTree(List<T> source, XKFunction<T, ?> idFun, XKFunction<T, ?> pidFun, XKFunction<T, List<T>> getChildFun, XKBiConsumer<T, List<T>> setChildFun) {
        return listToTree(source, null, idFun, pidFun, getChildFun, setChildFun);
    }

}
