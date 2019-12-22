package utils;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;

public class QueryMaker<T> {
    private Specification<T> specification;
    private T object;

    public Specification<T> getSpecification() {
        return specification;
    }

    public void setSpecification(Specification<T> specification) {
        this.specification = specification;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public QueryMaker(T object) {
        this.object = object;
    }

    public QueryMaker() {
    }

    public QueryMaker<T> lk(String column, String value) {
        Specification<T> specificationLocal = (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            try {
                Field declaredField = object.getClass().getDeclaredField(column);
                declaredField.setAccessible(true);
                if (declaredField.get(object) != null && !"".equals(declaredField.get(object))) {
                    Predicate like = criteriaBuilder.like(root.get(column).as(String.class), "%" + value + "%");
                    return criteriaBuilder.and(like);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
        if (specificationLocal == null) {
            return this;
        }
        if (this.specification == null) {
            this.specification = specificationLocal;
        } else {
            this.specification = this.specification.and(specificationLocal);
        }
        return this;
    }

    public QueryMaker<T> eq(String column, String value) {
        Specification<T> specificationLocal = (Specification<T>) (root, criteriaQuery, criteriaBuilder) -> {
            try {
                Field declaredField = object.getClass().getDeclaredField(column);
                declaredField.setAccessible(true);
                if (declaredField.get(object) != null && !"".equals(declaredField.get(object))) {
                    Predicate equal = criteriaBuilder.equal(root.get(column).as(String.class), value);
                    return criteriaBuilder.and(equal);
                }
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return null;
        };
        if (specificationLocal == null) {
            return this;
        }
        if (this.specification == null) {
            this.specification = specificationLocal;
        } else {
            this.specification = this.specification.and(specificationLocal);
        }
        return this;
    }
}