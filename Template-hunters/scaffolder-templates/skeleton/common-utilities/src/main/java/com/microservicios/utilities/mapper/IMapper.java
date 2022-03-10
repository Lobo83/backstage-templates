package com.microservicios.utilities.mapper;

import java.util.List;

/**
 * The interface Mapper.
 *
 * @param <X> the type parameter
 * @param <T> the type parameter
 *
 * @author joseluis.anton
 */
public interface IMapper<X, T> {

  /**
   * Class to entity.
   *
   * @param model the model
   *
   * @return the x
   */
  X modelToEntity(T model);

  /**
   * Entity to class.
   *
   * @param entity the entity
   *
   * @return the t
   */
  T entityToModel(X entity);

  /**
   * Model list to list entity list.
   *
   * @param model the model
   *
   * @return the list
   */
  List<X> modelListToListEntity(List<T> model);

  /**
   * Entity list to list model list.
   *
   * @param entity the entity
   *
   * @return the list
   */
  List<T> entityListToListModel(List<X> entity);
}