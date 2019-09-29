package com.czl.daliu.test.annotation.compile;

import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * 注解处理器类
 * 参考：https://blog.csdn.net/qq_20521573/article/details/82321755
 */
@SupportedAnnotationTypes("com.czl.daliu.test.annotation.compile.IShape")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class FactoryProcessor extends AbstractProcessor {
    private Types mTypeUtils;
    private Messager mMessager;
    private Filer mFiler;
    private MemberSubmissionEndpointReference.Elements mElementUtils;
    private Map<String, FactoryGroupedClasses> factoryClasses = new LinkedHashMap<String, FactoryGroupedClasses>();


    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //  通过RoundEnvironment获取到所有被@Factory注解的对象
        for (Element annotatedElement : roundEnv.getElementsAnnotatedWith(Factory.class)) {
            if (annotatedElement.getKind() != ElementKind.CLASS) {
                /*throw new ProcessingException(annotatedElement, "Only classes can be annotated with @%s",
                        Factory.class.getSimpleName());*/
            }
            TypeElement typeElement = (TypeElement) annotatedElement;
            FactoryAnnotatedClass annotatedClass = new FactoryAnnotatedClass(typeElement);
        }
        return true;
    }

    private void checkValidClass(FactoryAnnotatedClass item) throws ProcessingException {

        TypeElement classElement = item.getTypeElement();

        if (!classElement.getModifiers().contains(Modifier.PUBLIC)) {
            throw new ProcessingException(classElement, "The class %s is not public.",
                    classElement.getQualifiedName().toString());
        }

        // 如果是抽象方法则抛出异常终止编译
        if (classElement.getModifiers().contains(Modifier.ABSTRACT)) {
            throw new ProcessingException(classElement,
                    "The class %s is abstract. You can't annotate abstract classes with @%",
                    classElement.getQualifiedName().toString(), Factory.class.getSimpleName());
        }

        // 这个类必须是在@Factory.type()中指定的类的子类，否则抛出异常终止编译
        TypeElement superClassElement = mElementUtils.getTypeElement(item.getQualifiedFactoryGroupName());
        if (superClassElement.getKind() == ElementKind.INTERFACE) {
            // 检查被注解类是否实现或继承了@Factory.type()所指定的类型，此处均为IShape
            if (!classElement.getInterfaces().contains(superClassElement.asType())) {
                throw new ProcessingException(classElement,
                        "The class %s annotated with @%s must implement the interface %s",
                        classElement.getQualifiedName().toString(), Factory.class.getSimpleName(),
                        item.getQualifiedFactoryGroupName());
            }
        } else {
            TypeElement currentClass = classElement;
            while (true) {
                TypeMirror superClassType = currentClass.getSuperclass();

                if (superClassType.getKind() == TypeKind.NONE) {
                    // 向上遍历父类，直到Object也没获取到所需父类，终止编译抛出异常
                    throw new ProcessingException(classElement,
                            "The class %s annotated with @%s must inherit from %s",
                            classElement.getQualifiedName().toString(), Factory.class.getSimpleName(),
                            item.getQualifiedFactoryGroupName());
                }

                if (superClassType.toString().equals(item.getQualifiedFactoryGroupName())) {
                    // 校验通过，终止遍历
                    break;
                }
                currentClass = (TypeElement) mTypeUtils.asElement(superClassType);
            }
        }

        // 检查是否由public的无参构造方法
        for (Element enclosed : classElement.getEnclosedElements()) {
            if (enclosed.getKind() == ElementKind.CONSTRUCTOR) {
                ExecutableElement constructorElement = (ExecutableElement) enclosed;
                if (constructorElement.getParameters().size() == 0 &&
                        constructorElement.getModifiers().contains(Modifier.PUBLIC)) {
                    // 存在public的无参构造方法，检查结束
                    return;
                }
            }
        }

        // 为检测到public的无参构造方法，抛出异常，终止编译
        throw new ProcessingException(classElement,
                "The class %s must provide an public empty default constructor",
                classElement.getQualifiedName().toString());
    }
}
