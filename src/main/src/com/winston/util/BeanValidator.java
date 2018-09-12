package com.winston.util;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.winston.exception.ParamException;
import org.apache.commons.collections.MapUtils;
import org.apache.ibatis.builder.ParameterExpression;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.*;

/**
 * @Author: 于新泽
 * @Date: Created in 13:22 2018/9/10.
 * @site :
 */

public class BeanValidator {

    //全局的校验工厂
    private static ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();

    /**定义一个普通的校验方法
     * @param t ：
     * @param groups
     * @param <T> : 这里用T来代替类型
     * @return
     */
    public static <T> Map<String, String> validate(T t, Class... groups) {
        //validator 是从工厂中获取的
        Validator validator = validatorFactory.getValidator();
        Set validateResult = validator.validate(t, groups);
        if (validateResult.isEmpty()) {// 如果当前没有值就是没有错误
            return Collections.emptyMap();
        } else {//当前有值就是出现了错误
            LinkedHashMap errors = Maps.newLinkedHashMap();
            Iterator iterator = validateResult.iterator();
            while (iterator.hasNext()) {
                ConstraintViolation violation = (ConstraintViolation)iterator.next();
                errors.put(violation.getPropertyPath().toString(), violation.getMessage());
            }
            return errors;
        }
    }

    public static Map<String, String> validateList(Collection<?> collection) {
        Preconditions.checkNotNull(collection);
        Iterator iterator = collection.iterator();
        Map errors;
        do {
            if (!iterator.hasNext()) {//如果当前的iterator没有值，
                return Collections.emptyMap();
            }
            Object object = iterator.next();
            errors = validate(object, new Class[0]);
        } while (errors.isEmpty());//每次循环都要验证一下

        return errors;
    }

    /**
     * 用这个方法来包装一下上面的validate和validateList,以便于区分
     * @param first
     * @param objects
     * @return
     */
    public static Map<String,String> validateObject(Object first ,Object... objects){
        if(objects != null && objects.length > 0){
            //把里面的数传入数组中
            return validateList(Lists.asList(first,objects));
        }else{//只传入了一个值
            return validate(first, new Class[0]);
        }
    }

    /**
     * 这里不做任何返回，只是用来判断是否有异常
     */
    public static void check(Object param) throws ParamException{
        Map<String,String> map = BeanValidator.validateObject(param);
        if(MapUtils.isNotEmpty(map)){//如果当前map不为空
            throw new ParamException(map.toString());
        }
    }
}
