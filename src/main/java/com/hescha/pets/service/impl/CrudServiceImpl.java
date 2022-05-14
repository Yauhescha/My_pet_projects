package com.hescha.pets.service.impl;

import com.hescha.pets.service.CrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.EntityNotFoundException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static java.lang.String.format;

@RequiredArgsConstructor
public class CrudServiceImpl<T> implements CrudService<T> {
    private final JpaRepository<T, Long> repository;
    private T t;

    @Override
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<T> find(Long id) {
        return repository.findById(id);
    }

    @Override
    public T read(Long id) {
        return repository.findById(id).orElseThrow(generateNotFoundExceptionWithThisClass(id));
    }

    @Override
    public T create(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public T update(T entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Supplier<RuntimeException> generateNotFoundExceptionWithThisClass(Long id) {
        return () -> {
            try {
                t = (T) ((Class) ((ParameterizedType) this.getClass().
                    getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            Class<?> aClass = t.getClass();
            String modelName = aClass.getName().replace(aClass.getPackageName() + ".", "");
            String message = format("Entity '%s' with id '%d' not found.", modelName, id);
            throw new EntityNotFoundException(message);
        };
    }
}
