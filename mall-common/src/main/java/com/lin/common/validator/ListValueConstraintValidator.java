package com.lin.common.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @projectName: mall
 * @package: com.lin.common.validator
 * @className: ListValueConstraintValidator
 * @author: LinYi
 * @description: TODO
 * @date: 2023/9/5 15:34
 * @version: 1.0
 */
public class ListValueConstraintValidator implements ConstraintValidator<ListValue,Integer> {
    private final Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(ListValue constraintAnnotation){
        int[] vals = constraintAnnotation.vals();
        for(int val:vals){
            set.add(val);
        }
    }
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(integer);
    }
}
