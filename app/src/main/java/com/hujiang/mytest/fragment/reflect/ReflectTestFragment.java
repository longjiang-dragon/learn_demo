package com.hujiang.mytest.fragment.reflect;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hujiang.mytest.fragment.aidlFragment.R;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * Date:  2019-11-16
 * Time:  17:07
 * Author: jianglong
 * -----------------------------
 * MISSION
 */
public class ReflectTestFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reflect_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        parseField();
        parseMethod();
    }

    private void parseMethod() {
        for (Method method : TestClass.class.getMethods()) {
            Type type = method.getGenericReturnType();
            if (type instanceof ParameterizedType) {
                printLog(method.getName() + "   " + getParameterUpperBound(0, (ParameterizedType) type).getTypeName());
            }
            if (type instanceof TypeVariable) {
                printLog(method.getName() + "   " + getParameterUpperBound(0, (ParameterizedType) type).getTypeName());
            }
        }
    }

    private void parseField() {
        for (Field declaredField : TestClass.class.getDeclaredFields()) {
            Log.i("ReflectTestFragment", declaredField.getName() + "   " + declaredField.getGenericType());
            if (declaredField.getGenericType() instanceof ParameterizedType) {
                //参数化类型
                printLog(getParameterUpperBound(0, (ParameterizedType) declaredField.getGenericType()).getTypeName());
                printLog(((ParameterizedType) declaredField.getGenericType()).getRawType().getClass().getName());
            }

            if (declaredField.getGenericType() instanceof TypeVariable) {
                printLog("类型变量");
            }

            if (declaredField.getGenericType() instanceof GenericArrayType) {
                printLog("数组类型");
            }
            if (declaredField.getGenericType() instanceof WildcardType) {
                printLog("通配符类型");
            }
        }
    }

    static Type getParameterUpperBound(int index, ParameterizedType type) {
        Type[] types = type.getActualTypeArguments();
        if (index < 0 || index >= types.length) {
            throw new IllegalArgumentException(
                    "Index " + index + " not in range [0," + types.length + ") for " + type);
        }
        Type paramType = types[index];
        if (paramType instanceof WildcardType) {
            return ((WildcardType) paramType).getUpperBounds()[0];
        }
        return paramType;
    }

    private void printLog(String logMsg) {
        Log.i("ReflectTestFragment", logMsg);
    }
}
